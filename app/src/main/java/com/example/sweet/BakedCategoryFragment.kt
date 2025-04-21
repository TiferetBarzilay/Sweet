package com.example.sweet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet.databinding.FragmentBakedCategoryBinding
import com.example.sweet.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BakedCategoryFragment : Fragment() {

    private lateinit var binding: FragmentBakedCategoryBinding
    private lateinit var bakedCategoryAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentBakedCategoryBinding.inflate(inflater,container,false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // יצירת האדפטור של המתכונים
        bakedCategoryAdapter= RecipeAdapter(emptyList()){recipe ->
            // כאן יכול להיות טיפול בלחיצה על פריט
            Log.d("ColdCategoryFragment", "Recipe clicked: ${recipe.name}")
        }

        // קישור ה-RecyclerView לאדפטור
        binding.rvBackedCategoryFragment.layoutManager=LinearLayoutManager(context)
        binding.rvBackedCategoryFragment.adapter=bakedCategoryAdapter

        // שליפת המתכונים מ-Firebase
        RecipeRepository.getRecipes(
            onSuccess = {recipes: List<Recipe>->
                bakedCategoryAdapter.submitList(recipes)
            },
            onFailure = {exception->
                Log.e("SearchFragment", "Error getting recipes: ${exception.message}", exception)
            })

    }

}