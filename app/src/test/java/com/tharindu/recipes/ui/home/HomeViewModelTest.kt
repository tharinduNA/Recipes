package com.tharindu.recipes.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tharindu.recipes.TestCoroutineRule
import com.tharindu.recipes.domain.entity.IngredientsDomain
import com.tharindu.recipes.domain.entity.RecipeDomain
import com.tharindu.recipes.domain.repository.RecipeRepository
import com.tharindu.recipes.domain.result.Result
import com.tharindu.recipes.domain.usecase.RecipeUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: HomeViewModel

    private lateinit var recipeUseCase: RecipeUseCase

    @Mock
    private lateinit var recipeRepository: RecipeRepository

    private lateinit var recipeDomain: RecipeDomain
    private lateinit var ingredientsDomain: IngredientsDomain


    @Before
    fun setUp() {
        recipeUseCase = RecipeUseCase(recipeRepository)
        ingredientsDomain = IngredientsDomain(quantity = "quantity", name = "name", type = "type")
        recipeDomain = RecipeDomain(
            name = "name",
            ingredients = listOf(ingredientsDomain),
            steps = listOf("one", "two", "three"),
            timers = listOf(1, 3, 3),
            imageUrl = "imageUrl",
            originalUrl = "originalUrl"
        )
        viewModel = HomeViewModel(recipeUseCase)
    }

    @Test
    fun `test load data with empty list`() = runBlockingTest{
        val list = arrayListOf<RecipeDomain>()
        val flowWithEmptyList = flow<Result<List<RecipeDomain>>> { emit(
            Result.Success(list)
        ) }
        Mockito.`when`(recipeUseCase.getRecipes()).thenReturn(flowWithEmptyList)
        viewModel.loadData()
        Assert.assertEquals(0, viewModel.data.value?.size)
    }

    @Test
    fun `test load data with data`() = runBlockingTest{
        val list = arrayListOf(recipeDomain)
        val flowWithEmptyList = flow<Result<List<RecipeDomain>>> { emit(
            Result.Success(list)
        ) }
        Mockito.`when`(recipeUseCase.getRecipes()).thenReturn(flowWithEmptyList)
        viewModel.loadData()
        Assert.assertEquals(recipeDomain, viewModel.data.value?.first())
    }

}