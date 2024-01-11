package com.example.sensuspenduduk.ui.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.sensuspenduduk.AplikasiKeluarga

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(AplikasiKeluarga().container.repositoriKeluarga)
        }
        initializer { EntryViewModel(aplikasiKeluarga().container.repositoriKeluarga) }
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiKeluarga().container.repositoriKeluarga
            )
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiKeluarga().container.repositoriKeluarga
            )
        }

    }
}

fun CreationExtras.aplikasiKeluarga(): AplikasiKeluarga =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiKeluarga)
