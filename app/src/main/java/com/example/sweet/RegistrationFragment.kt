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

            val password = binding.etPasswordRegistrationFragment.text.toString()
            val email = binding.etEmailAddressRegistrationFragment.text.toString()


            if ( password.isNotEmpty() && email.isNotEmpty()){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            Log.d("Firebase", "User signed in successfully")

                            findNavController().navigate(R.id.action_registrationFragment_to_logInFragment)
                        }
                        else {
                            Log.w("Firebase", "Registration failed", task.exception)
                        }
                    }
            }
            else {
                Toast.makeText(context, "אנא מלא את כל השדות", Toast.LENGTH_SHORT).show()

            }

        }
    }


}