package com.example.sweet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        recipeAdapter = RecipeAdapter(emptyList()) { recipe ->
            // Handle item click here
            Log.d("SearchFragment", "Recipe clicked: ${recipe.name}")
            val bundle = Bundle().apply {
                putString("recipeId", recipe.id)
            }
            findNavController().navigate(R.id.action_searchFragment_to_postFragment, bundle)
        }

        binding.rvSearchFragment.layoutManager = LinearLayoutManager(context)
        binding.rvSearchFragment.adapter = recipeAdapter

        // Observe all recipes from the ViewModel
        viewModel.allRecipes.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.submitList(recipes)
        }
    }
}