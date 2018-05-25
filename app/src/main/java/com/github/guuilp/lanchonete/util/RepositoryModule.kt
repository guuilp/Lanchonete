package com.github.guuilp.lanchonete.util

import com.github.guuilp.lanchonete.data.source.LanchoneteDataSource
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository
import com.github.guuilp.lanchonete.data.source.remote.LanchoneteRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesLanchoneteRemoteDataSource(): LanchoneteDataSource{
        return LanchoneteRemoteDataSource
    }

    @Provides
    fun providesLanchoneteRepository(dataSource: LanchoneteDataSource): LanchoneteRepository{
        return LanchoneteRepository(dataSource)
    }

}