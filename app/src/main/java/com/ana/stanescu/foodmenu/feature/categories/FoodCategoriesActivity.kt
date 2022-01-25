package com.ana.stanescu.foodmenu.feature.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.ana.stanescu.foodmenu.databinding.ActivityFoodMenuBinding
import com.ana.stanescu.foodmenu.R



class FoodCategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodMenuBinding
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        drawerToggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.nav_open,
            R.string.nav_close)
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}