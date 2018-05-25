package com.github.guuilp.lanchonete.util

import com.github.guuilp.lanchonete.main.MainActivity
import dagger.Component

@Component(modules = [RepositoryModule::class])
interface LanchoneteComponent {

    fun inject(app: MainActivity)

}