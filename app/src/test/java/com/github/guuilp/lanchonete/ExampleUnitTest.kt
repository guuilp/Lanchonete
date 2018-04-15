package com.github.guuilp.lanchonete

import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository
import com.github.guuilp.lanchonete.data.source.remote.LanchoneteRemoteDataSource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private lateinit var lanchoneteRepository: LanchoneteRepository

    @Before fun setupTaskRepository(){
        lanchoneteRepository = LanchoneteRepository.getInstance(LanchoneteRemoteDataSource)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

//    @Test fun calculoPreco(){
//        val lanche = Lanche(null, null, null, listOf(1, 2), null, 1)
//        lanchoneteRepository.calcularPrecoDoLanche(lanche, )
//    }
}
