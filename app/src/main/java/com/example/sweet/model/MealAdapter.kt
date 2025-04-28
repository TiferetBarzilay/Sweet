package com.example.sweet.model


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sweet.R
import com.example.sweet.databinding.RecipeRowBinding


class MealAdapter(
    private val meals: List<Meal>,
    private val onItemClick: (Meal) -> Unit
) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = RecipeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.bind(meal)
    }


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

            Glide.with(binding.root.context)
                .load(meal.strMealThumb)
                .placeholder(R.drawable.cake) // תמונת ברירת מחדל עד שתיטען התמונה
                .into(binding.ivRecipeRow)
        }
    }
}
