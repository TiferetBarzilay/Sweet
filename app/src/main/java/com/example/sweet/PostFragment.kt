package com.example.sweet

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sweet.Dao.Recipe
import com.example.sweet.databinding.FragmentPostBinding


class PostFragment : Fragment() {

    private lateinit var binding:FragmentPostBinding

    private var addToFavoritesButton: ImageButton?=null
    private var isFavorite=false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostBinding.inflate(inflater, container, false)


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

        // שליפת הנתונים מ-SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("user_data", AppCompatActivity.MODE_PRIVATE)

        val imageUriString = sharedPreferences.getString("imageUri", null)
        val imageUri = imageUriString?.let { Uri.parse(it) }
        // הצגת התמונה אם קיימת
        imageUri?.let {
            Glide.with(this).load(it).into(binding.ivPostFragment)
        }

    }

    }

