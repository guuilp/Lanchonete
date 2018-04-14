package com.github.guuilp.lanchonete.lanches

import com.github.guuilp.lanchonete.data.Ingrediente
import com.github.guuilp.lanchonete.data.Lanche
import com.github.guuilp.lanchonete.data.source.LanchoneteDataSource
import com.github.guuilp.lanchonete.data.source.LanchoneteRepository

class LanchesPresenter(val lanchoneteRepository: LanchoneteRepository,
                       val lanchesView: LanchesContract.View) : LanchesContract.Presenter{

    init {
        lanchesView.presenter = this
    }

    override fun start() {
        loadIngredientesELanches()
    }

    fun loadIngredientesELanches(){
        lanchoneteRepository.listaDeIngredientes(object: LanchoneteDataSource.ListaDeIngredientesCallback{
            override fun onListaDeIngredientesSuccess(listaDeIngredientes: List<Ingrediente>) {
                loadLanches(listaDeIngredientes)
            }

            override fun onListaDeIngredientesError() {
                lanchesView.showError()
            }
        })
    }

    fun loadLanches(listaDeIngredientes: List<Ingrediente>){
        lanchoneteRepository.listaDeLanches(object: LanchoneteDataSource.ListaDeLanchesCallback{
            override fun onListaDeLanchesSuccess(listaDeLanches: List<Lanche>) {
                lanchesView.showLanches(atualizaListaComPrecoEIngrediente(listaDeLanches, listaDeIngredientes))
            }

            override fun onListaDeLanchesError() {
                lanchesView.showError()
            }
        })
    }

    fun atualizaListaComPrecoEIngrediente(listaDeLanches: List<Lanche>, listaDeIngredientes: List<Ingrediente>): List<Lanche>{

        listaDeLanches.forEach { lanche ->
            val listaIngredientesFiltrada = filtrarListaDeIngredientes(lanche, listaDeIngredientes)

            lanche.priceFormated = "R$ " + String.format("%.2f", calcularPrecoDoLanche(lanche, listaDeIngredientes))

            lanche.ingredientsString = listaIngredientesFiltrada.joinToString(", ") {
                it.name.toString()
            }
        }

        return listaDeLanches
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

            val qtdQueijoNaoPagara = quantidadeDeCarne.size.div(3).toInt()

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
}