package com.example.showmoview.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showmoview.domain.model.movie
import com.example.showmoview.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    val getMovieUseCase: GetMovieUseCase,
    val movieId: Int
) :
    ViewModel() {

    var uiState by mutableStateOf(DetailScreenState())

    init {
        getMovie(movieId)
    }

    private fun getMovie(movieId: Int) {
        viewModelScope.launch {

            uiState = uiState.copy(loading = true)

            uiState = try {

                val movie = getMovieUseCase(movieId)
                uiState.copy(loading = false, movie = movie)
            } catch (error: Exception) {
                uiState.copy(loading = false, errorMessage = error.toString())
            }
        }
    }

}


data class DetailScreenState(
    val loading: Boolean? = false,
    val movie: movie? = null,
    var errorMessage: String? = null
)