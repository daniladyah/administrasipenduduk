package com.example.sensuspenduduk.repository

import com.example.sensuspenduduk.data.Keluarga
import com.example.sensuspenduduk.data.KeluargaDao
import kotlinx.coroutines.flow.Flow

class OfflineRepository(private val keluargaDao: KeluargaDao) : RepositoriKeluarga {
    override fun getAllKeluargaStream(): Flow<List<Keluarga>> = keluargaDao.getAllKeluarga()

    override fun getKeluargaStream(id: Int): Flow<Keluarga?> = keluargaDao.getKeluarga(id)

    override suspend fun insertKeluarga(keluarga: Keluarga) = keluargaDao.insert(keluarga)

    override suspend fun deleteKeluarga(keluarga: Keluarga) = keluargaDao.delete(keluarga)

    override suspend fun updateKeluarga(keluarga: Keluarga) = keluargaDao.update(keluarga)
}