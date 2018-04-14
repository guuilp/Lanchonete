package com.github.guuilp.lanchonete.data.source


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

}