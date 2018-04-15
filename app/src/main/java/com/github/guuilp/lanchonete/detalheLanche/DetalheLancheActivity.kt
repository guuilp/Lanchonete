package com.github.guuilp.lanchonete.detalheLanche

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.github.guuilp.lanchonete.R
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository
import com.github.guuilp.lanchonete.data.source.remote.LanchoneteRemoteDataSource
import kotlinx.android.synthetic.main.activity_detalhe_lanche.*
import org.jetbrains.anko.toast

class DetalheLancheActivity: AppCompatActivity(), DetalheLancheContract.View  {

    override lateinit var presenter: DetalheLancheContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_lanche)

        val id = intent.getIntExtra("id", -1)

        DetalheLanchePresenter(id, LanchoneteRepository(LanchoneteRemoteDataSource), this)

        adicionarCarrinho.setOnClickListener {
            presenter.adicionarLancheAoPedido(id)
        }
    }

    override fun showLanche(lanche: Lanche) {
        Glide.with(this)
                .load(lanche.image)
                .into(imagem)

        titulo.text = lanche.name
        ingredientes.text = lanche.ingredientsString
        preco.text = lanche.priceFormated
    }

    override fun showLoading(active: Boolean) {
        toast("Loading")
    }

    override fun showToast(text: String) {
        toast(text)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }
}