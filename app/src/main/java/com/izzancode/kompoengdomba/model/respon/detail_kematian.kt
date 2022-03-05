package com.izzancode.kompoengdomba.model.respon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.izzancode.kompoengdomba.model.data.kematian


class detail_kematian {
    @SerializedName("success")
    @Expose
    var success: Int = 0

    @SerializedName("pesan")
    @Expose
    lateinit var pesan: String

    @SerializedName("data")
    @Expose
    lateinit var data: kematian
}