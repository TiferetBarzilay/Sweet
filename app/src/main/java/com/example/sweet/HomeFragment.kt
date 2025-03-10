package com.example.sweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweet.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding= FragmentHomeBinding.inflate(inflater,container,false)





      binding.rvHomeFragment.layoutManager = LinearLayoutManager(requireContext())
        val recipeList=RecipeRepository.getRecipes()
        val adapter=RecipeAdapter(recipeList){ recipe->

          Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_postFragment)

        }


        binding.rvHomeFragment.adapter=adapter




        return binding.root

    }




}
