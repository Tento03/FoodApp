package com.example.foodapp.module

import android.content.Context
import androidx.room.Room
import com.example.foodapp.api.FoodApi
import com.example.foodapp.favorit.FavoriteDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesRetrofit():Retrofit=Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providesFoodApi(retrofit: Retrofit)=retrofit.create(FoodApi::class.java)

    @Singleton
    @Provides
    fun providesFavoriteDB(@ApplicationContext app:Context)=Room.databaseBuilder(
        app,FavoriteDB::class.java,"makanankamyu"
    ).build()

    @Singleton
    @Provides
    fun providesFavoriteDao(db: FavoriteDB)=db.favoriteDao()
}