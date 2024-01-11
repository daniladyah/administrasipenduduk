package com.example.sensuspenduduk.navigasi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sensuspenduduk.R
import com.example.sensuspenduduk.ui.halaman.DestinasiDetail
import com.example.sensuspenduduk.ui.halaman.DestinasiEdit
import com.example.sensuspenduduk.ui.halaman.DestinasiEntry
import com.example.sensuspenduduk.ui.halaman.DestinasiHome
import com.example.sensuspenduduk.ui.halaman.DetailScreen
import com.example.sensuspenduduk.ui.halaman.EntryKeluargaScreen
import com.example.sensuspenduduk.ui.halaman.HalamanHome
import com.example.sensuspenduduk.ui.halaman.ItemEditScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeluargaTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier, scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                            id = R.string.back
                        )
                    )
                }
            }
        }
    )
}

@Composable
fun SensusApp(navController: NavHostController = rememberNavController()) {
    HostNavigasi(navController = navController)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = DestinasiHome.route) {
        composable(DestinasiHome.route) {
            HalamanHome(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick =
                { itemId ->
                    navController.navigate("${DestinasiDetail.route}/$itemId")
                }
            )
        }
        composable(DestinasiEntry.route){
            EntryKeluargaScreen(navigateBack = {navController.popBackStack()})
        }
        composable(
            DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.keluargaIdArg) {
                    type = NavType.IntType
                },
            ),
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(DestinasiDetail.keluargaIdArg)
            itemId?.let {
                DetailScreen(
                    navigateToEditItem = { navController.navigate("${DestinasiEdit.route}/$it") },
                    navigateBack = { navController.popBackStack() })
            }
        }
        composable(
            DestinasiEdit.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEdit.kkIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })

        }
    }
}