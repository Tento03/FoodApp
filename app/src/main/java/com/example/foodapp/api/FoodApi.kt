package com.example.foodapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    companion object{
        const val BASE_URL="https://www.themealdb.com/api/json/v1/1/"
    }

    @GET("search.php?")
    suspend fun searchFood(
        @Query("s")s:String
    ):MealResponse

    @GET("random.php")
    suspend fun randomFood():MealResponse

    @GET("categories.php")
    suspend fun categoryFood():CategoryResponse

    @GET("lookup.php")
    fun detailFood(
        @Query("i")i:String
    ):Call<MealResponse>
}