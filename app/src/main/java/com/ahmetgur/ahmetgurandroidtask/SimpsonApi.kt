package com.ahmetgur.ahmetgurandroidtask

import retrofit2.Response
import retrofit2.http.GET

interface SimpsonApi {

    @GET("?q=simpsons+characters&format=json")
    suspend fun getSimpsons(): Response<Simpson>
}