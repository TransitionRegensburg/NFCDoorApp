package de.lfuhr.nfcdoorapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import de.lfuhr.nfcdoorapp.data.Door

class Example {

    @SerializedName("door")
    @Expose
    var door: Door? = null

}