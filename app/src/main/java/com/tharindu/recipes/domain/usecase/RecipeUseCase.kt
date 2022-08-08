package com.tharindu.recipes.domain.usecase

import com.tharindu.recipes.domain.entity.RecipeDomain
import com.tharindu.recipes.domain.repository.RecipeRepository
import com.tharindu.recipes.domain.result.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeUseCase @Inject constructor(private val recipeRepository: RecipeRepository) {
    fun getRecipes(): Flow<Result<List<RecipeDomain>>> = recipeRepository.getRecipes()
}