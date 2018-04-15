package com.github.guuilp.lanchonete.detalheLanche

import com.github.guuilp.lanchonete.BasePresenter
import com.github.guuilp.lanchonete.BaseView
import com.github.guuilp.lanchonete.data.Lanche

interface DetalheLancheContract{
    interface View : BaseView<Presenter>{
        fun showLanche(lanche: Lanche)

        fun showLoading(active: Boolean)

        fun showToast(text: String)
    }

    interface Presenter : BasePresenter{
        fun adicionarLancheAoPedido(idLanche: Int)
    }
}