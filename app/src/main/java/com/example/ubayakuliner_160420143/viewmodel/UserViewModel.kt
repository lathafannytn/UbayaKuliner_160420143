package com.example.ubayakuliner_160420143.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ubayakuliner_160420143.model.KulinerDao
import com.example.ubayakuliner_160420143.model.KulinerDatabase
import com.example.ubayakuliner_160420143.model.UserEntity
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: KulinerDao) : ViewModel() {


    fun getUserByEmail(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }

    fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    fun signUp(username: String, email: String, password: String) {
        val user = UserEntity(id = 0, username, email, password)
        viewModelScope.launch {
            userDao.insertUser(user)
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val user = userDao.getUserByEmail(email)?.takeIf { it.password == password }
            // Lakukan sesuatu dengan data pengguna yang masuk
        }
    }
}