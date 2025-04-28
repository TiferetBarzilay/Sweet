package com.example.sweet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sweet.databinding.FragmentSweetAppBinding


class SweetAppFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSweetAppBinding.inflate(inflater, container, false)

        val btnRegistration=binding.btnRegistrationSweetAppFragment
        btnRegistration.setOnClickListener{
            findNavController().navigate(R.id.action_sweetAppFragment_to_registrationFragment)
        }

        val btnLogIn=binding.btnLogInSweetAppFragment
        btnLogIn.setOnClickListener{
            Log.d("SweetAppFragment", "Navigating to LogInFragment")
            findNavController().navigate(R.id.action_sweetAppFragment_to_logInFragment)
        }

        return binding.root
    }



    }