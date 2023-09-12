package com.example.showmoview.domain.usecase

import com.example.showmoview.domain.model.movie
import com.example.showmoview.domain.repository.MovieRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetMoviesUseCase : KoinComponent {

    private val repository: MovieRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(page: Int): List<movie> {
        return repository.getMovies(page)
    }
}