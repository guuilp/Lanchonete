package com.github.guuilp.lanchonete.detalheLanche

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.github.guuilp.lanchonete.R
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository
import com.github.guuilp.lanchonete.data.source.remote.LanchoneteRemoteDataSource
import kotlinx.android.synthetic.main.activity_detalhe_lanche.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.longToast

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

        personalizarLanche.setOnClickListener {
            longToast(getString(R.string.feature_nao_implementada))
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

    override fun showAPIIngredienteError() {
        snackbar(rootCoordinator, getString(R.string.erro_api_ingrediente))
    }

    override fun showAPILancheError() {
        snackbar(rootCoordinator, getString(R.string.erro_api_lanche))
    }

    override fun showAPIAdicionarPedidoError() {
        snackbar(rootCoordinator, getString(R.string.erro_api_adicionar_carrinho))
    }

    override fun showProdutoAdicionadoMessage() {
        snackbar(rootCoordinator, getString(R.string.message_produto_adicionado_carrinho))
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }
}