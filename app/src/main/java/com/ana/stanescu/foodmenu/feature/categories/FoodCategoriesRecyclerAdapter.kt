package com.ana.stanescu.foodmenu.feature.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ana.stanescu.foodmenu.databinding.ItemFoodCategoryBinding
import com.ana.stanescu.foodmenu.model.response.FoodCategory
import com.squareup.picasso.Picasso

class FoodCategoriesRecyclerAdapter : RecyclerView.Adapter<
        FoodCategoriesRecyclerAdapter.FoodCategoryViewHolder>() {

    private lateinit var binding: ItemFoodCategoryBinding
    private var foodCategories: List<FoodCategory> = emptyList()

    private var onItemClickListener: ((FoodCategory) -> Unit)? = null

    // This click listener is to help detect when a click is done on a FoodCategory object
    fun setItemClickListener(listener: (FoodCategory) -> Unit) {
        onItemClickListener = listener
    }

    fun setFoodCategories(data: List<FoodCategory>) {
        this.foodCategories = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemFoodCategoryBinding.inflate(layoutInflater, parent, false)
        return FoodCategoryViewHolder()
    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        val foodCategory = foodCategories[position]
        holder.bindFoodCategory(foodCategory)
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { clickEvent ->
                    clickEvent(foodCategory)
                }
            }
        }
    }

    override fun getItemCount() = foodCategories.size

    inner class FoodCategoryViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bindFoodCategory(foodCategory: FoodCategory) {
            binding.apply {
                Picasso.get().load(foodCategory.imageUrl).into(foodImageView)
                titleTextView.text = foodCategory.name
                descriptionTextView.text = foodCategory.description
            }
        }
    }
}