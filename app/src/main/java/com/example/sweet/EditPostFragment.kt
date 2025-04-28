package com.example.sweet

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.bumptech.glide.Glide
import com.example.sweet.Dao.Recipe
import com.example.sweet.databinding.FragmentEditPostBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.UUID

class EditPostFragment : Fragment() {
    private lateinit var binding: FragmentEditPostBinding
    private lateinit var recipe: Recipe
    private lateinit var recipeViewModel: RecipeViewModel

    private var imageUri: Uri? = null
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageUri = it
            Glide.with(this)
                .load(it)
                .into(binding.civEditPostFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditPostBinding.inflate(inflater, container, false)
        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        arguments?.let {
            recipe = it.getParcelable("recipe")!!
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etNameEditPostFragment.setText(recipe.name)
        binding.etIngredientsEditPostFragment.setText(recipe.ingredients)
        binding.etInstructionsEditPostFragment.setText(recipe.instructions)
        binding.etPreparationTimeEditPostFragment.setText(recipe.preparationTime)

        val imageUrl = recipe.photograph
        imageUrl?.let {
            Glide.with(this)
                .load(it)
                .into(binding.civEditPostFragment)
        }


        binding.btnPlusEditPostFragment.setOnClickListener {
            getContent.launch("image/*")
        }

        binding.btnSaveEditPostFragment.setOnClickListener {
            editPost()
        }
    }

    private fun editPost() {
        val  id = recipe.id
        val name = binding.etNameEditPostFragment.text.toString().trim()
        val preparationTime = binding.etPreparationTimeEditPostFragment.text.toString().trim()
        val ingredients = binding.etIngredientsEditPostFragment.text.toString().trim()
        val instructions = binding.etInstructionsEditPostFragment.text.toString().trim()

        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        val userId = currentUserId

        if (name.isEmpty() || preparationTime.isEmpty() || ingredients.isEmpty() || instructions.isEmpty()) {
            Toast.makeText(requireContext(), "נא למלא את כל השדות", Toast.LENGTH_SHORT).show()
            return
        }

        if (imageUri != null) {
            // If user selected a new image, upload it
            RecipeRepository.uploadImageToFirebaseStorage(
                imageUri = imageUri!!,
                onSuccess = { imageDownloadUrl ->
                    updateRecipeToFirestore(
                        id,
                        name,
                        preparationTime,
                        ingredients,
                        instructions,
                        imageDownloadUrl,
                        userId
                    )
                },
                onFailure = {
                    Toast.makeText(requireContext(), "נכשלה העלאת התמונה", Toast.LENGTH_SHORT)
                        .show()
                }
            )
        } else {
            // If no new image was selected, keep the existing one
            updateRecipeToFirestore(
                id,
                name,
                preparationTime,
                ingredients,
                instructions,
                recipe.photograph,
                userId
            )
        }
    }

    private fun updateRecipeToFirestore(
        recipeId:String,
        recipeName: String,
        preparationTime: String,
        ingredients: String,
        instructions: String,
        imageDownloadUrl: String,
        userId: String
    ) {

        val updatedRecipe = Recipe(
            id = recipeId,
            name = recipeName,
            ingredients = ingredients,
            instructions = instructions,
            preparationTime = preparationTime,
            photograph = imageDownloadUrl,
            userId=userId
        )

        RecipeRepository.updateRecipe(
            recipe = updatedRecipe,
            onSuccess = {
                Toast.makeText(requireContext(), "מתכון עודכן בהצלחה!", Toast.LENGTH_SHORT).show()
                binding.civEditPostFragment.setImageDrawable(null)
                imageUri = null

                // Navigate back with the updated recipe
                val bundle = Bundle().apply {
                    putParcelable("recipe", updatedRecipe)
                }
                findNavController().navigate(
                    R.id.postPageFragment,
                    bundle,
                    navOptions {
                        popUpTo(R.id.postPageFragment) {
                            inclusive = true // מוחק את הקודם מהסטאק
                        }
                    }
                )
              },

            onFailure = {
                Toast.makeText(requireContext(), "נכשלה העלאת מתכון!", Toast.LENGTH_SHORT).show()
            }
        )
    }
}