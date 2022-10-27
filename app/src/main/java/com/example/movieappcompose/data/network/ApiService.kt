package com.example.movieappcompose.data.network

import com.example.movieappcompose.data.models.Movies
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/shows")
    suspend fun getAllMovies() : Response<List<Movies>>
}