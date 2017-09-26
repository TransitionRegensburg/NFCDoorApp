package de.lfuhr.nfcdoorapp.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Door {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("api_key")
    @Expose
    var apiKey: String? = null

}