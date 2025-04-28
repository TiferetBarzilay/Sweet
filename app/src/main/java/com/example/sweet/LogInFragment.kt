package com.example.sweet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sweet.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       binding=FragmentLogInBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logInButton=binding.btnLoginFragment
        logInButton.setOnClickListener{

            val email = binding.etEmailLoginFragment.text.toString()
            val password = binding.etPasswordLoginFragment.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Firebase", "User signed in successfully")

                            // ניווט למסך הראשי לאחר התחברות מוצלחת
                            Toast.makeText(requireContext(),"התחברת בהצלחה!",Toast.LENGTH_SHORT).show()

                           findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
                        }
                        else {
                            Log.w("Firebase", "Login failed", task.exception)
                        }
                    }
            }
            else {
                Toast.makeText(context, "אנא מלא את כל השדות", Toast.LENGTH_SHORT).show()
            }
        }
        val forgotPasswordButton = binding.btnForgotPasswordRegistrationFragment

        forgotPasswordButton.setOnClickListener {
            val email =  binding.etEmailLoginFragment.text.toString() // הדואר האלקטרוני שהמשתמש הכניס
            if (email.isNotEmpty()) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(), "לינק לאיפוס סיסמה נשלח למייל שלך", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(requireContext(), "שגיאה, נסה שוב", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else {
                Toast.makeText(requireContext(), "אנא הזן כתובת אימייל", Toast.LENGTH_SHORT).show()
            }
        }

    }

}