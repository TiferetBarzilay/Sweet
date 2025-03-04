package com.example.sweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

class PostFragment : Fragment() {

    private var addToFavorites: ImageButton?=null
    private var isFavorite=false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(view)
    }
    private fun setupUI(view:View){

        //favorites button:
        addToFavorites=view.findViewById(R.id.btnAddToFavoritesRecipesPostFragment)

        addToFavorites?.setOnClickListener{
            isFavorite=!isFavorite

            if(isFavorite){
                addToFavorites?.setImageResource(R.drawable.favorites_button) // כוכב מלא
            }
            else {
                addToFavorites?.setImageResource(R.drawable.add_to_favorites_button)// כוכב ריק
            }
        }


    }
}
