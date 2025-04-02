package com.example.sweet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sweet.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


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
            // קבלת השם והסיסמא מהשדות
            val username  = binding.etNameRegistrationFragment.text.toString()
            val password = binding.etPasswordRegistrationFragment.text.toString()
            val email = binding.etEmailAddressRegistrationFragment.text.toString()


            if (username .isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()){
                // יצירת משתמש עם Firebase Authentication

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // אם ההתחברות הצליחה
                            Log.d("Firebase", "User signed in successfully")

                            // ניווט למסך הראשי לאחר התחברות מוצלחת
                            findNavController().navigate(R.id.action_registrationFragment_to_logInFragment)
                        }
                        else {
                            // הצגת הודעת שגיאה אם ההרשמה נכשלה
                            Log.w("Firebase", "Registration failed", task.exception)
                        }
                    }
            }
            else {
                // אם שדות המייל או הסיסמא ריקים
                Toast.makeText(context, "אנא מלא את כל השדות", Toast.LENGTH_SHORT).show()

            }

        }
    }


}