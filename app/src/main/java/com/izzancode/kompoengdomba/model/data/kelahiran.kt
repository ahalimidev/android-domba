package com.izzancode.kompoengdomba.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class kelahiran{
    @SerializedName("fc_nodoc")
    @Expose
    var fc_nodoc: String? = null

    @SerializedName("fv_codesheep")
    @Expose
    var fv_codesheep: String? = null

    @SerializedName("fv_namesheep")
    @Expose
    var fv_namesheep: String? = null

    @SerializedName("fd_birthdate")
    @Expose
    var fd_birthdate: String? = null

    @SerializedName("fn_age")
    @Expose
    var fn_age: String? = null

    @SerializedName("fn_gender")
    @Expose
    var fn_gender: String? = null

    @SerializedName("fn_sheeptype")
    @Expose
    var fn_sheeptype: String? = null

    @SerializedName("fn_weight")
    @Expose
    var fn_weight: String? = null

    @SerializedName("fn_height")
    @Expose
    var fn_height: String? = null

    @SerializedName("fv_characteristics")
    @Expose
    var fv_characteristics: String? = null

    @SerializedName("fv_image")
    @Expose
    var fv_image: String? = null

    @SerializedName("fn_perentid")
    @Expose
    var fn_perentid: String? = null
}