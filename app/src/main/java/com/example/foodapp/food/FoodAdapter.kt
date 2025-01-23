package com.example.foodapp.food

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.foodapp.api.Meal
import com.example.foodapp.databinding.ItemMealBinding

val FOOD_COMPARATOR = object : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        // User ID serves as unique ID
        oldItem.idMeal == newItem.idMeal

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        // Compare full contents (note: Java users should call .equals())
        oldItem == newItem
}

class MealAdapter(private val context: Context,private val onClickListener: OnClickListener) : PagingDataAdapter<Meal, MealAdapter.MealViewHolder>(FOOD_COMPARATOR) {
    inner class MealViewHolder(var binding:ItemMealBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(meal: Meal){
            Glide.with(context)
                .load(meal.strMealThumb)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imgMeal)
            binding.tvMealName.text=meal.strMeal

            binding.root.setOnClickListener(){
                var position=bindingAdapterPosition
                if (position!=RecyclerView.NO_POSITION){
                    var item=getItem(position)
                    item?.let {
                        onClickListener.OnItemClickListener(meal)
                    }
                }
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        var binding=ItemMealBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
//        val repoItem = getItem(position)
        // Note that item may be null, ViewHolder must support binding null item as placeholder
//        holder.bind(repoItem)
        var meal=getItem(position)
        meal?.let {
            holder.bind(meal)
        }
    }
    interface OnClickListener{
        fun OnItemClickListener(meal: Meal)
    }
}