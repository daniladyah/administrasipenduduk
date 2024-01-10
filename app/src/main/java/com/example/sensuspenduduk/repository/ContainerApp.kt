package com.example.sensuspenduduk.repository

import android.content.Context
import com.example.sensuspenduduk.data.DatabaseKeluarga

interface ContainerApp {
    val repositoriKeluarga: RepositoriKeluarga
}

class ContainerDataApp(private val context: Context) : ContainerApp {
    override val repositoriKeluarga: RepositoriKeluarga by lazy {
        OfflineRepository(DatabaseKeluarga.getDatabase(context).keluargaDao())
    }

}