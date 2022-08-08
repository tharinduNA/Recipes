package com.tharindu.recipes.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeDomain(
    val name: String?,
    val ingredients: List<IngredientsDomain>,
    val steps: List<String?>,
    val timers: List<Int>,
    val imageUrl: String?,
    val originalUrl: String?
) : Parcelable

@Parcelize
data class IngredientsDomain(
    val quantity: String?,
    val name: String?,
    val type: String?
) : Parcelable