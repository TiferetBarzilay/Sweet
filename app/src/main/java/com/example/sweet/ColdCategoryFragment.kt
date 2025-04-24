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
import com.example.sweet.databinding.FragmentColdCategoryBinding

class ColdCategoryFragment : Fragment() {

    private lateinit var binding: FragmentColdCategoryBinding
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentColdCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        recipeAdapter = RecipeAdapter(emptyList()) { recipe ->
            // Handle item click here
            Log.d("ColdCategoryFragment", "Recipe clicked: ${recipe.name}")
            val bundle = Bundle().apply {
                putString("recipeId", recipe.id)
            }
            findNavController().navigate(R.id.action_coldCategoryFragment_to_postFragment, bundle)
        }

        binding.rvColdCategoryFragment.layoutManager = LinearLayoutManager(context)
        binding.rvColdCategoryFragment.adapter = recipeAdapter

        // Observe cold recipes from the ViewModel
        viewModel.coldRecipes.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.submitList(recipes)
        }
    }
}