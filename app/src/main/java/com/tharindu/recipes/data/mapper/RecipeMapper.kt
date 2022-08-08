package com.tharindu.recipes.data.mapper

import com.tharindu.recipes.data.entity.IngredientsResponse
import com.tharindu.recipes.data.entity.RecipeResponse
import com.tharindu.recipes.domain.entity.IngredientsDomain
import com.tharindu.recipes.domain.entity.RecipeDomain
import javax.inject.Inject

class RecipeMapper @Inject constructor() {

    fun transformRecipe(recipeResponse: List<RecipeResponse>): List<RecipeDomain> {
        val recipeList = recipeResponse.map {
            RecipeDomain(
                name = it.name,
                ingredients = transformIngredient(it.ingredients),
                steps = it.steps,
                timers = it.timers,
                imageUrl = it.imageUrl,
                originalUrl = it.originalUrl
            )
        }
        return recipeList
    }

    private fun transformIngredient(ingredientsResponse: List<IngredientsResponse>): List<IngredientsDomain> {
        val ingredientsList = ingredientsResponse.map {
            IngredientsDomain(quantity = it.quantity,
            name = it.name,
            type = it.type)
        }
        return ingredientsList
    }

}