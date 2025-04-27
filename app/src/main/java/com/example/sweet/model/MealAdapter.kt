package com.example.sweet.model


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweet.R
import com.example.sweet.databinding.RecipeRowBinding
import com.example.sweet.model.Meal

class MealAdapter(
    private val meals: List<Meal>,
    private val onItemClick: (Meal) -> Unit
) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    // יצירת ה-ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        // בוחרים את העיצוב של כל פריט (שורה) ב-RecyclerView
        val binding = RecipeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding, onItemClick)
    }

    // חיבור הנתונים לכל ViewHolder
    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.bind(meal)
    }

    // החזרת כמות הפריטים (רשימת המתכונים)
    override fun getItemCount(): Int = meals.size

    // ViewHolder שמחבר את הנתונים עם ה-XML
    class MealViewHolder(private val binding: RecipeRowBinding,
                         private val onItemClick: (Meal) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: Meal) {
            binding.tvNameRecipeRow.text = meal.strMeal
            binding.root.setOnClickListener {
                onItemClick(meal)
            }

            // טען את התמונה מהאינטרנט
            Glide.with(binding.root.context)
                .load(meal.strMealThumb)
                .placeholder(R.drawable.cake) // תמונת ברירת מחדל עד שתיטען התמונה
                .into(binding.ivRecipeRow)
        }
    }
}
