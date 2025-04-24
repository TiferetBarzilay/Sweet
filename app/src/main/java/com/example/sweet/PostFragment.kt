package com.example.sweet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.example.sweet.databinding.FragmentPostBinding


class PostFragment : Fragment() {

    private lateinit var binding:FragmentPostBinding

    private var addToFavoritesButton: ImageButton?=null
    private var isFavorite=false
    private var recipeId: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(inflater, container, false)

        // קבלת ה-ID של המתכון מ-Bundle (אם יש)
        recipeId = arguments?.getString("recipeId")


        /*
        // קבלת ה-ID של המתכון מ-Bundle
        val recipeId = arguments?.getString("recipeId") ?: ""

        if (recipeId.isEmpty()) {
            Log.e("PostFragment", "Recipe ID is missing!")
            binding.tvNamePostFragment.text = "Unknown"
        } else {
            // שליפת המתכון מ-Firebase בעזרת ה-ID
            RecipeRepository.getRecipeById(recipeId,
                onSuccess = { recipe ->
                    // הצגת המידע של המתכון ב-UI
                    binding.tvNamePostFragment.text = recipe.name
                    binding.tvPreparationTimePostFragment.text=recipe.preparationTime
                    binding.tvIngredientsPostFragment.text=recipe.ingredients.joinToString(", ")
                    binding.tvInstructionPostFragment.text=recipe.instructions
                },
                onFailure = { exception ->
                    Log.e("PostFragment", "Error retrieving recipe: ${exception.message}", exception)
                })
                 }

             */


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(view)
    }
    private fun setupUI(view:View){

        //favorites button:
        addToFavoritesButton=view.findViewById(R.id.btnAddToFavoritesRecipesPostFragment)

        addToFavoritesButton?.setOnClickListener{
            isFavorite=!isFavorite

            if(isFavorite){
                addToFavoritesButton?.setImageResource(R.drawable.favorites_button) // כוכב מלא
            }
            else {
                addToFavoritesButton?.setImageResource(R.drawable.add_to_favorites_button)// כוכב ריק
            }
        }


    }
}
