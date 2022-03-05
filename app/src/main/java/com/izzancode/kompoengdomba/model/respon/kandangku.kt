package com.izzancode.kompoengdomba.model.respon

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.izzancode.kompoengdomba.model.data.kandang
import com.izzancode.kompoengdomba.model.data.kandangku
import com.izzancode.kompoengdomba.model.data.saldo
import com.izzancode.kompoengdomba.model.data.user

class kandangku {
    @SerializedName("success")
    @Expose
    var success: Int = 0

    @SerializedName("pesan")
    @Expose
    lateinit var pesan: String

    @SerializedName("data")
    @Expose
    lateinit var data: ArrayList<kandangku>
}