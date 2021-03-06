package com.github.guuilp.lanchonete.lanches

import com.github.guuilp.lanchonete.BasePresenter
import com.github.guuilp.lanchonete.BaseView
import com.github.guuilp.lanchonete.data.Lanche

interface LanchesContract{
    interface View : BaseView<Presenter>{
        val isActive: Boolean

        fun showLanches(lanches: List<Lanche>)

        fun showAPIError()
    }

    interface Presenter : BasePresenter
}