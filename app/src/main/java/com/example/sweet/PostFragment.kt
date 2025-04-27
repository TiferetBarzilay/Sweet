package com.example.sweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.sweet.Dao.Recipe
import com.example.sweet.databinding.FragmentPostBinding


class PostFragment : Fragment() {

    private lateinit var binding:FragmentPostBinding
    private lateinit var recipe: Recipe


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(inflater, container, false)

        arguments?.let {
            recipe = it.getParcelable("recipe")!!
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvNamePostFragment.text=recipe.name
        binding.tvPreparationTimePostFragment.text=recipe.preparationTime
        binding.tvInstructionPostFragment.text=recipe.instructions
        binding.tvIngredientsPostFragment.text=recipe.ingredients

        val imageUrl = recipe.photograph
        // בדוק אם ה-URL קיים וטעון אותו ב-ImageView
        imageUrl?.let {
            Glide.with(this)
                .load(it) // טוען את התמונה מ-URL של Firebase
                .into(binding.ivPostFragment) // הצגת התמונה ב-ImageView
        }
        }







    }

