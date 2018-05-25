package com.github.guuilp.lanchonete

import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository
import com.github.guuilp.lanchonete.data.source.remote.LanchoneteRemoteDataSource
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class LanchoneteRepositoryTest {

    private lateinit var lanchoneteRepository: LanchoneteRepository
    val ingredientes = listOf(Ingrediente(null, 1.0, "Alface", 1),
                              Ingrediente(null, 2.0, "Bacon", 2),
                              Ingrediente(null, 3.0, "Hamburguer de Carne", 3),
                              Ingrediente(null, 4.0, "Ovo", 4),
                              Ingrediente(null, 5.0, "Queijo", 5),
                              Ingrediente(null, 6.0, "Pão com Gergelim", 6))

    @Before fun setupTaskRepository(){
//        lanchoneteRepository = LanchoneteRepository.getInstance(LanchoneteRemoteDataSource)
    }

    @After fun destroyRepositoryInstance(){
//        LanchoneteRepository.destroyInstance()
    }

    @Test fun calcularPrecoSemPromocao(){
        val lanche = Lanche(ingredients = listOf(5, 2, 3, 4), id = 1)

        val precoDoLanche = lanchoneteRepository.calcularPrecoDoLanche(lanche, ingredientes)

        assertEquals(14.0, precoDoLanche, 0.01)
    }

    @Test fun calcularPrecoPromocao1(){
        val lanche = Lanche(ingredients = listOf(1, 3, 4, 5), id = 1)

        val precoDoLanche = lanchoneteRepository.calcularPrecoDoLanche(lanche, ingredientes)

        assertEquals(11.7, precoDoLanche, 0.01)
    }

    @Test fun calcularPrecoPromocao2(){
        val lancheCom3Carnes = Lanche(ingredients = listOf(5, 2, 3, 3, 3, 4), id = 1)
        val precoDoLanche3Carnes = lanchoneteRepository.calcularPrecoDoLanche(lancheCom3Carnes, ingredientes)
        assertEquals(17.0, precoDoLanche3Carnes, 0.01)

        val lancheCom6Carnes = Lanche(ingredients = listOf(5, 2, 3, 3, 3, 3, 3, 3, 4), id = 1)
        val precoDoLanche6Carnes = lanchoneteRepository.calcularPrecoDoLanche(lancheCom6Carnes, ingredientes)
        assertEquals(23.0, precoDoLanche6Carnes, 0.01)
    }

    @Test fun calcularPrecoPromocao3(){
        val lancheCom3Quejos = Lanche(ingredients = listOf(5, 5, 5, 2, 3, 4), id = 1)
        val precoDoLanche3Queijos = lanchoneteRepository.calcularPrecoDoLanche(lancheCom3Quejos, ingredientes)
        assertEquals(19.0, precoDoLanche3Queijos, 0.01)

        val lancheCom6Queijos = Lanche(ingredients = listOf(5, 5, 5, 5, 5, 5, 2, 3, 4), id = 1)
        val precoDoLanche6Queijos = lanchoneteRepository.calcularPrecoDoLanche(lancheCom6Queijos, ingredientes)
        assertEquals(29.0, precoDoLanche6Queijos, 0.01)
    }
}
