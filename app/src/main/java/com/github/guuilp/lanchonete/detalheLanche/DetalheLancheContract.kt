package com.github.guuilp.lanchonete.detalheLanche

import com.github.guuilp.lanchonete.BasePresenter
import com.github.guuilp.lanchonete.BaseView
import com.github.guuilp.lanchonete.data.Lanche

interface DetalheLancheContract{
    interface View : BaseView<Presenter>{
        fun showLanche(lanche: Lanche)

        fun showAPIIngredienteError()
        fun showAPILancheError()
        fun showAPIAdicionarPedidoError()
        fun showProdutoAdicionadoMessage()
    }

    interface Presenter : BasePresenter{
        fun adicionarLancheAoPedido(idLanche: Int)
    }
}