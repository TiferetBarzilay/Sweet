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
import com.example.sweet.databinding.FragmentMyPostsBinding

class MyPostsFragment : Fragment() {

    private lateinit var binding: FragmentMyPostsBinding
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        recipeAdapter = RecipeAdapter(emptyList()) { recipe ->
            // Handle item click here
            Log.d("MyPostsFragment", "Recipe clicked: ${recipe.name}")
            val bundle = Bundle().apply {
                putString("recipeId", recipe.id)
            }
            findNavController().navigate(R.id.action_myPostsFragment_to_postPageFragment)
        }

        binding.rvMyPostsFragment.layoutManager = LinearLayoutManager(context)
        binding.rvMyPostsFragment.adapter = recipeAdapter

        // Observe all recipes from the ViewModel
        viewModel.allRecipes.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.submitList(recipes)
        }
    }
}