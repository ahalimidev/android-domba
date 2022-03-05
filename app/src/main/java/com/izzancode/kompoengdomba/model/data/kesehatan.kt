package com.izzancode.kompoengdomba.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class kesehatan{
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

    @SerializedName("fd_healthdate")
    @Expose
    var fd_healthdate: String? = null

    @SerializedName("fv_disease")
    @Expose
    var fv_disease: String? = null

    @SerializedName("fv_diseasetreatment")
    @Expose
    var fv_diseasetreatment: String? = null

    @SerializedName("fv_preventivemeasure")
    @Expose
    var fv_preventivemeasure: String? = null
}