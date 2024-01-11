package com.example.sensuspenduduk.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface KeluargaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(keluarga: Keluarga)

    @Update
    suspend fun update(keluarga: Keluarga)

    @Delete
    suspend fun delete(keluarga: Keluarga)

    @Query("SELECT * from tbl_keluarga WHERE id = :id")
    fun getKeluarga(id: Int): Flow<Keluarga>

    @Query("SELECT * from tbl_keluarga ORDER BY nomorKK ASC")
    fun getAllKeluarga(): Flow<List<Keluarga>>
}