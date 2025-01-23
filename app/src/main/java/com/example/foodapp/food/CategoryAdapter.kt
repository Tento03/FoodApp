package com.example.foodapp.food

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.foodapp.api.Category
import com.example.foodapp.api.Meal
import com.example.foodapp.databinding.ItemMealBinding

val CATEGORY_COMPARATOR = object : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
        // User ID serves as unique ID
        oldItem.idCategory == newItem.idCategory

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
        // Compare full contents (note: Java users should call .equals())
        oldItem == newItem
}

class CategoryAdapter(private val context: Context,private val onClickListener: OnClickListener) : PagingDataAdapter<Category, CategoryAdapter.CategoryViewHolder>(CATEGORY_COMPARATOR) {
    inner class CategoryViewHolder(var binding:ItemMealBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(category: Category){
            Glide.with(context)
                .load(category.strCategoryThumb)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imgMeal)
            binding.tvMealName.text=category.strCategory

            binding.root.setOnClickListener(){
                var position=bindingAdapterPosition
                if (position!=RecyclerView.NO_POSITION){
                    var item=getItem(position)
                    item?.let {
                        onClickListener.OnItemClickListener(category)
                    }
                }
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var binding=ItemMealBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
//        val repoItem = getItem(position)
        // Note that item may be null, ViewHolder must support binding null item as placeholder
//        holder.bind(repoItem)
        var category=getItem(position)
        category?.let {
            holder.bind(category)
        }
    }
    interface OnClickListener{
        fun OnItemClickListener(category: Category)
    }
}