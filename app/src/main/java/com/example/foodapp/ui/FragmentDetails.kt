package com.example.foodapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentDetailsBinding
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.favorit.FavoriteViewModel
import com.example.foodapp.food.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FragmentDetails:Fragment(R.layout.fragment_details) {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel  by viewModels<FoodViewModel>()
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    private val args by navArgs<FragmentDetailsArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentDetailsBinding.bind(view)

        binding.imgToolbarBtnBack.setOnClickListener(){
            findNavController().navigate(R.id.action_navigation_details_to_navigation_home)
        }
        viewModel.detail(args.meal.idMeal).observe(viewLifecycleOwner) {
            Glide.with(requireContext())
                .load(args.meal.strMealThumb)
                .into(binding.imgItem)
            var ingre = args.meal
            binding.tvCategory.text = args.meal.strCategory
            binding.tvIngredients.text =
                "${ingre.strIngredient1}+${ingre.strIngredient2}+${ingre.strIngredient3}"
            binding.tvInstructions.text = args.meal.strInstructions

            binding.btnYoutube.setOnClickListener(){
                var ytUrl=args.meal.strYoutube

                if (ytUrl != null){
                    var intent=Intent(Intent.ACTION_VIEW, Uri.parse(ytUrl))

                    if (intent.resolveActivity(requireActivity().packageManager)!=null){
                        startActivity(intent)
                    }
                    else{
                        val webIntent=Intent(Intent.ACTION_VIEW,Uri.parse(ytUrl))
                        startActivity(webIntent)
                    }
                }
                else {
                    Toast.makeText(requireContext(), "URL YouTube tidak tersedia", Toast.LENGTH_SHORT).show()
                }
            }
        }
        var isChecked=false
        CoroutineScope(Dispatchers.IO).launch {
            var count=favoriteViewModel.getFavoriteId(args.meal.idMeal)
            withContext(Dispatchers.Main){
                if (count==null){
                    isChecked=false
                    binding.toggleFavorite.isChecked=false
                }
                else{
                    isChecked=true
                    binding.toggleFavorite.isChecked=true
                }
            }
        }
        binding.toggleFavorite.setOnClickListener(){
            if (isChecked==false){
                binding.toggleFavorite.isChecked=true
                favoriteViewModel.addFavorite(args.meal)
            }
            else{
                binding.toggleFavorite.isChecked=false
                favoriteViewModel.deleteFavoriteId(args.meal.idMeal)
            }
        }
    }
}