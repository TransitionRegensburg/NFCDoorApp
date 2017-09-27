package de.lfuhr.nfcdoorapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import de.lfuhr.nfcdoorapp.data.Door

class DoorWrapper {

    @SerializedName("door")
    @Expose
    var door: Door? = null

}