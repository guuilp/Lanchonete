package com.github.guuilp.lanchonete.promocoes

import com.github.guuilp.lanchonete.data.Promocao
import com.github.guuilp.lanchonete.data.source.LanchoneteDataSource
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository

class PromocoesPresenter(val lanchoneteRepository: LanchoneteRepository,
                         val promocoesView: PromocoesContract.View) : PromocoesContract.Presenter{

    init {
        promocoesView.presenter = this
    }

    override fun start() {
        loadPromocoes()
    }

    fun loadPromocoes(){
        lanchoneteRepository.listaPromocoes(object: LanchoneteDataSource.ListaPromocoesCallback{
            override fun onListaPromocoesSuccess(listaPromocoes: List<Promocao>) {
                if(listaPromocoes.isNotEmpty()) promocoesView.showPromocoes(listaPromocoes)
                else promocoesView.showEmptyState()
            }

            override fun onListaPromocoesError() {
                promocoesView.showError()
            }
        })
    }
}