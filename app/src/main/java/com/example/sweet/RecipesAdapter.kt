package com.example.sweet

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet.databinding.RecipeRowBinding

class RecipesAdapter(
    private val recipesList: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {



   inner class RecipeViewHolder(private val binding: RecipeRowBinding):
       RecyclerView.ViewHolder(binding.root) {

    }

    override fun getItemCount(): Int = recipesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        TODO("Not yet implemented")
    }


    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}