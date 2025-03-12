package com.example.sweet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sweet.databinding.FragmentLogInBinding
import com.example.sweet.databinding.FragmentPostPageBinding


class PostPageFragment : Fragment() {

    private lateinit var binding: FragmentPostPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentPostPageBinding.inflate(inflater,container,false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editButton=binding.btnEditPostDetailsPostPageFragment
        editButton.setOnClickListener{
            findNavController().navigate(R.id.action_postPageFragment_to_editPostFragment)
        }
    }
}