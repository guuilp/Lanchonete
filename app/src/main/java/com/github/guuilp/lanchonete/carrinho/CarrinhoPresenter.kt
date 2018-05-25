package com.github.guuilp.lanchonete.carrinho

import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.ItemPedido
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.data.source.LanchoneteDataSource
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository

class CarrinhoPresenter(val listaDeLanches: List<Lanche>,
                        val listaDeIngrediente: List<Ingrediente>,
                        val lanchoneteRepository: LanchoneteRepository,
                        val lanchesView: CarrinhoContract.View) : CarrinhoContract.Presenter{

    init {
        lanchesView.presenter = this
    }

    override fun start() {
        loadItemsDoCarrinho()
    }

    fun loadItemsDoCarrinho(){
        lanchoneteRepository.listaPedido(object : LanchoneteDataSource.ListaPedidoCallback{
            override fun onListaPedidoSuccess(listaPedido: List<ItemPedido>) {

                if(listaPedido.isNotEmpty()) {

                    val listaDeLancheDoPedido = lanchoneteRepository.converterListaDePedidosEmListaDeLanche(listaPedido, listaDeLanches)

                    val precoFinal = lanchoneteRepository.calcularPrecoDoPedido(listaDeLancheDoPedido)

                    if (!lanchesView.isActive) {
                        return
                    }

                    lanchesView.showLanches(listaDeLancheDoPedido, precoFinal)

                    lanchesView.showEmptyState(false)
                } else {
                    if (!lanchesView.isActive) {
                        return
                    }

                    lanchesView.showEmptyState(true)
                }
            }

            override fun onListaPedidoError() {
                lanchesView.showAPIError()
            }

        })
    }
}