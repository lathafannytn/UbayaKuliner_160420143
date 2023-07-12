package com.example.ubayakuliner_160420143.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.ubayakuliner_160420143.R
import com.example.ubayakuliner_160420143.SignInFragmentDirections
import com.example.ubayakuliner_160420143.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.*


class SignInFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_sign_in, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        btnSignIn.setOnClickListener {
            val email = txtEmail.text.toString()
            val password = txtPassword.text.toString()
            val user = userViewModel.getUserByEmail(email)
            if (user != null && user.password == password) {
                // Proses login berhasil
                // Navigasi ke halaman utama aplikasi
                val action = SignInFragmentDirections.actionSignInFragmentToItemTenant()
                findNavController().navigate(action)
            } else {
                // Proses login gagal
                val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
                findNavController().navigate(action)
            }

        }
    }
}


