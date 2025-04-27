package com.example.sweet

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.sweet.databinding.FragmentPersonalAreaBinding
import com.google.firebase.auth.FirebaseAuth

class PersonalAreaFragment : Fragment() {

    private lateinit var binding: FragmentPersonalAreaBinding
    val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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

        val logOutButton=binding.btnLogOutPersonalAreaFragment
        logOutButton.setOnClickListener {
            showLogOutConfirmationDialog()
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

    // זה מוודא שהשם משתמש והתמונה החדשים ישתנו באופן מיידי לאחר סיום העריכה
    override fun onResume() {
        super.onResume()
        setupUI()
    }

    private fun setupUI() {

        // שליפת הנתונים מ-SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("user_data", AppCompatActivity.MODE_PRIVATE)

        val name = sharedPreferences.getString("name", " ")
        val imageUriString = sharedPreferences.getString("imageUri", null)
        val imageUri = imageUriString?.let { Uri.parse(it) }

        // הצגת שם המשתמש
        binding.tvUserNamePersonalAreaFragment.setText(name)

        // הצגת התמונה אם קיימת
        imageUri?.let {
            Glide.with(this).load(it).into(binding.ivUserPhotoPersonalAreaFragment)
        }

    }
    private fun showLogOutConfirmationDialog() {
        // יצירת AlertDialog
        val builder = AlertDialog.Builder(requireContext())

        // הגדרת כותרת והודעה
        builder.setTitle("התנתקות")
        builder.setMessage("האם אתה בטוח שברצונך להתנתק?")

        // כפתור "כן"
        builder.setPositiveButton("כן") { dialog, which ->
            auth.signOut()
            Toast.makeText(context, "התנתקת בהצלחה", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_personalAreaFragment_to_logInFragment)        }

        // כפתור "לא"
        builder.setNegativeButton("לא") { dialog, which ->
            dialog.dismiss() // סוגר את הדיאלוג אם המשתמש לחץ "לא"
        }

        // יצירת והצגת הדיאלוג
        builder.create().show()
    }

}