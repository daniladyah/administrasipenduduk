package com.example.sensuspenduduk

import android.app.Application
import com.example.sensuspenduduk.repository.ContainerApp
import com.example.sensuspenduduk.repository.ContainerDataApp

class AplikasiKeluarga : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}