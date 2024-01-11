package com.example.sensuspenduduk.ui.model

import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sensuspenduduk.AplikasiKeluarga

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(AplikasiKeluarga().container.repositoriKeluarga)
        }
    }
}
