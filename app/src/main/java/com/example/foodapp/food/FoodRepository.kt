package com.example.foodapp.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.foodapp.api.Category
import com.example.foodapp.api.FoodApi
import com.example.foodapp.api.MealResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepository @Inject constructor(private var foodApi: FoodApi) {
    fun searchFood(s:String)=Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = {FoodPagingSource(foodApi,s)}
    ).liveData

    fun randomFood()=Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = {FoodPagingSource(foodApi,"")}
    ).liveData

    fun categoryFood()=Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = {CategoryPagingSource(foodApi)}
    ).liveData

    fun detailFood(i:String):LiveData<MealResponse>{
        var detail=MutableLiveData<MealResponse>()
        foodApi.detailFood(i).enqueue(object :Callback<MealResponse>{
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                if (response.isSuccessful){
                    detail.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {

            }

        })
        return detail
    }
}