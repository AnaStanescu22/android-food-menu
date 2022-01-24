package com.ana.stanescu.foodmenu.presentation.categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ana.stanescu.foodmenu.domain.FoodCategory
import com.squareup.picasso.Picasso
import com.ana.stanescu.foodmenu.databinding.ItemFoodCategoryBinding as ItemFoodCategoryBinding1

class FoodCategoriesRecyclerAdapter :
    RecyclerView.Adapter<FoodCategoriesRecyclerAdapter.FoodCategoryViewHolder>() {

    private lateinit var binding: ItemFoodCategoryBinding1

    private val diffUtil = object : DiffUtil.ItemCallback<FoodCategory>() {
        override fun areContentsTheSame(p0: FoodCategory, p1: FoodCategory): Boolean {
            return p0 == p1
        }

        override fun areItemsTheSame(p0: FoodCategory, p1: FoodCategory): Boolean {
            return p0.id == p1.id
        }
    }

    private val differ = AsyncListDiffer(this, diffUtil)

    fun setFoodCategories(data: List<FoodCategory>) {
        differ.submitList(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodCategoryViewHolder {
        binding =
            ItemFoodCategoryBinding1.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        val foodCategory = differ.currentList[position]
        holder.bindFoodCategory(foodCategory)
    }

    override fun getItemCount() = differ.currentList.size

    class FoodCategoryViewHolder(private val binding: ItemFoodCategoryBinding1) :
        RecyclerView.ViewHolder(binding.root) {

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