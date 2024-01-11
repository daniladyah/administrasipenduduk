package com.example.sensuspenduduk.repository

import com.example.sensuspenduduk.data.Keluarga
import kotlinx.coroutines.flow.Flow

interface RepositoriKeluarga {
    fun getAllKeluargaStream(): Flow<List<Keluarga>>
    fun getKeluargaStream(id: Int): Flow<Keluarga?>
    suspend fun insertKeluarga(keluarga: Keluarga)
    suspend fun deleteKeluarga(keluarga: Keluarga)
    suspend fun updateKeluarga(keluarga: Keluarga)
}