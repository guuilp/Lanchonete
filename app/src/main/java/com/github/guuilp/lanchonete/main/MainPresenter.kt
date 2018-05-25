package com.github.guuilp.lanchonete.main

import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.data.source.LanchoneteDataSource
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository


class MainPresenter(val lanchoneteRepository: LanchoneteRepository,
                    val mainView: MainContract.View) : MainContract.Presenter{

    init {
        mainView.presenter = this
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
                mainView.showAPIIngredienteError()
            }
        })
    }

    fun loadLanches(listaDeIngredientes: List<Ingrediente>){
        lanchoneteRepository.listaDeLanches(object: LanchoneteDataSource.ListaDeLanchesCallback{
            override fun onListaDeLanchesSuccess(listaDeLanches: List<Lanche>) {
                val listaDeLanchesComPreco = lanchoneteRepository.atualizaListaComPrecoEIngrediente(listaDeLanches, listaDeIngredientes)

                mainView.getLanchesEIngredientes(listaDeIngredientes, listaDeLanchesComPreco)
            }

            override fun onListaDeLanchesError() {
                mainView.showAPILancheError()
            }
        })
    }
}