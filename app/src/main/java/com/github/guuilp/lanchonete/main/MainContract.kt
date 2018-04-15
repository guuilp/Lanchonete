package com.github.guuilp.lanchonete.main

import com.github.guuilp.lanchonete.BasePresenter
import com.github.guuilp.lanchonete.BaseView
import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.Lanche

interface MainContract{
    interface View : BaseView<Presenter>{
        fun getLanchesEIngredientes(listaDeIngredientes: List<Ingrediente>, listaDeLanches: List<Lanche>)

        fun showError()
    }

    interface Presenter : BasePresenter
}