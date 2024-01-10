package com.example.sensuspenduduk.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [Keluarga::class], version = 1, exportSchema = false)
abstract class DatabaseKeluarga : RoomDatabase() {
    abstract fun keluargaDao(): KeluargaDao

    companion object {
        @Volatile
        private var Instance: DatabaseKeluarga? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): DatabaseKeluarga {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DatabaseKeluarga::class.java, "Keluarga_database").build()
                    .also { Instance = it }
            })
        }
    }
}