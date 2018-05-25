package com.github.guuilp.lanchonete.data.source

import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.ItemPedido
import com.github.guuilp.lanchonete.data.Lanche


class LanchoneteRepository(val lanchoneteRemoteDataSource: LanchoneteDataSource): LanchoneteDataSource{

    override fun listaDeLanches(callback: LanchoneteDataSource.ListaDeLanchesCallback) {
        lanchoneteRemoteDataSource.listaDeLanches(callback)
    }

    override fun listaDeIngredientes(callback: LanchoneteDataSource.ListaDeIngredientesCallback) {
        lanchoneteRemoteDataSource.listaDeIngredientes(callback)
    }

    override fun listaPedido(callback: LanchoneteDataSource.ListaPedidoCallback) {
        lanchoneteRemoteDataSource.listaPedido(callback)
    }

    override fun listaPromocoes(callback: LanchoneteDataSource.ListaPromocoesCallback) {
        lanchoneteRemoteDataSource.listaPromocoes(callback)
    }

    override fun listaIngredientesDoLanche(idLanche: Int, callback: LanchoneteDataSource.ListaIngredientesDoLancheCallback) {
        lanchoneteRemoteDataSource.listaIngredientesDoLanche(idLanche, callback)
    }

    override fun lanche(idLanche: Int, callback: LanchoneteDataSource.LancheCallback) {
        lanchoneteRemoteDataSource.lanche(idLanche, callback)
    }

    override fun adicionarLancheAoPedido(idLanche: Int, callback: LanchoneteDataSource.AdicionarLancheAoPedidoCallback) {
        lanchoneteRemoteDataSource.adicionarLancheAoPedido(idLanche, callback)
    }

    override fun adicionarLanchPersonalizadoeAoPedido(idLanche: Int, idIngredientes: List<Int>, callback: LanchoneteDataSource.AdicionarLancheAoPedidoCallback) {
        lanchoneteRemoteDataSource.adicionarLanchPersonalizadoeAoPedido(idLanche, idIngredientes, callback)
    }

    fun converterListaDePedidosEmListaDeLanche(listaPedido: List<ItemPedido>, listaCompletaDeLanches: List<Lanche>): List<Lanche>{
        val listaDeLancheDoPedido = mutableListOf<Lanche>()

        listaPedido.forEach { itemPedido ->
            listaDeLancheDoPedido.add(listaCompletaDeLanches.single { it.id == itemPedido.id })
        }

        return listaDeLancheDoPedido
    }

    fun calcularPrecoDoPedido(lanchesDoPedido: List<Lanche>): String{
        val precoFinal = lanchesDoPedido.sumByDouble {
            it.price
        }

        return String.format("%.2f", precoFinal)
    }

    fun atualizaListaComPrecoEIngrediente(listaDeLanches: List<Lanche>, listaDeIngredientes: List<Ingrediente>): List<Lanche>{

        listaDeLanches.forEach { lanche ->
            val listaIngredientesFiltrada = filtrarListaDeIngredientes(lanche, listaDeIngredientes)

            val preco = calcularPrecoDoLanche(lanche, listaDeIngredientes)

            lanche.price = preco

            lanche.priceFormated = "R$ " + String.format("%.2f", preco)

            lanche.ingredientsString = listaIngredientesFiltrada.joinToString(", ") {
                it.name.toString()
            }
        }

        return listaDeLanches
    }

    fun atualizaLancheComPrecoEIngrediente(lanche: Lanche, listaDeIngredientes: List<Ingrediente>): Lanche{

        val listaIngredientesFiltrada = filtrarListaDeIngredientes(lanche, listaDeIngredientes)

        val preco = calcularPrecoDoLanche(lanche, listaDeIngredientes)

        lanche.price = preco

        lanche.priceFormated = "R$ " + String.format("%.2f", preco)

        lanche.ingredientsString = listaIngredientesFiltrada.joinToString(", ") {
            it.name.toString()
        }

        return lanche
    }

    fun calcularPrecoDoLanche(lanche: Lanche, listaDeIngredientes: List<Ingrediente>): Double{
        val listaIngredientesFiltrada = filtrarListaDeIngredientes(lanche, listaDeIngredientes)

        var precoDoLanche = listaIngredientesFiltrada.sumByDouble { it.price }

        //Promoção 1
        val contemAlface = lanche.ingredients.contains(1)
        val contemBacon = lanche.ingredients.contains(2)
        if(contemAlface && !contemBacon) precoDoLanche -= (precoDoLanche * 0.10)

        val quantidadeDeCarne = lanche.ingredients.filter { it == 3 }

        //Promoção 2
        if(quantidadeDeCarne.size > 2) {
            val hamburguerDeCarne = listaDeIngredientes.single { it.id == 3 }

            val qtdHamburguerNaoPagara = quantidadeDeCarne.size.div(3).toInt()

            precoDoLanche -= qtdHamburguerNaoPagara * hamburguerDeCarne.price
        }

        val quantidadeDeQueijo = lanche.ingredients.filter { it == 5 }

        //Promoção 3
        if(quantidadeDeQueijo.size > 2) {
            val queijo = listaDeIngredientes.single { it.id == 5 }

            val qtdQueijoNaoPagara = quantidadeDeQueijo.size.div(3).toInt()

            precoDoLanche -= qtdQueijoNaoPagara * queijo.price
        }

        return precoDoLanche
    }

    fun filtrarListaDeIngredientes(lanche: Lanche, listaDeIngredientes: List<Ingrediente>): List<Ingrediente>{
        val listaIngredientesFiltrada = mutableListOf<Ingrediente>()

        lanche.ingredients.forEach { idIngrediente ->
            listaIngredientesFiltrada.addAll(listaDeIngredientes.filter { ingrediente ->
                ingrediente.id == idIngrediente
            })
        }

        return listaIngredientesFiltrada
    }

//    companion object {
//
//        private var INSTANCE: LanchoneteRepository? = null
//
//        /**
//         * Returns the single instance of this class, creating it if necessary.
//
//         * @param tasksRemoteDataSource the backend data source
//         * *
//         * @param tasksLocalDataSource  the device storage data source
//         * *
//         * @return the [TasksRepository] instance
//         */
//        @JvmStatic fun getInstance(tasksRemoteDataSource: LanchoneteDataSource): LanchoneteRepository {
//            return INSTANCE ?: LanchoneteRepository(tasksRemoteDataSource)
//                    .apply { INSTANCE = this }
//        }
//
//        /**
//         * Used to force [getInstance] to create a new instance
//         * next time it's called.
//         */
//        @JvmStatic fun destroyInstance() {
//            INSTANCE = null
//        }
//    }

}