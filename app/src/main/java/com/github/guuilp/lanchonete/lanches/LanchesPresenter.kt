package com.github.guuilp.lanchonete.lanches

import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.data.source.LanchoneteDataSource
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository

class LanchesPresenter(val lanchoneteRepository: LanchoneteRepository,
                       val lanchesView: LanchesContract.View) : LanchesContract.Presenter{

    init {
        lanchesView.presenter = this
    }

    override fun start() {
        loadIngredientesELanches()
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
                lanchesView.showLanches(lanchoneteRepository.atualizaListaComPrecoEIngrediente(listaDeLanches, listaDeIngredientes))
            }

            override fun onListaDeLanchesError() {
                lanchesView.showError()
            }
        })
    }
}