package com.github.guuilp.lanchonete

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository
import com.github.guuilp.lanchonete.data.source.remote.LanchoneteRemoteDataSource
import com.github.guuilp.lanchonete.lanches.LanchesFragment
import com.github.guuilp.lanchonete.lanches.LanchesPresenter
import com.github.guuilp.lanchonete.util.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private lateinit var lancheFragment: LanchesFragment

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.lanches -> {
                viewpager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.promocoes -> {
                toast("Segunda aba")
                return@OnNavigationItemSelectedListener true
            }
            R.id.carrinho -> {
                toast("Terceira aba")
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
        adapter.addFragment(lancheFragment)
        viewPager.adapter = adapter

        LanchesPresenter(LanchoneteRepository(LanchoneteRemoteDataSource), lancheFragment)
    }
}
