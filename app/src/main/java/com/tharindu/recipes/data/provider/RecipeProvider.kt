package com.tharindu.recipes.data.provider

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tharindu.recipes.R
import com.tharindu.recipes.data.entity.RecipeResponse
import com.tharindu.recipes.data.mapper.RecipeMapper
import com.tharindu.recipes.domain.entity.RecipeDomain
import com.tharindu.recipes.domain.repository.RecipeRepository
import com.tharindu.recipes.domain.result.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class RecipeProvider @Inject constructor(
    @ApplicationContext private val context: Context,
    private val recipeMapper: RecipeMapper
) : RecipeRepository {
    override fun getRecipes(): Flow<Result<List<RecipeDomain>>> =
        flow {
            emit(
                withContext(Dispatchers.IO) {
                    getRecipeList()
                })
        }.catch {
            Result.Error(Exception(context.getString(R.string.something_went_wrong)))
        }.map { result ->
            Result.Success(recipeMapper.transformRecipe(result))
        }

    private fun getRecipeList(): List<RecipeResponse> {
        val jsonFileString = getJsonDataFromAsset(context, "recipes.json")
        val listRecipeType = object : TypeToken<List<RecipeResponse>>() {}.type
        return Gson().fromJson(jsonFileString, listRecipeType)
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}