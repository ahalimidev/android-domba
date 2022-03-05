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

class kandangViewModel : ViewModel() {
    var api: Api = retrofitFatory.retrofitService()

    suspend fun kandang(status: String ) = withContext(Dispatchers.IO) {

        api.kandang(status)
    }
    suspend fun pencarian_kandang (status: String, name : String ) = withContext(Dispatchers.IO) {

        api.pencarian_kandang(status,name)
    }
    suspend fun kandang_detail(status: String,fn_kavid : String ) = withContext(Dispatchers.IO) {

        api.kandang_detail(status,fn_kavid)
    }

    suspend fun type_kandang( ) = withContext(Dispatchers.IO) {

        api.type_kandang()
    }

    suspend fun filter_kandang(status : String,kandang : String,kondisi : String,
                                batas_min : String, batas_max : String,
                                kapasitas_min : String, kapasitas_max : String,
                                harga_min : String, harga_max : String) = withContext(Dispatchers.IO) {

        api.filter_kandang(status,kandang, kondisi, batas_min, batas_max, kapasitas_min, kapasitas_max, harga_min, harga_max)
    }
}