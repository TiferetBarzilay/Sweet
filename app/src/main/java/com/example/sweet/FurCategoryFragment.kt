package com.example.sweet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet.databinding.FragmentFurCategoryBinding


class FurCategoryFragment : Fragment() {
private lateinit var binding:FragmentFurCategoryBinding
private lateinit var furCategoryAdapter: RecipeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentFurCategoryBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // יצירת האדפטור של המתכונים
        furCategoryAdapter= RecipeAdapter(emptyList()){recipe ->
            // כאן יכול להיות טיפול בלחיצה על פריט
            Log.d("ColdCategoryFragment", "Recipe clicked: ${recipe.name}")
        }

        // קישור ה-RecyclerView לאדפטור
        binding.rvFurCategoryFragment.layoutManager=LinearLayoutManager(context)
        binding.rvFurCategoryFragment.adapter=furCategoryAdapter

        // שליפת המתכונים מ-Firebase
        RecipeRepository.getRecipes(
            onSuccess = {recipes: List<Recipe>->
               furCategoryAdapter.submitList(recipes)
            },
            onFailure = {exception->
                Log.e("SearchFragment", "Error getting recipes: ${exception.message}", exception)
            })
    }


}