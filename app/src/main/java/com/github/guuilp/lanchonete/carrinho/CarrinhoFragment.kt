package com.github.guuilp.lanchonete.carrinho


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.guuilp.lanchonete.R
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.detalheLanche.DetalheLancheActivity
import com.github.guuilp.lanchonete.lanches.LanchesAdapter
import kotlinx.android.synthetic.main.fragment_carrinho.*
import kotlinx.android.synthetic.main.fragment_lista_lanche.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class CarrinhoFragment : Fragment(), CarrinhoContract.View {
    override fun showEmptyState(show: Boolean) {

        if(show) {
            groupEmptyState.visibility = View.VISIBLE
            conteudo.visibility = View.GONE
        } else {
            groupEmptyState.visibility = View.GONE
            conteudo.visibility = View.VISIBLE
        }
    }

    override fun showError() {
        toast("Deu erro")
    }

    override val isActive: Boolean
        get() = isAdded

    override lateinit var presenter: CarrinhoContract.Presenter

    override fun showLanches(lanches: List<Lanche>, precoFinal: Double) {
        rvListaLanchesPedido.adapter = LanchesAdapter(lanches, context){ lanche, position ->
            startActivity<DetalheLancheActivity>("id" to lanche.id)
        }

        finalizar.text = "FINALIZAR - R$ " + String.format("%.2f", precoFinal)
    }

    override fun showLoading(active: Boolean) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_carrinho, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CarrinhoFragment()
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }
}
