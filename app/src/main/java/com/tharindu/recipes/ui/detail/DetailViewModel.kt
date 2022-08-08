package com.tharindu.recipes.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tharindu.recipes.domain.entity.RecipeDomain
import com.tharindu.recipes.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    val recipeDomain: MutableLiveData<RecipeDomain?>
        get() = _data
    private val _data = MutableLiveData<RecipeDomain?>()

    val timer: MutableLiveData<String?>
        get() = _timer
    private val _timer = MutableLiveData<String?>()

    val ingredients: MutableLiveData<String?>
        get() = _ingredients
    private val _ingredients = MutableLiveData<String?>()

    val steps: MutableLiveData<String?>
        get() = _steps
    private val _steps = MutableLiveData<String?>()

    private val _onAppBackPressed = SingleLiveEvent<Unit>()
    val onAppBackPressed: SingleLiveEvent<Unit>
        get() = _onAppBackPressed

    fun backButton() = _onAppBackPressed.call()

    fun updateRecipe(recipeDomain: RecipeDomain) = _data.postValue(recipeDomain)

    fun prepareData() {
        _timer.value = _data.value?.timers?.sum().toString()
        _ingredients.value = prepareIngredients()
        _steps.value = prepareSteps()
    }

    private fun prepareIngredients(): String {
        var ingredients = ""
        _data.value?.ingredients?.forEach {
            ingredients += it.name + " - " + it.quantity + "\n"
        }
        return ingredients
    }

    private fun prepareSteps(): String {
        var steps = ""
        _data.value?.steps?.forEach {
            steps += it + "\n"
        }
        return steps
    }

}