package com.example.foodapp.food

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import com.example.foodapp.api.FoodApi
import com.example.foodapp.api.Meal
import retrofit2.HttpException
import retrofit2.http.Query
import java.io.IOException

/**
 * Sample page-keyed PagingSource, which uses Int page number to load pages.
 *
 * Loads Items from network requests via Retrofit to a backend service.
 *
 * Note that the key type is Int, since we're using page number to load a page.
 */
class FoodPagingSource(
    val foodApi: FoodApi,
    var query: String
) : PagingSource<Int, Meal>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Meal> {

        // Retrofit calls that return the body type throw either IOException for network
        // failures, or HttpException for any non-2xx HTTP status codes. This code reports all
        // errors to the UI, but you can inspect/wrap the exceptions to provide more context.
        return try {
            // Key may be null during a refresh, if no explicit key is passed into Pager
            // construction. Use 0 as default, because our API is indexed started at index 0
            val pageNumber = params.key ?: 0

            // Suspending network load via Retrofit. This doesn't need to be wrapped in a
            // withContext(Dispatcher.IO) { ... } block since Retrofit's Coroutine
            // CallAdapter dispatches on a worker thread.
            val response = if (query.isNotEmpty()==true){
                foodApi.searchFood(query)
            }
            else{
                foodApi.randomFood()
            }

            // Since 0 is the lowest page number, return null to signify no more pages should
            // be loaded before it.
            val prevKey = if (pageNumber > 1) pageNumber - 1 else null

            // This API defines that it's out of data when a page returns empty. When out of
            // data, we return `null` to signify no more pages should be loaded
            val nextKey = if (response.meals.isNotEmpty()) pageNumber + 1 else null
            LoadResult.Page(
                data = response.meals,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Meal>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}