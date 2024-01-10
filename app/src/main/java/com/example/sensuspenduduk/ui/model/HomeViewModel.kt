package com.example.sensuspenduduk.ui.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensuspenduduk.data.Keluarga
import com.example.sensuspenduduk.repository.RepositoriKeluarga
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val repositoriKeluarga: RepositoriKeluarga) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState: StateFlow<HomeUiState> = repositoriKeluarga.getAllKeluargaStream().filterNotNull()
        .map { HomeUiState(listKeluarga = it.toList()) }.stateIn(
            scope = viewModelScope, started = SharingStarted.WhileSubscribed(
                TIMEOUT_MILLIS
            ), initialValue = HomeUiState()
        )

    data class HomeUiState(
        val listKeluarga: List<Keluarga> = listOf()
    )
}