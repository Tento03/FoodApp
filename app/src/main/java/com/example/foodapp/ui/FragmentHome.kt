package com.example.foodapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.api.Category
import com.example.foodapp.api.Meal
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.food.CategoryAdapter
import com.example.foodapp.food.FoodViewModel
import com.example.foodapp.food.MealAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHome:Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewCategory: RecyclerView
    lateinit var mealAdapter: MealAdapter
    lateinit var categoryAdapter: CategoryAdapter
    private val viewModel by viewModels<FoodViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding=FragmentHomeBinding.bind(view)

        recyclerView=binding.rvRandomMeal
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        recyclerViewCategory=binding.rvCategory
        recyclerViewCategory.layoutManager=LinearLayoutManager(requireContext())

        mealAdapter= MealAdapter(requireContext(),object :MealAdapter.OnClickListener{
            override fun OnItemClickListener(meal: Meal) {
                val meal = Meal(
                    idMeal = meal.idMeal,
                    strMeal = meal.strMeal,
                    strDrinkAlternate = meal.strDrinkAlternate ?: "",
                    strCategory = meal.strCategory,
                    strArea = meal.strArea,
                    strInstructions = meal.strInstructions ?: "",
                    strMealThumb = meal.strMealThumb ?: "",
                    strTags = meal.strTags ?: "",
                    strYoutube = meal.strYoutube ?: "",
                    strIngredient1 = meal.strIngredient1 ?: "",
                    strIngredient2 = meal.strIngredient2 ?: "",
                    strIngredient3 = meal.strIngredient3 ?: "",
                    strIngredient4 = meal.strIngredient4 ?: "",
                    strIngredient5 = meal.strIngredient5 ?: "",
                    strIngredient6 = meal.strIngredient6 ?: "",
                    strIngredient7 = meal.strIngredient7 ?: "",
                    strIngredient8 = meal.strIngredient8 ?: "",
                    strIngredient9 = meal.strIngredient9 ?: "",
                    strIngredient10 = meal.strIngredient10 ?: "",
                    strIngredient11 = meal.strIngredient11 ?: "",
                    strIngredient12 = meal.strIngredient12 ?: "",
                    strIngredient13 = meal.strIngredient13 ?: "",
                    strIngredient14 = meal.strIngredient14 ?: "",
                    strIngredient15 = meal.strIngredient15 ?: "",
                    strIngredient16 = meal.strIngredient16 ?: "",
                    strIngredient17 = meal.strIngredient17 ?: "",
                    strIngredient18 = meal.strIngredient18 ?: "",
                    strIngredient19 = meal.strIngredient19 ?: "",
                    strIngredient20 = meal.strIngredient20 ?: "",
                    strMeasure1 = meal.strMeasure1 ?: "",
                    strMeasure2 = meal.strMeasure2 ?: "",
                    strMeasure3 = meal.strMeasure3 ?: "",
                    strMeasure4 = meal.strMeasure4 ?: "",
                    strMeasure5 = meal.strMeasure5 ?: "",
                    strMeasure6 = meal.strMeasure6 ?: "",
                    strMeasure7 = meal.strMeasure7 ?: "",
                    strMeasure8 = meal.strMeasure8 ?: "",
                    strMeasure9 = meal.strMeasure9 ?: "",
                    strMeasure10 = meal.strMeasure10 ?: "",
                    strMeasure11 = meal.strMeasure11 ?: "",
                    strMeasure12 = meal.strMeasure12 ?: "",
                    strMeasure13 = meal.strMeasure13 ?: "",
                    strMeasure14 = meal.strMeasure14 ?: "",
                    strMeasure15 = meal.strMeasure15 ?: "",
                    strMeasure16 = meal.strMeasure16 ?: "",
                    strMeasure17 = meal.strMeasure17 ?: "",
                    strMeasure18 = meal.strMeasure18 ?: "",
                    strMeasure19 = meal.strMeasure19 ?: "",
                    strMeasure20 = meal.strMeasure20 ?: "",
                    strSource = meal.strSource ?: "",
                    strImageSource = meal.strImageSource ?: "",
                    strCreativeCommonsConfirmed = meal.strCreativeCommonsConfirmed ?: "",
                    dateModified = meal.dateModified ?: ""
                )

                var action=FragmentHomeDirections.actionNavigationHomeToNavigationDetails(meal)
                findNavController().navigate(action)
            }
        })
        categoryAdapter= CategoryAdapter(requireContext(),object :CategoryAdapter.OnClickListener{
            override fun OnItemClickListener(category: Category) {

            }

        })

        recyclerView.adapter=mealAdapter
        viewModel.random.observe(viewLifecycleOwner,{
            mealAdapter.submitData(viewLifecycleOwner.lifecycle,it)
        })

        recyclerViewCategory.adapter=categoryAdapter
        viewModel.category.observe(viewLifecycleOwner,{
            categoryAdapter.submitData(viewLifecycleOwner.lifecycle,it)
        })
        viewModel.meal.observe(viewLifecycleOwner,{
            mealAdapter.submitData(viewLifecycleOwner.lifecycle,it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu,menu)
        var searchItem=menu.findItem(R.id.action_search)
        var searchView=searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                recyclerView.scrollToPosition(0)
                if (query != null) {
                    viewModel.searchFood(query)
                    mealAdapter.notifyDataSetChanged()
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }
}