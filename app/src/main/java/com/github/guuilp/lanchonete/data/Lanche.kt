package com.github.guuilp.lanchonete.data

data class Lanche(
	val image: String? = null,
	val name: String? = null,
	var priceFormated: String? = null,
	val ingredients: List<Int>,
	var ingredientsString: String? = null,
	val id: Int
)
