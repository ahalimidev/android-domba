package com.izzancode.kompoengdomba.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class user{
    @SerializedName("fn_cusid")
    @Expose
    var fn_cusid: String? = null

    @SerializedName("fv_codecus")
    @Expose
    var fv_codecus: String? = null

    @SerializedName("fv_namecus")
    @Expose
    var fv_namecus: String? = null

    @SerializedName("fv_identitynum")
    @Expose
    var fv_identitynum: String? = null

    @SerializedName("fv_address")
    @Expose
    var fv_address: String? = null

    @SerializedName("fv_zipcode")
    @Expose
    var fv_zipcode: String? = null

    @SerializedName("fv_phone")
    @Expose
    var fv_phone: String? = null

    @SerializedName("fv_email")
    @Expose
    var fv_email: String? = null

    @SerializedName("fv_image")
    @Expose
    var fv_image: String? = null

    @SerializedName("fv_countryname")
    @Expose
    var fv_countryname: String? = null

    @SerializedName("fv_provincename")
    @Expose
    var fv_provincename: String? = null

    @SerializedName("fv_cityname")
    @Expose
    var fv_cityname: String? = null

    @SerializedName("fc_hold")
    @Expose
    var fc_hold: String? = null

    @SerializedName("fd_dateinput")
    @Expose
    var fd_dateinput: String? = null

}