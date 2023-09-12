package com.example.showmoview.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL1 = "https://api.themoviedb.org/"
private const val BASE_URL =  "https://api.themoviedb.org"
//private const val API_KEY = "1723080046477ef86643926818bbb343"
private const val API_KEY = "8c1a99064f1795f3ba344497293aa2c4"

internal abstract class KtorApi {


    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            }

            )
        }
    }

    fun HttpRequestBuilder.pathUrl(path: String) {
        url {
            takeFrom(BASE_URL)
            path("3",path)
            parameter("api_key", API_KEY)

        }
    }
}