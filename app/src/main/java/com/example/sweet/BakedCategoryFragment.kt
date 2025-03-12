package com.example.sweet

import android.os.Bundle
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentBakedCategoryBinding.inflate(inflater,container,false)


        binding.rvBackedCategoryFragment.layoutManager=LinearLayoutManager(requireContext())

        val recipeList=RecipeRepository.getRecipes()
        val adapter=RecipeAdapter(recipeList){ recipe->

            Navigation.findNavController(requireView()).navigate(R.id.action_bakedCategoriesFragment_to_postFragment)

        }


        binding.rvBackedCategoryFragment.adapter=adapter


        return binding.root


    }

}