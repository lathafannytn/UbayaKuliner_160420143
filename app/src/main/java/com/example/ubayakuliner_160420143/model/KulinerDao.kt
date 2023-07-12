package com.example.ubayakuliner_160420143.model

import androidx.room.*


@Dao
interface KulinerDao {
    @Query("SELECT * FROM users WHERE email = :email")
    fun getUserByEmail(email: String): UserEntity?

    @Insert
    fun insertUser(user: UserEntity)

    @Update
    fun updateUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)
}


