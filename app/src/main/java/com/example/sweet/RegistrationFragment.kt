package com.example.sweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.sweet.databinding.FragmentRegistrationBinding



class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentRegistrationBinding.inflate(inflater,container,false)

        return binding.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerButton=binding.btnRegistrationFragment
        registerButton.setOnClickListener{

            findNavController().navigate(R.id.action_registrationFragment_to_logInFragment)
        }
        }

}