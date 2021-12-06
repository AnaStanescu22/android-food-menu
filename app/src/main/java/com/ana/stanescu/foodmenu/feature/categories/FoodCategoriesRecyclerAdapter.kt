package com.ana.stanescu.foodmenu.feature.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.ana.stanescu.foodmenu.R
import com.ana.stanescu.foodmenu.databinding.ActivityFoodMenuBinding
import com.ana.stanescu.foodmenu.databinding.ItemFoodCategoryBinding
import com.ana.stanescu.foodmenu.model.response.FoodCategory
import com.squareup.picasso.Picasso

class FoodCategoriesRecyclerAdapter : RecyclerView.Adapter<FoodCategoriesRecyclerAdapter.FoodCategoryViewHolder>() {

    private var foodCategories: List<FoodCategory> = emptyList()
    private lateinit var binding: ItemFoodCategoryBinding

    fun setFoodCategories(data: List<FoodCategory>) {
        this.foodCategories = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodCategoryViewHolder {
        binding = ItemFoodCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        val foodCategory = foodCategories[position]
        holder.bindFoodCategory(foodCategory)
    }

    override fun getItemCount() = foodCategories.size

    class FoodCategoryViewHolder(private val binding: ItemFoodCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindFoodCategory(foodCategory: FoodCategory) {
            Picasso
                .get()
                .load(foodCategory.imageUrl)
                .into(binding.foodImageView)
            binding.titleTextView.text = foodCategory.name
            binding.descriptionTextView.text = foodCategory.description
        }

    }
}


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}