package com.example.foodapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.api.Meal
import com.example.foodapp.databinding.FragmentFavoriteBinding
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.favorit.Favorite
import com.example.foodapp.favorit.FavoriteAdapter
import com.example.foodapp.favorit.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentFavorite:Fragment(R.layout.fragment_favorite) {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var favoriteAdapter: FavoriteAdapter
    private val viewModel by viewModels<FavoriteViewModel>()
    private val favoriteList= arrayListOf<Favorite>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentFavoriteBinding.bind(view)

        recyclerView=binding.favRecView
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        favoriteAdapter= FavoriteAdapter(favoriteList,requireContext(),object :FavoriteAdapter.OnItemClickListener{
            override fun OnItemClick(favorite: Favorite) {
                var meal= Meal(
                    idMeal=favorite.idMeal,
                    strMeal = favorite.strMeal,
                    strDrinkAlternate = favorite.strDrinkAlternate ?: "",
                    strCategory = favorite.strCategory,
                    strArea = favorite.strArea,
                    strInstructions = favorite.strInstructions ?: "",
                    strMealThumb = favorite.strMealThumb ?: "",
                    strTags = favorite.strTags ?: "",
                    strYoutube = favorite.strYoutube ?: "",
                    strIngredient1 = favorite.strIngredient1 ?: "",
                    strIngredient2 = favorite.strIngredient2 ?: "",
                    strIngredient3 = favorite.strIngredient3 ?: "",
                    strIngredient4 = favorite.strIngredient4 ?: "",
                    strIngredient5 = favorite.strIngredient5 ?: "",
                    strIngredient6 = favorite.strIngredient6 ?: "",
                    strIngredient7 = favorite.strIngredient7 ?: "",
                    strIngredient8 = favorite.strIngredient8 ?: "",
                    strIngredient9 = favorite.strIngredient9 ?: "",
                    strIngredient10 = favorite.strIngredient10 ?: "",
                    strIngredient11 = favorite.strIngredient11 ?: "",
                    strIngredient12 = favorite.strIngredient12 ?: "",
                    strIngredient13 = favorite.strIngredient13 ?: "",
                    strIngredient14 = favorite.strIngredient14 ?: "",
                    strIngredient15 = favorite.strIngredient15 ?: "",
                    strIngredient16 = favorite.strIngredient16 ?: "",
                    strIngredient17 = favorite.strIngredient17 ?: "",
                    strIngredient18 = favorite.strIngredient18 ?: "",
                    strIngredient19 = favorite.strIngredient19 ?: "",
                    strIngredient20 = favorite.strIngredient20 ?: "",
                    strMeasure1 = favorite.strMeasure1 ?: "",
                    strMeasure2 = favorite.strMeasure2 ?: "",
                    strMeasure3 = favorite.strMeasure3 ?: "",
                    strMeasure4 = favorite.strMeasure4 ?: "",
                    strMeasure5 = favorite.strMeasure5 ?: "",
                    strMeasure6 = favorite.strMeasure6 ?: "",
                    strMeasure7 = favorite.strMeasure7 ?: "",
                    strMeasure8 = favorite.strMeasure8 ?: "",
                    strMeasure9 = favorite.strMeasure9 ?: "",
                    strMeasure10 = favorite.strMeasure10 ?: "",
                    strMeasure11 = favorite.strMeasure11 ?: "",
                    strMeasure12 = favorite.strMeasure12 ?: "",
                    strMeasure13 = favorite.strMeasure13 ?: "",
                    strMeasure14 = favorite.strMeasure14 ?: "",
                    strMeasure15 = favorite.strMeasure15 ?: "",
                    strMeasure16 = favorite.strMeasure16 ?: "",
                    strMeasure17 = favorite.strMeasure17 ?: "",
                    strMeasure18 = favorite.strMeasure18 ?: "",
                    strMeasure19 = favorite.strMeasure19 ?: "",
                    strMeasure20 = favorite.strMeasure20 ?: "",
                    strSource = favorite.strSource ?: "",
                    strImageSource = favorite.strImageSource ?: "",
                    strCreativeCommonsConfirmed = favorite.strCreativeCommonsConfirmed ?: "",
                    dateModified = favorite.dateModified ?: ""
                )
                var action=FragmentFavoriteDirections.actionNavigationFavoriteToNavigationDetails(meal)
                findNavController().navigate(action)
            }
        })
        recyclerView.adapter=favoriteAdapter
        viewModel.getFavorite().observe(viewLifecycleOwner,{
            favoriteAdapter.setNewList(it)
        })
    }
}