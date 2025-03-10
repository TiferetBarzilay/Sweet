package com.example.sweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sweet.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding=FragmentLogInBinding.inflate(inflater, container, false)

        return inflater.inflate(R.layout.fragment_fur_category, container, false)

    }
}