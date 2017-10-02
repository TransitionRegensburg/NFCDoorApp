package de.lfuhr.nfcdoorapp.data_model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DoorsWrapper {

    @SerializedName("elements")
    @Expose
    var doors: List<Door>? = null

}