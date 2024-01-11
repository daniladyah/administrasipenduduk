package com.example.sensuspenduduk.ui.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sensuspenduduk.repository.RepositoriKeluarga
import com.example.sensuspenduduk.ui.halaman.DestinasiDetail
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriKeluarga: RepositoriKeluarga
) : ViewModel() {
    private val keluargaId: Int = checkNotNull(savedStateHandle[DestinasiDetail.keluargaIdArg])
    val uiState: StateFlow<ItemDetailUiState> =
        repositoriKeluarga.getKeluargaStream(keluargaId).filterNotNull()
            .map { ItemDetailUiState(detailKeluarga = it.toDetailKeluarga()) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailUiState()
            )

    suspend fun deleteItem() {
        repositoriKeluarga.deleteKeluarga(uiState.value.detailKeluarga.toKeluarga())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ItemDetailUiState(
    val outOfStock: Boolean = true,
    val detailKeluarga: DetailKeluarga = DetailKeluarga()
)