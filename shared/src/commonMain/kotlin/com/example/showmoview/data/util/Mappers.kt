package com.example.showmoview.data.util

import com.example.showmoview.data.remote.MovieRemote
import com.example.showmoview.domain.model.movie

internal fun MovieRemote.toMovies(): movie {
    return movie(
        id = id?:0,
        title = title?:"",
        description = overview?:"",
        imageUrl = getImageUrl(posterPath),
        releaseDate = releaseDate?:""
    )
}

private fun getImageUrl(posterImage: String?) = "https://image.tmdb.org/t/p/w500/$posterImage"