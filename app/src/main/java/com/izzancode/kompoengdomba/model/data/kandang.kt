package com.izzancode.kompoengdomba.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class kandang{
    @SerializedName("fn_kavid")
    @Expose
    var fn_kavid: String? = null

    @SerializedName("fv_codekav")
    @Expose
    var fv_codekav: String? = null

    @SerializedName("fv_namekav")
    @Expose
    var fv_namekav: String? = null

    @SerializedName("fn_size")
    @Expose
    var fn_size: String? = null

    @SerializedName("fm_price")
    @Expose
    var fm_price: String? = null

    @SerializedName("fv_picture")
    @Expose
    var fv_picture: String? = null

    @SerializedName("fn_totalsheep")
    @Expose
    var fn_totalsheep: String? = null

    @SerializedName("fv_configname")
    @Expose
    var fv_configname: String? = null


    @SerializedName("fv_desc")
    @Expose
    var fv_desc: String? = null

    @SerializedName("fc_status")
    @Expose
    var fc_status: String? = null

    @SerializedName("fn_typekavid")
    @Expose
    var fn_typekavid: String? = null

}