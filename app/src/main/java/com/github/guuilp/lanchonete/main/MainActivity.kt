package com.github.guuilp.lanchonete.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.github.guuilp.lanchonete.R
import com.github.guuilp.lanchonete.carrinho.CarrinhoFragment
import com.github.guuilp.lanchonete.carrinho.CarrinhoPresenter
import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository
import com.github.guuilp.lanchonete.lanches.LanchesFragment
import com.github.guuilp.lanchonete.lanches.LanchesPresenter
import com.github.guuilp.lanchonete.promocoes.PromocoesFragment
import com.github.guuilp.lanchonete.promocoes.PromocoesPresenter
import com.github.guuilp.lanchonete.util.DaggerLanchoneteComponent
import com.github.guuilp.lanchonete.util.RepositoryModule
import com.github.guuilp.lanchonete.util.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject override lateinit var presenter: MainContract.Presenter

    @Inject lateinit var repository: LanchoneteRepository

    private lateinit var lancheFragment: LanchesFragment
    private lateinit var promocoesFragment: PromocoesFragment
    private lateinit var carrinhoFragment: CarrinhoFragment

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.lanches -> {
                viewpager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.promocoes -> {
                viewpager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.carrinho -> {
                viewpager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        DaggerLanchoneteComponent.builder()
                .repositoryModule(RepositoryModule(this))
                .build()
                .inject(this)

        presenter.start()
    }

    private fun setupViewPager(viewPager: ViewPager, listaDeIngredientes: List<Ingrediente>, listaDeLanches: List<Lanche>){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        lancheFragment = LanchesFragment.newInstance()
        promocoesFragment = PromocoesFragment.newInstance()
        carrinhoFragment = CarrinhoFragment.newInstance()

        LanchesPresenter(listaDeLanches, listaDeIngredientes, lancheFragment)
        PromocoesPresenter(repository, promocoesFragment)
        CarrinhoPresenter(listaDeLanches, listaDeIngredientes, repository, carrinhoFragment)

        adapter.addFragment(lancheFragment)
        adapter.addFragment(promocoesFragment)
        adapter.addFragment(carrinhoFragment)

        viewPager.adapter = adapter
        viewpager.offscreenPageLimit = 2
    }

    override fun getLanchesEIngredientes(listaDeIngredientes: List<Ingrediente>, listaDeLanches: List<Lanche>) {
        setupViewPager(viewpager, listaDeIngredientes, listaDeLanches)
    }

    override fun showAPIIngredienteError() {
        toast(getString(R.string.erro_api_ingrediente))
    }

    override fun showAPILancheError() {
        toast(getString(R.string.erro_api_lanche))
    }
}