package com.example.sweet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sweet.databinding.RecipeRowBinding

//RecipeAdapter מקבל שני פרמטרים:
//recipesList - רשימה של אובייקטים מסוג Recipe (מתכונים).
//onItemClick - פונקציה שתתבצע כאשר לוחצים על פריט ברשימה.

class RecipeAdapter(
    private var recipesList: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    //מעדכנת את רשימה המתכונים כשהיא משתנה
    fun submitList(newRecipes: List<Recipe>) {
        recipesList = newRecipes
        notifyDataSetChanged() // מעדכן את ה-RecyclerView
    }


   inner class RecipeViewHolder(private val binding: RecipeRowBinding):
       RecyclerView.ViewHolder(binding.root)
   {
       //ב-bind() אתה מעדכן את רכיבי ה-UI עם המידע מתוך אובייקט ה-Recipe
       fun bind(recipe: Recipe){
           binding.tvNameRecipeRow.text=recipe.name
           binding.btnAddToFavoritesRecipesPostFragment.isChecked = recipe.isFavorite

           binding.root.setOnClickListener {
               onItemClick(recipe)
           }
       }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding=RecipeRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecipeViewHolder(binding)

    }


    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipesList[position])
    }

    override fun getItemCount(): Int = recipesList.size


}