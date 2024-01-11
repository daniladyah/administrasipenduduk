package com.example.sensuspenduduk.ui.halaman

import com.example.sensuspenduduk.R
import com.example.sensuspenduduk.navigasi.DestinasiNavigasi

object DestinasiDetail : DestinasiNavigasi {
    override val route = "item_details"
    override val titleRes = R.string.detail_Keluarga
    const val keluargaIdArg = "itemId"
    val routeWithArgs = "$route/{$keluargaIdArg}"
}