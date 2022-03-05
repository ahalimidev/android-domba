package com.izzancode.kompoengdomba.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import com.izzancode.kompoengdomba.model.data.user
import com.izzancode.kompoengdomba.server.Api
import com.izzancode.kompoengdomba.server.retrofitFatory
import com.izzancode.kompoengdomba.view.login
import com.izzancode.kompoengdomba.view.menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

class saldoViewModel : ViewModel() {
    var api: Api = retrofitFatory.retrofitService()

    suspend fun saldo_total(context: Context ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)
        api.total_saldo(sharedPreferences.getString("fn_cusid", null).toString())
    }
    suspend fun saldo_detail(
        context: Context,
        awal : String,
        akhir : String
        ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)
        api.detail_saldo(sharedPreferences.getString("fn_cusid", null).toString(),awal,akhir)
    }
}