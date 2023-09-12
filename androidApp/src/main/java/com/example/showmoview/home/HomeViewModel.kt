package com.example.showmoview.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showmoview.domain.model.movie
import com.example.showmoview.domain.usecase.GetMovieUseCase
import com.example.showmoview.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

data class HomeScreenState(
    val loading: Boolean = false,
    val movieList: List<movie> = emptyList(),
    val refreshState: Boolean = false,
    val errorMessage: String? = "",
    val loadFinished: Boolean = false
)

class HomeViewModel(val getMoviesUseCase: GetMoviesUseCase) : ViewModel() {

    var uiState by mutableStateOf(HomeScreenState())

    private var currentPage = 1

    init {
        loadMovies(forceLoad = false)
    }

    fun loadMovies(forceLoad: Boolean = false) {
        if (uiState.loading) return
        if (forceLoad) currentPage = 1
        if (currentPage == 1) uiState = uiState.copy(refreshState = true)

        viewModelScope.launch {
            uiState = uiState.copy(loading = true)

            try {
                val resultMovie = getMoviesUseCase(page = currentPage)
                val movies = if (currentPage == 1) resultMovie else uiState.movieList + resultMovie

                currentPage += 1

                uiState = uiState.copy(
                    loading = false,
                    refreshState = false,
                    movieList = movies,
                    loadFinished = resultMovie.isEmpty()
                )

                Log.e("hbvhjbfdv", "$currentPage == ${uiState.movieList.toString()}")

            } catch (error: Exception) {
                Log.e("hbvhjbfdv", "${error.toString()}")

                uiState = uiState.copy(
                    loading = false,
                    refreshState = false,
                    errorMessage = "No Movie",
                    loadFinished = true
                )
            }
        }
    }


}

//Ktor (Network Client)
//SQL Delight (Caching Client)
//Jetpack Compose Navigation
//clean architecture design principles in a KMM project {MVVM,MVI architectural pattern}
//Sharing ViewModels between both platforms
//Coroutines ,Flows and channels
//Dependency injection in KMM projects {Hilt ,Koin,Dagger2}
//MutableState

/*Functional programming
Kotlin coroutines
Object Oriented Programming
Kotlin extensions
Generics
Scope function
sealed class
lambda function*/


