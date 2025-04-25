package com.example.sweet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet.Dao.Recipe
import com.example.sweet.databinding.FragmentColdCategoryBinding
import com.example.sweet.databinding.FragmentSearchBinding


class ColdCategoryFragment : Fragment() {

    private lateinit var binding: FragmentColdCategoryBinding
    private lateinit var coldCategoryAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentColdCategoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // יצירת האדפטור של המתכונים
        coldCategoryAdapter= RecipeAdapter(emptyList()){recipe ->
            // כאן יכול להיות טיפול בלחיצה על פריט
            Log.d("ColdCategoryFragment", "Recipe clicked: ${recipe.name}")
        }

        // קישור ה-RecyclerView לאדפטור
        binding.rvColdCategoryFragment.layoutManager=LinearLayoutManager(context)
        binding.rvColdCategoryFragment.adapter=coldCategoryAdapter

        // שליפת המתכונים מ-Firebase
        RecipeRepository.getRecipes(
            onSuccess = {recipes: List<Recipe>->
                coldCategoryAdapter.submitList(recipes)
            },
            onFailure = {exception->
                Log.e("SearchFragment", "Error getting recipes: ${exception.message}", exception)
            })

    }
}