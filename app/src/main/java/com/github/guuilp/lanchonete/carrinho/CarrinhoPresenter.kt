package com.github.guuilp.lanchonete.carrinho

import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.ItemPedido
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.data.source.LanchoneteDataSource
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository

class CarrinhoPresenter(val lanchoneteRepository: LanchoneteRepository,
                        val lanchesView: CarrinhoContract.View) : CarrinhoContract.Presenter{

    init {
        lanchesView.presenter = this
    }

    override fun start() {
        loadIngredientesELanches()
    }

    fun loadItemsDoCarrinho(listaDeLanches: List<Lanche>){
        lanchoneteRepository.listaPedido(object : LanchoneteDataSource.ListaPedidoCallback{
            override fun onListaPedidoSuccess(listaPedido: List<ItemPedido>) {

                if(listaPedido.isNotEmpty()) {
                    val listaDeLancheDoPedido = mutableListOf<Lanche>()

                    listaPedido.forEach { itemPedido ->
                        listaDeLancheDoPedido.add(listaDeLanches.single { it.id == itemPedido.id })
                    }

                    val precoFinal = listaDeLancheDoPedido.sumByDouble {
                        it.price
                    }

                    lanchesView.showLanches(listaDeLancheDoPedido, precoFinal)

                    lanchesView.showEmptyState(false)
                } else {
                    lanchesView.showEmptyState(true)
                }
            }

            override fun onListaPedidoError() {
                lanchesView.showError()
            }

        })
    }

    fun loadIngredientesELanches(){
        lanchoneteRepository.listaDeIngredientes(object: LanchoneteDataSource.ListaDeIngredientesCallback{
            override fun onListaDeIngredientesSuccess(listaDeIngredientes: List<Ingrediente>) {
                loadLanches(listaDeIngredientes)
            }

            override fun onListaDeIngredientesError() {
                lanchesView.showError()
            }
        })
    }

    fun loadLanches(listaDeIngredientes: List<Ingrediente>){
        lanchoneteRepository.listaDeLanches(object: LanchoneteDataSource.ListaDeLanchesCallback{
            override fun onListaDeLanchesSuccess(listaDeLanches: List<Lanche>) {
                loadItemsDoCarrinho(lanchoneteRepository.atualizaListaComPrecoEIngrediente(listaDeLanches, listaDeIngredientes))
            }

            override fun onListaDeLanchesError() {
                lanchesView.showError()
            }
        })
    }
}