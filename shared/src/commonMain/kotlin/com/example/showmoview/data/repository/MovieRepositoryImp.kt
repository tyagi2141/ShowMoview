package com.example.showmoview.data.repository

import com.example.showmoview.data.remote.RemoteDataSource
import com.example.showmoview.data.util.toMovies
import com.example.showmoview.domain.model.movie
import com.example.showmoview.domain.repository.MovieRepository

internal class MovieRepositoryImp(private val remoteDataSource: RemoteDataSource) :
    MovieRepository {
    override suspend fun getMovies(page: Int): List<movie> {
        return remoteDataSource.getMovies(page = page).results.map { it.toMovies() }
    }

    override suspend fun getMovie(movieId: Int): movie {
        return remoteDataSource.getMovie(movieId).toMovies()
    }

}