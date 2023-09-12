package com.example.showmoview.domain.repository

import com.example.showmoview.data.remote.MovieRemote
import com.example.showmoview.data.remote.MoviesResponse
import com.example.showmoview.domain.model.movie

interface MovieRepository {

   suspend fun getMovies(page:Int):List<movie>

   suspend fun getMovie(movieId:Int):movie
}