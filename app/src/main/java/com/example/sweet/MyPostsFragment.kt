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
import com.example.sweet.Dao.Recipe
import com.example.sweet.databinding.FragmentMyPostsBinding
import com.google.firebase.auth.FirebaseAuth


class MyPostsFragment : Fragment() {
    private lateinit var binding: FragmentMyPostsBinding
    private lateinit var myPostsAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMyPostsBinding.inflate(inflater,container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // יצירת האדפטור של המתכונים
        myPostsAdapter= RecipeAdapter(emptyList()){recipe ->
            Toast.makeText(requireContext(), "נלחץ: ${recipe.name}", Toast.LENGTH_SHORT).show()
            val bundle = Bundle().apply {
                putParcelable("recipe", recipe)
            }
            findNavController().navigate(R.id.action_myPostsFragment_to_postPageFragment, bundle)

        }

        // קישור ה-RecyclerView לאדפטור
        binding.rvMyPostsFragment.layoutManager=LinearLayoutManager(context)
        binding.rvMyPostsFragment.adapter=myPostsAdapter

        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
        if (currentUserId != null) {
            RecipeRepository.getRecipesByUserId(
                userId = currentUserId,
                onSuccess = { recipes ->
                    myPostsAdapter.submitList(recipes)
                },
                onFailure = { exception ->
                    Log.e("MyPostsFragment", "Error getting recipes: ${exception.message}", exception)
                }
            )
        }

    }

    override fun onResume() {
        super.onResume()
        loadUserPosts()
    }

    private fun loadUserPosts() {
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
        if (currentUserId != null) {
            RecipeRepository.getRecipesByUserId(
                userId = currentUserId,
                onSuccess = { recipes ->
                    myPostsAdapter.submitList(recipes)
                },
                onFailure = { exception ->
                    Log.e("MyPostsFragment", "Error getting recipes: ${exception.message}", exception)
                }
            )
        }
    }

}