package com.example.sweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.sweet.Dao.Recipe
import com.example.sweet.databinding.FragmentLogInBinding
import com.example.sweet.databinding.FragmentPostPageBinding


class PostPageFragment : Fragment() {

    private lateinit var binding: FragmentPostPageBinding
    private lateinit var recipe: Recipe


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentPostPageBinding.inflate(inflater,container,false)

        arguments?.let {
            recipe = it.getParcelable("recipe")!!
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvNamePostPageFragment.text=recipe.name
        binding.tvPreparationTimePostPageFragment.text=recipe.preparationTime
        binding.tvInstructionsPostPageFragment.text=recipe.instructions
        binding.tvIngredientsPostPageFragment.text=recipe.ingredients

        val imageUrl = recipe.photograph
        // בדוק אם ה-URL קיים וטעון אותו ב-ImageView
        imageUrl?.let {
            Glide.with(this)
                .load(it) // טוען את התמונה מ-URL של Firebase
                .into(binding.ivPostPhotoPostPageFragment) // הצגת התמונה ב-ImageView
        }

        val editButton=binding.btnEditPostDetailsPostPageFragment
        editButton.setOnClickListener{
            val bundle = Bundle().apply {
                putParcelable("recipe", recipe)
            }
            findNavController().navigate(R.id.action_postPageFragment_to_editPostFragment, bundle)
        }

        val deleteButton=binding.btnDeletePostPageFragment
        deleteButton.setOnClickListener {
            showDeletePostConfirmationDialog()

        }
    }

    private fun showDeletePostConfirmationDialog() {
        // יצירת AlertDialog
        val builder = AlertDialog.Builder(requireContext())

        // הגדרת כותרת והודעה
        builder.setTitle("מחיקה")
        builder.setMessage("האם אתה בטוח שברצונך למחוק את הפוסט?")

        // כפתור "כן"
        builder.setPositiveButton("כן") { dialog, which ->
           deletPost()
        }

        // כפתור "לא"
        builder.setNegativeButton("לא") { dialog, which ->
            dialog.dismiss() // סוגר את הדיאלוג אם המשתמש לחץ "לא"
        }

        // יצירת והצגת הדיאלוג
        builder.create().show()
    }

    private fun deletPost() {
        RecipeRepository.deleteRecipe(
            recipe,
            onSuccess = {
                Toast.makeText(requireContext(), "הפוסט נמחק בהצלחה", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            },
            onFailure = { e ->
                Toast.makeText(requireContext(), "שגיאה במחיקה: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        )    }
}

