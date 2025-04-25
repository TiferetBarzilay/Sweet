package com.example.sweet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import androidx.core.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet.Dao.Recipe
import com.example.sweet.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSearchBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // יצירת האדפטור של המתכונים
        searchAdapter=RecipeAdapter(emptyList()){recipe->
            // כאן יכול להיות טיפול בלחיצה על פריט
            Log.d("SearchFragment", "Recipe clicked: ${recipe.name}")
        }

        // קישור ה-RecyclerView לאדפטור
        binding.rvSearchFragment.layoutManager=LinearLayoutManager(context)
        binding.rvSearchFragment.adapter=searchAdapter

        // שליפת המתכונים מ-Firebase או ממקור אחר (במקרה הזה אפשר לחפש גם לפי שם או קטגוריה)
        RecipeRepository.getRecipes(
            onSuccess = {recipes: List<Recipe>->
                searchAdapter.submitList(recipes)
            },
            onFailure = {exception->
                Log.e("SearchFragment", "Error getting recipes: ${exception.message}", exception)
            })
    }



}