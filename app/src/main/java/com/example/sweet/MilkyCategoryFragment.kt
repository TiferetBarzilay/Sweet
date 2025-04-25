package com.example.sweet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet.Dao.Recipe
import com.example.sweet.databinding.FragmentMilkyCategoryBinding


class MilkyCategoryFragment : Fragment() {
    private lateinit var binding: FragmentMilkyCategoryBinding
    private lateinit var milkyCategoryAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMilkyCategoryBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // יצירת האדפטור של המתכונים
        milkyCategoryAdapter= RecipeAdapter(emptyList()){recipe ->
            // כאן יכול להיות טיפול בלחיצה על פריט
            Log.d("ColdCategoryFragment", "Recipe clicked: ${recipe.name}")
        }

        // קישור ה-RecyclerView לאדפטור
        binding.rvMilkyCategoryFragment.layoutManager=LinearLayoutManager(context)
        binding.rvMilkyCategoryFragment.adapter=milkyCategoryAdapter

        // שליפת המתכונים מ-Firebase
        RecipeRepository.getRecipes(
            onSuccess = {recipes: List<Recipe>->
                milkyCategoryAdapter.submitList(recipes)
            },
            onFailure = {exception->
                Log.e("SearchFragment", "Error getting recipes: ${exception.message}", exception)
            })


    }
}