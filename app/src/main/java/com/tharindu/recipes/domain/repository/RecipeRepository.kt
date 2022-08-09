package com.tharindu.recipes.domain.repository

import com.tharindu.recipes.domain.entity.RecipeDomain
import com.tharindu.recipes.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipes(): Flow<Result<List<RecipeDomain>>>
}