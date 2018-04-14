package com.github.guuilp.lanchonete.data.source.remote

import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.ItemPedido
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.data.Promocao
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface LanchoneteService {
    @GET("lanche")
    fun listaDeLanches(): Call<List<Lanche>>

    @GET("ingrediente")
    fun listaDeIngredientes(): Call<List<Ingrediente>>

    @GET("pedido")
    fun listaPedido(): Call<List<ItemPedido>>

    @GET("promocao")
    fun listaPromocoes(): Call<List<Promocao>>

    @GET("ingrediente/de/{idLanche}")
    fun listaIngredientesDoLanche(@Path("idLanche") idLanche: Int): Call<List<Ingrediente>>

    @GET("lanche/{idLanche}")
    fun lanche(@Path("idLanche") idLanche: Int): Call<Lanche>

    @PUT("pedido/{idLanche}")
    fun adicionarLancheAoPedido(@Path("idLanche") idLanche: Int): Call<ItemPedido>

    @PUT("pedido/{idLanche}")
    fun adicionarLanchePersonalizadoAoPedido(@Path("idLanche") idLanche: Int, @Field("extras") idIngredientes: List<Int>): Call<ItemPedido>
}