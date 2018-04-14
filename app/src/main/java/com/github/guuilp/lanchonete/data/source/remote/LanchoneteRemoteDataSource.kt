package com.github.guuilp.lanchonete.data.source.remote

import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.ItemPedido
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.data.Promocao
import com.github.guuilp.lanchonete.data.source.LanchoneteDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LanchoneteRemoteDataSource : LanchoneteDataSource{

    override fun listaDeLanches(callback: LanchoneteDataSource.ListaDeLanchesCallback) {
        val call = RetrofitInitializer().lanchoneteService().listaDeLanches()

        call.enqueue(object: Callback<List<Lanche>> {
            override fun onResponse(call: Call<List<Lanche>>, response: Response<List<Lanche>>) {
                response.body()?.let{
                    callback.onListaDeLanchesSuccess(it)
                }
            }

            override fun onFailure(call: Call<List<Lanche>>, t: Throwable?) {
                callback.onListaDeLanchesError()
            }
        })
    }

    override fun listaDeIngredientes(callback: LanchoneteDataSource.ListaDeIngredientesCallback) {
        val call = RetrofitInitializer().lanchoneteService().listaDeIngredientes()

        call.enqueue(object: Callback<List<Ingrediente>> {
            override fun onResponse(call: Call<List<Ingrediente>>, response: Response<List<Ingrediente>>) {
                response.body()?.let{
                    callback.onListaDeIngredientesSuccess(it)
                }
            }

            override fun onFailure(call: Call<List<Ingrediente>>, t: Throwable?) {
                callback.onListaDeIngredientesError()
            }
        })
    }

    override fun listaPedido(callback: LanchoneteDataSource.ListaPedidoCallback) {
        val call = RetrofitInitializer().lanchoneteService().listaPedido()

        call.enqueue(object: Callback<List<ItemPedido>> {
            override fun onResponse(call: Call<List<ItemPedido>>, response: Response<List<ItemPedido>>) {
                response.body()?.let{
                    callback.onListaPedidoSuccess(it)
                }
            }

            override fun onFailure(call: Call<List<ItemPedido>>, t: Throwable?) {
                callback.onListaPedidoError()
            }
        })
    }

    override fun listaPromocoes(callback: LanchoneteDataSource.ListaPromocoesCallback) {
        val call = RetrofitInitializer().lanchoneteService().listaPromocoes()

        call.enqueue(object: Callback<List<Promocao>> {
            override fun onResponse(call: Call<List<Promocao>>, response: Response<List<Promocao>>) {
                response.body()?.let{
                    callback.onListaPromocoesSuccess(it)
                }
            }

            override fun onFailure(call: Call<List<Promocao>>, t: Throwable?) {
                callback.onListaPromocoesError()
            }
        })
    }

    override fun listaIngredientesDoLanche(idLanche: Int, callback: LanchoneteDataSource.ListaIngredientesDoLancheCallback) {
        val call = RetrofitInitializer().lanchoneteService().listaIngredientesDoLanche(idLanche)

        call.enqueue(object: Callback<List<Ingrediente>> {
            override fun onResponse(call: Call<List<Ingrediente>>, response: Response<List<Ingrediente>>) {
                response.body()?.let{
                    callback.onListaIngredientesDoLancheSuccess(it)
                }
            }

            override fun onFailure(call: Call<List<Ingrediente>>, t: Throwable?) {
                callback.onListaIngredientesDoLancheError()
            }
        })
    }

    override fun lanche(idLanche: Int, callback: LanchoneteDataSource.LancheCallback) {
        val call = RetrofitInitializer().lanchoneteService().lanche(idLanche)

        call.enqueue(object: Callback<Lanche> {
            override fun onResponse(call: Call<Lanche>, response: Response<Lanche>) {
                response.body()?.let{
                    callback.onLancheSuccess(it)
                }
            }

            override fun onFailure(call: Call<Lanche>, t: Throwable?) {
                callback.onLancheError()
            }
        })
    }

    override fun adicionarLancheAoPedido(idLanche: Int, callback: LanchoneteDataSource.AdicionarLancheAoPedidoCallback) {
        val call = RetrofitInitializer().lanchoneteService().adicionarLancheAoPedido(idLanche)

        call.enqueue(object: Callback<ItemPedido> {
            override fun onResponse(call: Call<ItemPedido>, response: Response<ItemPedido>) {
                response.body()?.let{
                    callback.onAdicionarLancheAoPedidoSuccess(it)
                }
            }

            override fun onFailure(call: Call<ItemPedido>, t: Throwable?) {
                callback.onAdicionarLancheAoPedidoError()
            }
        })
    }

    override fun adicionarLanchPersonalizadoeAoPedido(idLanche: Int, idIngredientes: List<Int>, callback: LanchoneteDataSource.AdicionarLancheAoPedidoCallback) {
        val call = RetrofitInitializer().lanchoneteService().adicionarLanchePersonalizadoAoPedido(idLanche, idIngredientes)

        call.enqueue(object: Callback<ItemPedido> {
            override fun onResponse(call: Call<ItemPedido>, response: Response<ItemPedido>) {
                response.body()?.let{
                    callback.onAdicionarLancheAoPedidoSuccess(it)
                }
            }

            override fun onFailure(call: Call<ItemPedido>, t: Throwable?) {
                callback.onAdicionarLancheAoPedidoError()
            }
        })
    }
}