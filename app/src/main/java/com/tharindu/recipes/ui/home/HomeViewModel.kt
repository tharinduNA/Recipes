package com.tharindu.recipes.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tharindu.recipes.domain.entity.RecipeDomain
import com.tharindu.recipes.domain.result.Result
import com.tharindu.recipes.domain.usecase.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val recipeUseCase: RecipeUseCase) : ViewModel() {

    val data: MutableLiveData<List<RecipeDomain>?>
        get() = _data
    private val _data = MutableLiveData<List<RecipeDomain>?>(emptyList())

    val showLoading: MutableLiveData<Boolean>
        get() = _showLoading
    private val _showLoading = MutableLiveData<Boolean>()

    val error: MutableLiveData<Exception>
        get() = _error
    private val _error = MutableLiveData<Exception>()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            recipeUseCase.getRecipes()
                .onStart {
                    _showLoading.postValue(true)
                 }
                .onCompletion {
                    _showLoading.postValue(false)
                }
                .collect {
                    when (it) {
                        is Result.Success -> _data.postValue(it.data)
                        else -> _error.postValue(Exception())
                    }
                }
        }
    }
}