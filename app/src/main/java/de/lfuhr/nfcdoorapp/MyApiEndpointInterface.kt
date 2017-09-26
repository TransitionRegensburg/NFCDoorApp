package de.lfuhr.nfcdoorapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApiEndpointInterface {

    @GET("doors/{id}")
    fun getDoor(@Path("id") id: String): Call<Example>

}