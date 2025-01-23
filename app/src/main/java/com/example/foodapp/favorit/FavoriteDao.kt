package com.example.foodapp.favorit

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert
    fun addFavorite(favorite: Favorite)

    @Query("SELECT * FROM McMeal")
    fun getFavorite():LiveData<List<Favorite>>

    @Query("SELECT * FROM McMeal WHERE idMeal=:idMeal")
    fun getFavoriteId(idMeal:String):Favorite

    @Query("DELETE FROM McMeal WHERE idMeal=:idMeal")
    fun deleteFavorite(idMeal: String)
}