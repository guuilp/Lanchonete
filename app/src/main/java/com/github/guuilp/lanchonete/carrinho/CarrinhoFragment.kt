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
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class CarrinhoFragment : Fragment(), CarrinhoContract.View {

    override val isActive: Boolean
        get() = isAdded

    override lateinit var presenter: CarrinhoContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_carrinho, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        finalizar.setOnClickListener { longToast(getString(R.string.finalizar_pedido)) }
    }

    override fun showEmptyState(show: Boolean) {
        if(show) {
            groupEmptyState.visibility = View.VISIBLE
            conteudo.visibility = View.GONE
        } else {
            groupEmptyState.visibility = View.GONE
            conteudo.visibility = View.VISIBLE
        }
    }

    override fun showLanches(lanches: List<Lanche>, precoFinal: String) {
        rvListaLanchesPedido.adapter = LanchesAdapter(lanches, context){ _, _ -> }
        finalizar.text = getString(R.string.footer_carrinho, precoFinal)
    }

    override fun showAPIError() {
        toast(getString(R.string.erro_api_carrinho))
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
