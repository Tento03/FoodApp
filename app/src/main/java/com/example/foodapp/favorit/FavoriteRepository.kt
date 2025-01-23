package com.example.foodapp.favorit

import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(private var dao: FavoriteDao) {
    suspend fun addFavorite(favorite: Favorite){
        return dao.addFavorite(favorite)
    }
    fun getFavorite():LiveData<List<Favorite>>{
        return dao.getFavorite()
    }
    fun getFavoriteId(idMeal:String):Favorite{
        return dao.getFavoriteId(idMeal)
    }
    suspend fun deleteFavorite(idMeal: String){
        return dao.deleteFavorite(idMeal)
    }
}