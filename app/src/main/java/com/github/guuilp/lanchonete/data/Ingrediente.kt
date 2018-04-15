package com.github.guuilp.lanchonete.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingrediente (
	val image: String? = null,
	val price: Double = 0.0,
	val name: String? = null,
	val id: Int? = null
): Parcelable