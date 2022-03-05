package com.izzancode.kompoengdomba.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class perawatan{
    @SerializedName("fn_sheepid")
    @Expose
    var fn_sheepid: String? = null

    @SerializedName("fv_codesheep")
    @Expose
    var fv_codesheep: String? = null

    @SerializedName("fv_namesheep")
    @Expose
    var fv_namesheep: String? = null

    @SerializedName("fv_image")
    @Expose
    var fv_image: String? = null

    @SerializedName("fd_treatdate")
    @Expose
    var fd_treatdate: String? = null

    @SerializedName("fv_desc")
    @Expose
    var fv_desc: String? = null
}