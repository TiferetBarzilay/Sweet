package com.example.sweet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweet.Dao.Recipe
import com.example.sweet.databinding.RecipeRowBinding

//RecipeAdapter מקבל שני פרמטרים:
//recipesList - רשימה של אובייקטים מסוג Recipe (מתכונים).
//onItemClick - פונקציה שתתבצע כאשר לוחצים על פריט ברשימה.

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
       //ב-bind() אתה מעדכן את רכיבי ה-UI עם המידע מתוך אובייקט ה-Recipe
       fun bind(recipe: Recipe){
           binding.tvNameRecipeRow.text=recipe.name

           // ✅ טען את התמונה מקישור ה-Imgur
           Glide.with(binding.root.context)
               .load(recipe.photograph) // הקישור ל-Imgur שנמצא בדאטה
               .placeholder(R.drawable.cake) // תמונת ברירת מחדל (תכניסי אם יש לך)
               .into(binding.ivRecipeRow)

           binding.root.setOnClickListener {
               onItemClick(recipe)
           }
       }
    }

    //מעדכנת את רשימה המתכונים כשהיא משתנה
    fun submitList(newRecipes: List<Recipe>) {
        recipesList = newRecipes
        notifyDataSetChanged() // מעדכן את ה-RecyclerView
    }


}