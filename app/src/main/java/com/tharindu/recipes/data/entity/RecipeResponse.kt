package com.tharindu.recipes.data.entity

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    val name: String,
    val ingredients: List<IngredientsResponse>,
    val steps: List<String>,
    val timers: List<Int>,
    @SerializedName("imageURL")
    val imageUrl: String,
    @SerializedName("originalURL")
    val originalUrl: String
)

data class IngredientsResponse(
    val quantity: String,
    val name : String,
    val type: String
)