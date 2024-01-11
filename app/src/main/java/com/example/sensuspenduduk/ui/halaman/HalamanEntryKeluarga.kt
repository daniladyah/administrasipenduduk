package com.example.sensuspenduduk.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sensuspenduduk.R
import com.example.sensuspenduduk.navigasi.DestinasiNavigasi
import com.example.sensuspenduduk.ui.model.DetailKeluarga
import com.example.sensuspenduduk.ui.model.EntryViewModel
import com.example.sensuspenduduk.ui.model.PenyediaViewModel
import com.example.sensuspenduduk.ui.model.UIStateKeluarga
import kotlinx.coroutines.launch

object DestinasiEntry : DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = R.string.entry_Keluarga
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryKeluargaScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { innerPadding ->
        EntryKeluargaBody(
            uiStateKeluarga = viewModel.uiStateKeluarga,
            onKeluargaValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveKeluarga()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryKeluargaBody(
    uiStateKeluarga: UIStateKeluarga,
    onKeluargaValueChange: (DetailKeluarga) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        FormInputKeluarga(
            detailKeluarga = uiStateKeluarga.detailKeluarga,
            onValueChange = onKeluargaValueChange,
            modifier = Modifier.fillMaxWidth(),
            tempatRW = listOf("RW 01", "RW 02", "RW 03"),
            onSelectionChanged = { uiStateKeluarga.detailKeluarga.rw }
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateKeluarga.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.btn_submit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputKeluarga(
    detailKeluarga: DetailKeluarga,
    modifier: Modifier = Modifier,
    onValueChange: (DetailKeluarga) -> Unit = {},
    enabled: Boolean = true,
    tempatRW: List<String>,
    onSelectionChanged: (String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {

        OutlinedTextField(
            value = detailKeluarga.nomorKK,
            onValueChange = { onValueChange(detailKeluarga.copy(nomorKK = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(R.string.nomorKartuKeluarga)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailKeluarga.namaAyah,
            onValueChange = { onValueChange(detailKeluarga.copy(namaAyah = it)) },
            label = { Text(stringResource(R.string.ayah)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailKeluarga.namaIbu,
            onValueChange = { onValueChange(detailKeluarga.copy(namaIbu = it)) },
            label = { Text(stringResource(R.string.ibu)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailKeluarga.anak,
            onValueChange = { onValueChange(detailKeluarga.copy(anak = it)) },
            label = { Text(stringResource(R.string.anak)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailKeluarga.alamat,
            onValueChange = { onValueChange(detailKeluarga.copy(alamat = it)) },
            label = { Text(stringResource(R.string.alamat)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        Column(modifier.fillMaxWidth()) {
            tempatRW.forEach { item ->
                Row(modifier = Modifier.selectable(
                    selected = detailKeluarga.rw == item,
                    onClick = {
                        onValueChange(detailKeluarga.copy(rw = item))
                        onSelectionChanged(item)
                    }
                ),
                    verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = detailKeluarga.rw == item,
                        onClick = {
                            onValueChange(detailKeluarga.copy(rw = item))
                            onSelectionChanged(item)
                        }
                    )
                    Text(item)
                }
            }
        }
        if (enabled) {
            Text(
                text = stringResource(id = R.string.required_field),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
        Divider(
            thickness = dimensionResource(id = R.dimen.padding_small),
            modifier = Modifier.padding(
                bottom = dimensionResource(
                    id = R.dimen.padding_medium
                )
            )
        )
    }
}