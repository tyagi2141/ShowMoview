package com.example.showmoview.di

import com.example.showmoview.data.remote.MovieService
import com.example.showmoview.data.remote.RemoteDataSource
import com.example.showmoview.data.repository.MovieRepositoryImp
import com.example.showmoview.domain.repository.MovieRepository
import com.example.showmoview.domain.usecase.GetMovieUseCase
import com.example.showmoview.domain.usecase.GetMoviesUseCase
import com.example.showmoview.util.provideDispatcher
import org.koin.dsl.module

private val dataModule = module {
    factory { RemoteDataSource(get(), get()) }
    factory { MovieService() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

private val domainModule = module {
    single<MovieRepository> { MovieRepositoryImp(get()) }
    factory { GetMoviesUseCase() }
    factory { GetMovieUseCase() }
}

private val shareModules = listOf(dataModule, utilityModule, domainModule)

fun getShareModule() = shareModules