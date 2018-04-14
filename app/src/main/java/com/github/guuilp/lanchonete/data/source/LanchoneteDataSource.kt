package com.github.guuilp.lanchonete.data.source

import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.ItemPedido
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.data.Promocao

interface LanchoneteDataSource{
    interface ListaDeLanchesCallback{
        fun onListaDeLanchesSuccess(listaDeLanches: List<Lanche>)
        fun onListaDeLanchesError()
    }

    interface ListaDeIngredientesCallback{
        fun onListaDeIngredientesSuccess(listaDeIngredientes: List<Ingrediente>)
        fun onListaDeIngredientesError()
    }

    interface ListaPedidoCallback{
        fun onListaPedidoSuccess(listaPedido: List<ItemPedido>)
        fun onListaPedidoError()
    }

    interface ListaPromocoesCallback{
        fun onListaPromocoesSuccess(listaPromocoes: List<Promocao>)
        fun onListaPromocoesError()
    }

    interface ListaIngredientesDoLancheCallback{
        fun onListaIngredientesDoLancheSuccess(listaIngredientesDoLanche: List<Ingrediente>)
        fun onListaIngredientesDoLancheError()
    }

    interface LancheCallback{
        fun onLancheSuccess(lanche: Lanche)
        fun onLancheError()
    }

    interface AdicionarLancheAoPedidoCallback{
        fun onAdicionarLancheAoPedidoSuccess(itemPedido: ItemPedido)
        fun onAdicionarLancheAoPedidoError()
    }

    fun listaDeLanches(callback: ListaDeLanchesCallback)
    fun listaDeIngredientes(callback: ListaDeIngredientesCallback)
    fun listaPedido(callback: ListaPedidoCallback)
    fun listaPromocoes(callback: ListaPromocoesCallback)
    fun listaIngredientesDoLanche(idLanche: Int, callback: ListaIngredientesDoLancheCallback)
    fun lanche(idLanche: Int, callback: LancheCallback)
    fun adicionarLancheAoPedido(idLanche: Int, callback: AdicionarLancheAoPedidoCallback)
    fun adicionarLanchPersonalizadoeAoPedido(idLanche: Int, idIngredientes: List<Int>, callback: AdicionarLancheAoPedidoCallback)
}