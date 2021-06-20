package com.ana.stanescu.foodmenu.feature.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.ana.stanescu.foodmenu.R
import com.ana.stanescu.foodmenu.model.response.FoodCategory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_food_category.view.*

class FoodCategoriesRecyclerAdapter : RecyclerView.Adapter<FoodCategoriesRecyclerAdapter.FoodCategoryViewHolder>() {

    private var foodCategories: List<FoodCategory> = emptyList()

    fun setFoodCategories(data: List<FoodCategory>) {
        this.foodCategories = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodCategoryViewHolder {
        val inflatedView = parent.inflate(R.layout.item_food_category, false)
        return FoodCategoryViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        val foodCategory = foodCategories[position]
        holder.bindFoodCategory(foodCategory)
    }

    override fun getItemCount() = foodCategories.size

    class FoodCategoryViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v

        fun bindFoodCategory(foodCategory: FoodCategory) {
            Picasso
                .get()
                .load(foodCategory.imageUrl)
                .into(view.foodImageView)
            view.titleTextView.text = foodCategory.name
            view.descriptionTextView.text = foodCategory.description
        }

    }
}


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}