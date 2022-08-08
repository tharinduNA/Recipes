package com.tharindu.recipes.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tharindu.recipes.TestCoroutineRule
import com.tharindu.recipes.domain.entity.IngredientsDomain
import com.tharindu.recipes.domain.entity.RecipeDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class DetailViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: DetailViewModel

    private lateinit var recipeDomain: RecipeDomain
    private lateinit var ingredientsDomain: IngredientsDomain

    @Mock
    lateinit var observer: Observer<Unit>


    @Before
    fun setUp() {
        ingredientsDomain = IngredientsDomain(quantity = "quantity", name = "name", type = "type")
        recipeDomain = RecipeDomain(
            name = "name",
            ingredients = listOf(ingredientsDomain),
            steps = listOf("one", "two", "three"),
            timers = listOf(1, 3, 3),
            imageUrl = "imageUrl",
            originalUrl = "originalUrl"
        )
        viewModel = DetailViewModel()
    }

    @Test
    fun `test update recipe method`() {
        viewModel.updateRecipe(recipeDomain)
        Assert.assertEquals("name", viewModel.recipeDomain.value?.name)
        Assert.assertEquals("imageUrl", viewModel.recipeDomain.value?.imageUrl)
    }

    @Test
    fun `test prepare data method`() {
        viewModel.updateRecipe(recipeDomain)
        viewModel.prepareData()
        Assert.assertEquals("7", viewModel.timer.value)
        Assert.assertEquals("name - quantity\n", viewModel.ingredients.value)
        Assert.assertEquals("one\ntwo\nthree\n", viewModel.steps.value)
    }

    @Test
    fun `test back button live data`() {
        viewModel.onAppBackPressed.observeForever(observer)
        viewModel.backButton()
        Mockito.verify(observer).onChanged(viewModel.onAppBackPressed.value)
        Mockito.verifyNoMoreInteractions(observer)
        viewModel.onAppBackPressed.observeForever(observer)
        Mockito.verifyNoMoreInteractions(observer)
    }

}