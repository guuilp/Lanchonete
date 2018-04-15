package com.github.guuilp.lanchonete.lanches

import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.Lanche

class LanchesPresenter(val listaDeLanches: List<Lanche>,
                       val listaDeIngrediente: List<Ingrediente>,
                       val lanchesView: LanchesContract.View) : LanchesContract.Presenter{

    init {
        lanchesView.presenter = this
    }

    override fun start() {
        loadLanches()
    }

    fun loadLanches(){
        if (!lanchesView.isActive) {
            return
        }

        lanchesView.showLanches(listaDeLanches)
    }
}