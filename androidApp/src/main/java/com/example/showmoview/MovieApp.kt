package com.example.showmoview

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.showmoview.common.Detail
import com.example.showmoview.common.Home
import com.example.showmoview.common.MovieAppBar
import com.example.showmoview.common.movieDestination
import com.example.showmoview.detail.DetailScreen
import com.example.showmoview.detail.DetailViewModel
import com.example.showmoview.home.HomeScreen
import com.example.showmoview.home.HomeViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieApp() {

    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val isSystemDark = isSystemInDarkTheme()

    val scaffoldState = rememberScaffoldState()
    val statusBarColor = if (isSystemDark) {
        MaterialTheme.colors.primaryVariant
    } else {
        Color.Transparent
    }

    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor, darkIcons = !isSystemDark)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = movieDestination.find {
        backStackEntry?.destination?.route == it.route || backStackEntry?.destination?.route == it.routeWithArgs
    } ?: Home

    Scaffold(scaffoldState = scaffoldState, topBar = {
        MovieAppBar(
            canNavigateBack = navController.previousBackStackEntry != null,
            currentScreen = currentScreen
        ) {
            navController.navigateUp()
        }
    }) { innerpadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerpadding),
            startDestination = Home.routeWithArgs
        ) {
            composable(Home.routeWithArgs) {
                val homeViewModel: HomeViewModel = koinViewModel()
                Log.e("screenView","HOME......")
                HomeScreen(
                    uiState = homeViewModel.uiState,
                    loadNextMovie = { homeViewModel.loadMovies(forceLoad = it) },
                    navigateToDetail = {
                        navController.navigate(
                            "${Detail.route}/${it.id?:1}"
                        )
                    }
                )
            }


            composable(Detail.routeWithArgs) {
                Log.e("screenView","DETAILS......")

                val movieId = it.arguments?.getString("movieId") ?: "0"
                val detailViewModel: DetailViewModel =
                    koinViewModel(parameters = { parametersOf(movieId.toInt()) })

                DetailScreen(uiState = detailViewModel.uiState)
            }
        }

    }
}