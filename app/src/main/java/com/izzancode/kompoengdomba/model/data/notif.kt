package com.izzancode.kompoengdomba.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class notif{
    @SerializedName("fc_notif")
    @Expose
    var fc_notif: String? = null

    @SerializedName("fd_date")
    @Expose
    var fd_date: String? = null

    @SerializedName("fv_desc")
    @Expose
    var fv_desc: String? = null
}