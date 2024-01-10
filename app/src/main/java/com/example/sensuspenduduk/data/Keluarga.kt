package com.example.sensuspenduduk.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_keluarga")
data class Keluarga(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nomorKK: String = "",
    val namaAyah: String = "",
    val namaIbu: String = "",
    val alamat: String = "",
    val anak: String = "",
    val rw: String = ""
)
