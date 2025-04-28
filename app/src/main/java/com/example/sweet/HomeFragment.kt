package com.example.sweet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(inflater,container,false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeAdapter = RecipeAdapter(emptyList()) { recipe ->
            Toast.makeText(requireContext(), "נלחץ: ${recipe.name}", Toast.LENGTH_SHORT).show()
            val bundle = Bundle().apply {
                putParcelable("recipe", recipe)
            }
            findNavController().navigate(R.id.action_homeFragment_to_postFragment, bundle)

        }

        binding.rvHomeFragment.layoutManager = LinearLayoutManager(context)
        binding.rvHomeFragment.adapter = recipeAdapter


        RecipeRepository.getRecipes(
            onSuccess = { recipes ->
                Log.d("HomeFragment", "Fetched ${recipes.size} recipes")  // עדכון ללוג
                recipeAdapter.submitList(recipes) // עדכון הרשימה במקום יצירה מחדש
            },
            onFailure = { exception ->
                Log.e("HomeFragment", "Error getting recipes: ${exception.message}", exception)
            }
        )

    }


}
