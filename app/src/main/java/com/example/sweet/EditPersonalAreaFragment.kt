package com.example.sweet

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
    ): View {
        binding = FragmentEditPersonalAreaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // טוען נתונים מ־SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("user_data", AppCompatActivity.MODE_PRIVATE)
        val currentName = sharedPreferences.getString("name", "")
        val imageUriString = sharedPreferences.getString("imageUri", null)

        binding.etNameEditPersonalAreaFragment.setText(currentName)

        imageUri = imageUriString?.let { Uri.parse(it) }
        imageUri?.let {
            Glide.with(this)
                .load(it)
                .into(binding.civUserPhotoEditPersonalAreaFragment)
        }

        // כפתור שמירה
        binding.btnSaveEditPersonalAreaFragment.setOnClickListener {
            val name = binding.etNameEditPersonalAreaFragment.text.toString()
            val editor = sharedPreferences.edit()

            editor.putString("name", name)
            imageUri?.let {
                editor.putString("imageUri", it.toString())
            }
            editor.apply()

            Navigation.findNavController(view).popBackStack()
        }

        // כפתור להוספת תמונה
        binding.btnPlusEditPersonalAreaFragment.setOnClickListener {
            getContent.launch("image/*")
        }
    }
}
