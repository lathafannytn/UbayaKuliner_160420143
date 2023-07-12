package com.example.ubayakuliner_160420143.model

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [UserEntity::class], version = 1)
abstract class KulinerDatabase : RoomDatabase() {
    abstract fun userDao(): KulinerDao
}
