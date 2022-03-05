package com.izzancode.kompoengdomba.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class perkembangan{
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

    @SerializedName("fd_devdate")
    @Expose
    var fd_devdate: String? = null

    @SerializedName("fn_weight")
    @Expose
    var fn_weight: String? = null

    @SerializedName("fn_height")
    @Expose
    var fn_height: String? = null

    @SerializedName("fv_characteristics")
    @Expose
    var fv_characteristics: String? = null

    @SerializedName("fv_desc")
    @Expose
    var fv_desc: String? = null
}