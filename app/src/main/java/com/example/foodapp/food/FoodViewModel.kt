package com.example.foodapp.food

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.foodapp.api.MealResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(private var repository: FoodRepository,
private var savedStateHandle: SavedStateHandle):ViewModel() {
    companion object{
        const val DEFAULT_QUERY="potato"
        const val CURRENT_QUERY="current"
        const val DEFAULT2_QUERY="beef"
    }
    private var currentQuery=savedStateHandle.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)
    private var currentQuery2=savedStateHandle.getLiveData(CURRENT_QUERY, DEFAULT2_QUERY)

    var meal=currentQuery.switchMap {
        repository.searchFood(it).cachedIn(viewModelScope)
    }
    var random=currentQuery.switchMap {
        repository.randomFood().cachedIn(viewModelScope)
    }
    var category=currentQuery2.switchMap {
        repository.categoryFood().cachedIn(viewModelScope)
    }
    fun detail(i:String):LiveData<MealResponse> {
        return repository.detailFood(i)
    }
    fun searchFood(s:String){
        currentQuery.value=s
        repository.searchFood(s)
    }
}