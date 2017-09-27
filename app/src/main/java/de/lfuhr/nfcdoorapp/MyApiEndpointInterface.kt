package de.lfuhr.nfcdoorapp

import de.lfuhr.nfcdoorapp.data.DoorWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MyApiEndpointInterface {

    @GET("doors/{id}")
    fun getDoor(@Path("id") id: String): Call<DoorWrapper>

}