package com.github.guuilp.lanchonete.util

import com.github.guuilp.lanchonete.data.source.LanchoneteDataSource
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository
import com.github.guuilp.lanchonete.data.source.remote.LanchoneteRemoteDataSource
import com.github.guuilp.lanchonete.main.MainContract
import com.github.guuilp.lanchonete.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule(private var view: MainContract.View) {

    @Provides
    fun providesLanchoneteRemoteDataSource(): LanchoneteDataSource{
        return LanchoneteRemoteDataSource
    }

    @Provides
    fun providesLanchoneteRepository(dataSource: LanchoneteDataSource): LanchoneteRepository{
        return LanchoneteRepository(dataSource)
    }

    @Provides
    fun providesMainView(): MainContract.View{
        return view
    }

    @Provides
    fun providesMainPresenter(repository: LanchoneteRepository, view: MainContract.View): MainContract.Presenter{
        return MainPresenter(repository, view)
    }

}