package com.github.guuilp.lanchonete

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.github.guuilp.lanchonete.carrinho.CarrinhoFragment
import com.github.guuilp.lanchonete.carrinho.CarrinhoPresenter
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository
import com.github.guuilp.lanchonete.data.source.remote.LanchoneteRemoteDataSource
import com.github.guuilp.lanchonete.lanches.LanchesFragment
import com.github.guuilp.lanchonete.lanches.LanchesPresenter
import com.github.guuilp.lanchonete.promocoes.PromocoesFragment
import com.github.guuilp.lanchonete.promocoes.PromocoesPresenter
import com.github.guuilp.lanchonete.util.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

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

        setupViewPager(viewpager)
    }

    private fun setupViewPager(viewPager: ViewPager){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        lancheFragment = LanchesFragment.newInstance()
        promocoesFragment = PromocoesFragment.newInstance()
        carrinhoFragment = CarrinhoFragment.newInstance()
        adapter.addFragment(lancheFragment)
        adapter.addFragment(promocoesFragment)
        adapter.addFragment(carrinhoFragment)
        viewPager.adapter = adapter
        viewpager.offscreenPageLimit = 2

        val repository = LanchoneteRepository.getInstance(LanchoneteRemoteDataSource)

        LanchesPresenter(repository, lancheFragment)
        PromocoesPresenter(repository, promocoesFragment)
        CarrinhoPresenter(repository, carrinhoFragment)
    }
}