package com.example.sensuspenduduk.ui.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sensuspenduduk.data.Keluarga
import com.example.sensuspenduduk.repository.RepositoriKeluarga

class EntryViewModel(private val repositoriKeluarga: RepositoriKeluarga) : ViewModel() {
    var uiStateKeluarga by mutableStateOf(UIStateKeluarga())
        private set

    private fun validasiInput(uiState: DetailKeluarga = uiStateKeluarga.detailKeluarga): Boolean {
        return with(uiState) {
            namaAyah.isNotBlank() && alamat.isNotBlank() && nomorKK.isNotBlank()
        }
    }

    fun updateUiState(detailKeluarga: DetailKeluarga) {
        uiStateKeluarga =
            UIStateKeluarga(detailKeluarga = detailKeluarga, isEntryValid = validasiInput(detailKeluarga))
    }

    suspend fun saveKeluarga() {
        if (validasiInput()) {
            repositoriKeluarga.insertKeluarga(uiStateKeluarga.detailKeluarga.toKeluarga())
        }
    }
}

data class UIStateKeluarga(
    val detailKeluarga: DetailKeluarga = DetailKeluarga(),
    val isEntryValid: Boolean = false
)

data class DetailKeluarga(
    val id: Int = 0,
    val nomorKK: String="",
    val namaAyah: String = "",
    val namaIbu: String = "",
    val alamat: String = "",
    val anak: String = "",
    val rw:String=""
)

fun DetailKeluarga.toKeluarga(): Keluarga = Keluarga(
    id = id,
    nomorKK = nomorKK,
    namaAyah = namaAyah,
    namaIbu = namaIbu,
    alamat = alamat,
    anak = anak,
    rw = rw
)
fun Keluarga.toUiStateKeluarga(isEntryValid: Boolean = false): UIStateKeluarga = UIStateKeluarga(
    detailKeluarga = this.toDetailKeluarga(),
    isEntryValid = isEntryValid
)

fun Keluarga.toDetailKeluarga(): DetailKeluarga = DetailKeluarga(
    id = id,
    nomorKK = nomorKK,
    namaAyah = namaAyah,
    namaIbu = namaIbu,
    alamat = alamat,
    anak = anak,
    rw = rw
)
