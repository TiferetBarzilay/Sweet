package com.example.sweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class HomeFragment : Fragment() {

    private var buttonProfile: Button?=null
    private var buttonRestAPI: Button?=null
    private var buttonCategories: Button?=null
    private var buttonHome: Button?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        buttonProfile=view?.findViewById(R.id.bottom_bar_profile_button)
        buttonRestAPI=view?.findViewById(R.id.bottom_bar_restAPI_button)
        buttonCategories=view?.findViewById(R.id.bottom_bar_categories_button)
        buttonHome=view?.findViewById(R.id.bottom_bar_home_button)


        buttonProfile?.setOnClickListener{

        }


        return inflater.inflate(R.layout.fragment_home, container, false)

    }




}
