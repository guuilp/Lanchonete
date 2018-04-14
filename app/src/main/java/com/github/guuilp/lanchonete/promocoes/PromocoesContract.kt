package com.github.guuilp.lanchonete.promocoes

import com.github.guuilp.lanchonete.BasePresenter
import com.github.guuilp.lanchonete.BaseView
import com.github.guuilp.lanchonete.data.Promocao

interface PromocoesContract{
    interface View : BaseView<Presenter>{
        val isActive: Boolean

        fun showPromocoes(promocoes: List<Promocao>)

        fun showLoading(active: Boolean)

        fun showError()

        fun showEmptyState()
    }

    interface Presenter : BasePresenter
}