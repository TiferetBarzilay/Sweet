package com.example.sweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sweet.databinding.FragmentCategoriesBinding
import com.example.sweet.databinding.FragmentLogInBinding

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentCategoriesBinding.inflate(inflater,container,false)


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val milkyCategoryButton=binding.btnMilkyCategoriesFragment
        milkyCategoryButton.setOnClickListener{

            findNavController().navigate(R.id.action_categoriesFragment_to_milkyCategoryFragment)
        }

        val furCategoryButton=binding.btnFurCategoriesFragment
        furCategoryButton.setOnClickListener{

            findNavController().navigate(R.id.action_categoriesFragment_to_furCategoryFragment)
        }

        val coldCategoryButton=binding.btnColdCategoriesFragment
        coldCategoryButton.setOnClickListener {

            findNavController().navigate(R.id.action_categoriesFragment_to_coldCategoryFragment)
        }

        val bakedCategory=binding.btnBakedCategoriesFragment
        bakedCategory.setOnClickListener {

            findNavController().navigate(R.id.action_categoriesFragment_to_bakedCategoriesFragment)
        }
    }


}