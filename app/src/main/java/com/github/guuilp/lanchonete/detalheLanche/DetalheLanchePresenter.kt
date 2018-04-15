package com.github.guuilp.lanchonete.detalheLanche

import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.ItemPedido
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.data.source.LanchoneteDataSource
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository

class DetalheLanchePresenter(val idLanche: Int,
                             val lanchoneteRepository: LanchoneteRepository,
                             val detalheLancheView: DetalheLancheContract.View) : DetalheLancheContract.Presenter{

    init {
        detalheLancheView.presenter = this
    }

    override fun start() {
        loadIngredientesELanches()
    }

    override fun adicionarLancheAoPedido(idLanche: Int) {
        lanchoneteRepository.adicionarLancheAoPedido(idLanche, object: LanchoneteDataSource.AdicionarLancheAoPedidoCallback{
            override fun onAdicionarLancheAoPedidoSuccess(itemPedido: ItemPedido) {
                detalheLancheView.showToast("Produto adicionado ao carrinho com sucesso!")
            }

            override fun onAdicionarLancheAoPedidoError() {
                detalheLancheView.showToast("Deu erro")
            }

        })
    }

    fun loadIngredientesELanches(){
        lanchoneteRepository.listaDeIngredientes(object: LanchoneteDataSource.ListaDeIngredientesCallback{
            override fun onListaDeIngredientesSuccess(listaDeIngredientes: List<Ingrediente>) {
                loadLanche(listaDeIngredientes)
            }

            override fun onListaDeIngredientesError() {
                detalheLancheView.showToast("Deu erro")
            }
        })
    }

    fun loadLanche(listaDeIngredientes: List<Ingrediente>) {
        lanchoneteRepository.lanche(idLanche, object: LanchoneteDataSource.LancheCallback{
            override fun onLancheSuccess(lanche: Lanche) {
                detalheLancheView.showLanche(lanchoneteRepository.atualizaLancheComPrecoEIngrediente(lanche, listaDeIngredientes))
            }

            override fun onLancheError() {
                detalheLancheView.showToast("Deu erro")
            }
        })
    }
}