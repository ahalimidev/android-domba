package com.izzancode.kompoengdomba.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class kandangku{
    @SerializedName("fn_kavid")
    @Expose
    var fn_kavid: String? = null

    @SerializedName("fv_codekav")
    @Expose
    var fv_codekav: String? = null

    @SerializedName("fv_namekav")
    @Expose
    var fv_namekav: String? = null

    @SerializedName("fv_picture")
    @Expose
    var fv_picture: String? = null

    @SerializedName("total_kambing")
    @Expose
    var total_kambing: String? = null

    @SerializedName("kelahiran_kambing")
    @Expose
    var kelahiran_kambing: String? = null

    @SerializedName("kematian_kambing")
    @Expose
    var kematian_kambing: String? = null


}