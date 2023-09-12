package com.example.showmoview.common

import androidx.navigation.NavArgs
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destinations {
    val title: String
    val route: String
    val routeWithArgs: String
}

object Home : Destinations {
    override val title: String
        get() = "Movies"
    override val route: String
        get() = "home"
    override val routeWithArgs: String
        get() = route

}

object Detail : Destinations {
    override val title: String
        get() = "Movie"
    override val route: String
        get() = "detail"
    override val routeWithArgs: String
        get() = "$route/{movieId}"

    val argument = listOf(
        navArgument(name = "movieId") { type = NavType.IntType }
    )

}

val movieDestination = listOf(Home, Detail)