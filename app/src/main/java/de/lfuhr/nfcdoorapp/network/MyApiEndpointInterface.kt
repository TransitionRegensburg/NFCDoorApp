package de.lfuhr.nfcdoorapp.network

import de.lfuhr.nfcdoorapp.data_model.Door
import de.lfuhr.nfcdoorapp.data_model.DoorWrapper
import de.lfuhr.nfcdoorapp.data_model.DoorsWrapper
import retrofit2.Call
import retrofit2.http.*


interface MyApiEndpointInterface {

    @GET("doors/{id}")
    fun getDoor(@Path("id") id: Int): Call<DoorWrapper>

    @GET("doors")
    fun getDoors(): Call<DoorsWrapper>

    @PATCH("doors/{id}")
    fun updateDoor(@Body user: Door, @Path("id") id: Int): Call<DoorWrapper>

}