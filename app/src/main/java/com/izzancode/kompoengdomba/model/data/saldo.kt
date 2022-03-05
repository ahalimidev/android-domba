package com.izzancode.kompoengdomba.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class saldo{
    @SerializedName("fc_nodoc")
    @Expose
    var fc_nodoc: String? = null

    @SerializedName("fd_withdrawaldate")
    @Expose
    var fd_withdrawaldate: String? = null

    @SerializedName("fm_saldo")
    @Expose
    var fm_saldo: String? = null

    @SerializedName("fm_amount")
    @Expose
    var fm_amount: String? = null

    @SerializedName("fv_picture")
    @Expose
    var fv_picture: String? = null

    @SerializedName("fv_desc")
    @Expose
    var fv_desc: String? = null


}