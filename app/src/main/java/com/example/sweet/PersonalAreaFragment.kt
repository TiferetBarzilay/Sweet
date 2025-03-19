package com.example.sweet

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.sweet.databinding.FragmentPersonalAreaBinding

class PersonalAreaFragment : Fragment() {

    private lateinit var binding: FragmentPersonalAreaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

      binding=FragmentPersonalAreaBinding.inflate(inflater,container,false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()

        val editButton=binding.btnEditDetailsPersonalAreaFragment
        editButton.setOnClickListener {

            findNavController().navigate(R.id.action_personalAreaFragment_to_editPersonalAreaFragment)
        }

        val favoritesButton=binding.btnFavouritesPersonalAreaFragment
        favoritesButton.setOnClickListener {
            findNavController().navigate(R.id.action_personalAreaFragment_to_favoritesPageFragment)
        }

        val myPostsButton=binding.btnMyPostsPersonalAreaFragment
        myPostsButton.setOnClickListener {

            findNavController().navigate(R.id.action_personalAreaFragment_to_myPostsFragment)
        }

        val addPostsButton=binding.btnAddPostsPersonalAreaFragment
        addPostsButton.setOnClickListener {

            findNavController().navigate(R.id.action_personalAreaFragment_to_newPostUploadFragment)
        }
    }

    private fun setupUI() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Bundle>("data")
            ?.observe(viewLifecycleOwner) { bundle ->
                bundle?.let {
                    val name = it.getString("name")
                    val imageUriString = it.getString("imageUri")
                    val imageUri = imageUriString?.let { Uri.parse(it) }
                    onSavedClicked(name, imageUri)
                }
            }
    }

    private fun onSavedClicked(name: String?, imageUri: Uri?) {
        binding.etUserNamePersonalAreaFragment.setText(name)
        imageUri?.let {
            Glide.with(this).load(it).into(binding.ivUserPhotoPersonalAreaFragment)
        }
    }
}