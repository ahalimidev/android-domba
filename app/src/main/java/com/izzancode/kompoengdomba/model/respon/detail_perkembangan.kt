package com.izzancode.kompoengdomba.model.respon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.izzancode.kompoengdomba.model.data.*
import com.izzancode.kompoengdomba.model.data.kandang
import com.izzancode.kompoengdomba.model.data.saldo
import com.izzancode.kompoengdomba.model.data.user

class detail_perkembangan {
    @SerializedName("success")
    @Expose
    var success: Int = 0

    @SerializedName("pesan")
    @Expose
    lateinit var pesan: String

    @SerializedName("data_all")
    @Expose
    lateinit var data_all: ArrayList<perkembangan>

    @SerializedName("data_one")
    @Expose
    lateinit var data_one: domba
}