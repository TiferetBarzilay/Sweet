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
import com.example.sweet.databinding.FragmentNewPostUploadBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.UUID


class NewPostUploadFragment : Fragment() {
    private lateinit var binding: FragmentNewPostUploadBinding
    private var imageUri: Uri? = null
    private lateinit var recipeViewModel: RecipeViewModel
    private var isImageAvailable = false


    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageUri = it
            Glide.with(this)
                .load(it)
                .into(binding.civNewPostUploadFragment)
            isImageAvailable = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewPostUploadBinding.inflate(inflater, container, false)
        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlusNewPostUploadFragment.setOnClickListener {
            getContent.launch("image/*")
        }

        binding.btnSaveNewPostUploadFragment.setOnClickListener {
            uploadRecipe()
        }

        binding.btnCancelNewPostUploadFragment.setOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }
    }

    private fun uploadRecipe() {
        val recipeName = binding.edNameNewPostUploadFragment.text.toString()
        val preparationTime = binding.etPreparationTimeNewPostUploadFragment.text.toString()
        val ingredients = binding.edIngredientsNewPostUploadFragment.text.toString()
        val instructions = binding.edInstructionsNewPostUploadFragment.text.toString()

        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        val userId = currentUserId

        if (recipeName.isBlank() || preparationTime.isBlank() || ingredients.isBlank() || instructions.isBlank()) {
            Toast.makeText(requireContext(), "אנא מלא את כל השדות", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isImageAvailable) {
            Toast.makeText(requireContext(), "נא להעלות תמונה", Toast.LENGTH_SHORT).show()
            return
        }

        if (imageUri != null) {
            RecipeRepository.uploadImageToFirebaseStorage(
                imageUri = imageUri!!,
                onSuccess = { imageDownloadUrl ->
                    saveRecipeToFirestore(recipeName, preparationTime, ingredients, instructions, imageDownloadUrl, userId)
                },
                onFailure = {
                    Toast.makeText(requireContext(), "נכשלה העלאת התמונה", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }


    private fun saveRecipeToFirestore(
        recipeName: String,
        preparationTime: String,
        ingredients: String,
        instructions: String,
        imageDownloadUrl: String,
        userId: String,
    ) {
        val newRecipe = Recipe(
            id = UUID.randomUUID().toString(),
            name = recipeName,
            ingredients = ingredients,
            instructions = instructions,
            preparationTime = preparationTime,
            photograph = imageDownloadUrl,
            userId = userId
        )

        RecipeRepository.addRecipe(
            recipe = newRecipe,
            onSuccess = {
                Toast.makeText(requireContext(), "מתכון נוסף בהצלחה!", Toast.LENGTH_SHORT).show()
                binding.civNewPostUploadFragment.setImageDrawable(null)
                imageUri = null
                isImageAvailable = false
                val bundle = Bundle().apply {
                    putParcelable("recipe", newRecipe)
                }
                findNavController().navigate(
                    R.id.postPageFragment,
                    bundle,
                    navOptions {
                        popUpTo(R.id.newPostUploadFragment) {
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