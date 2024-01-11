package com.example.sensuspenduduk.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensuspenduduk.repository.RepositoriKeluarga
import com.example.sensuspenduduk.ui.halaman.DestinasiEdit
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoriKeluarga: RepositoriKeluarga
) : ViewModel(){
    var keluargaUiState by mutableStateOf(UIStateKeluarga())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[DestinasiEdit.kkIdArg])

    init {
        viewModelScope.launch {
            keluargaUiState = repositoriKeluarga.getKeluargaStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateKeluarga(true)
        }
    }

    suspend fun updateKeluarga() {
        if (validasiInput(keluargaUiState.detailKeluarga)) {
            repositoriKeluarga.updateKeluarga(keluargaUiState.detailKeluarga.toKeluarga())
        }
        else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailKeluarga: DetailKeluarga) {
        keluargaUiState =
            UIStateKeluarga(detailKeluarga = detailKeluarga, isEntryValid = validasiInput(detailKeluarga))
    }

    private fun validasiInput(uiState: DetailKeluarga = keluargaUiState.detailKeluarga ): Boolean {
        return with(uiState) {
            nomorKK.isNotBlank() && alamat.isNotBlank() && rw.isNotBlank()
        }
    }
}
