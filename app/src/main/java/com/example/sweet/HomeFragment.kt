package com.example.sweet

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
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

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            showLogoutDialog()
        }
        recipeAdapter = RecipeAdapter(emptyList()) { recipe ->
            // Handle item click here
            Log.d("HomeFragment", "Recipe clicked: ${recipe.name}")

            /*
                            val bundle = Bundle().apply {
                                putString("recipeId", recipe.id)  // שליחה של ה-ID של המתכון
                                }
                            // מעבר למסך המתכון
                            findNavController().navigate(R.id.action_homeFragment_to_postFragment, bundle)

             */
        }

        binding.rvHomeFragment.layoutManager = LinearLayoutManager(context)
        binding.rvHomeFragment.adapter = recipeAdapter


        RecipeRepository.getRecipes(
            onSuccess = { recipes ->
                recipeAdapter.submitList(recipes) // עדכון הרשימה במקום יצירה מחדש
            },
            onFailure = { exception ->
                Log.e("HomeFragment", "Error getting recipes: ${exception.message}", exception)
            }
        )

    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("התנתקות")
            .setMessage("האם אתה בטוח שברצונך לצאת מהאפליקציה?")
            .setPositiveButton("כן") { _, _ ->
                findNavController().navigate(R.id.action_homeFragment_to_sweetAppFragment)
            }
            .setNegativeButton("לא", null)
            .show()
    }



}
