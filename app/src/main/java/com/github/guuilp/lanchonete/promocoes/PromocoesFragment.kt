package com.github.guuilp.lanchonete.promocoes


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.github.guuilp.lanchonete.R
import com.github.guuilp.lanchonete.data.Promocao
import kotlinx.android.synthetic.main.fragment_promocao.*
import org.jetbrains.anko.support.v4.toast

class PromocoesFragment : Fragment(), PromocoesContract.View {

    override val isActive: Boolean
        get() = isAdded

    override lateinit var presenter: PromocoesContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_promocao, container, false)
    }

    override fun showPromocoes(promocoes: List<Promocao>) {
        rvListaPromocao.adapter = PromocoesAdapter(promocoes, context)
    }

    override fun showLoading(active: Boolean) {

    }

    override fun showEmptyState() {
        groupEmptyState.visibility = View.VISIBLE
        rvListaPromocao.visibility = View.GONE
    }

    override fun showError() {
        toast("Deu erro")
    }

    companion object {
        @JvmStatic
        fun newInstance() = PromocoesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.start()
    }
}
