package com.example.sweet

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.sweet.databinding.FragmentEditPersonalAreaBinding


class EditPersonalAreaFragment : Fragment() {

    private lateinit var binding: FragmentEditPersonalAreaBinding
    private var imageUri: Uri? = null

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageUri = it
            Glide.with(this)
                .load(it)
                .into(binding.civUserPhotoEditPersonalAreaFragment)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentEditPersonalAreaBinding.inflate(inflater,container,false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveButton=binding.btnSaveEditPersonalAreaFragment
        saveButton.setOnClickListener {
            val name = binding.etNameEditPersonalAreaFragment.text.toString()

            val bundle = Bundle().apply {
                putString("name", name)
                putString("imageUri", imageUri?.toString())
            }

            Navigation.findNavController(view).previousBackStackEntry?.savedStateHandle?.set("data", bundle)
            Navigation.findNavController(view).popBackStack()
        }

        val addPhotoButton=binding.btnPlusEditPersonalAreaFragment
        addPhotoButton.setOnClickListener {
            getContent.launch("image/*")
        }


    }

}