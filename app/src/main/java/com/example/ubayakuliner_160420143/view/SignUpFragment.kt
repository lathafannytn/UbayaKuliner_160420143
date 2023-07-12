package com.example.ubayakuliner_160420143.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.ubayakuliner_160420143.R
import com.example.ubayakuliner_160420143.model.UserEntity
import com.example.ubayakuliner_160420143.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_in.txtEmail
import kotlinx.android.synthetic.main.fragment_sign_in.txtPassword
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_sign_up, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        btnSignUp.setOnClickListener {
            val email = txtEmail.text.toString()
            val password = txtPassword.text.toString()
            val name = txtUsername.text.toString()
            val user = UserEntity(0, email, password, name)
            userViewModel.insertUser(user)
        }
    }
}
