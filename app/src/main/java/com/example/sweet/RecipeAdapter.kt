package com.example.sweet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweet.Dao.Recipe
import com.example.sweet.databinding.RecipeRowBinding



class RecipeAdapter(
    private var recipesList: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding=RecipeRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecipeViewHolder(binding, onItemClick)

    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe= recipesList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipesList.size


    class RecipeViewHolder(private val binding: RecipeRowBinding,
                                private val onItemClick: (Recipe) -> Unit):
       RecyclerView.ViewHolder(binding.root)
   {

       fun bind(recipe: Recipe){
           binding.tvNameRecipeRow.text=recipe.name


           Glide.with(binding.root.context)
               .load(recipe.photograph)
               .placeholder(R.drawable.cake)
               .into(binding.ivRecipeRow)

           binding.root.setOnClickListener {
               onItemClick(recipe)
           }
       }
    }

    fun submitList(newRecipes: List<Recipe>) {
        recipesList = newRecipes
        notifyDataSetChanged()
    }


}