package com.github.guuilp.lanchonete.carrinho

import com.github.guuilp.lanchonete.BasePresenter
import com.github.guuilp.lanchonete.BaseView
import com.github.guuilp.lanchonete.data.Lanche

interface CarrinhoContract{
    interface View : BaseView<Presenter>{
        val isActive: Boolean

        fun showLanches(lanches: List<Lanche>, precoFinal: String)

        fun showAPIError()

        fun showEmptyState(show: Boolean)
    }

    interface Presenter : BasePresenter
}