package de.lfuhr.nfcdoorapp.data_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DoorWrapper {

    @SerializedName("door")
    @Expose
    var door: Door? = null

}