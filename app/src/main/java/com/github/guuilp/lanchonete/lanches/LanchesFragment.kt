package com.github.guuilp.lanchonete.lanches


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.guuilp.lanchonete.R
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.detalheLanche.DetalheLancheActivity
import kotlinx.android.synthetic.main.fragment_lista_lanche.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class LanchesFragment : Fragment(), LanchesContract.View {
    override fun showError() {
        toast("Deu erro")
    }

    override val isActive: Boolean
        get() = isAdded

    override lateinit var presenter: LanchesContract.Presenter

    override fun showLanches(lanches: List<Lanche>) {
        rvListaLanches.adapter = LanchesAdapter(lanches, context){ lanche, position ->
            startActivity<DetalheLancheActivity>("id" to lanche.id)
        }
    }

    override fun showLoading(active: Boolean) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lista_lanche, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LanchesFragment()
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }
}
