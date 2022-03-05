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

class kandangkuViewModel : ViewModel() {
    var api: Api = retrofitFatory.retrofitService()

    suspend fun kandangku(context: Context ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)
        api.kandangku(sharedPreferences.getString("fn_cusid", null).toString())
    }

    //-----list
    suspend fun list_ternak(fn_kavid : String ) = withContext(Dispatchers.IO) {
        api.list_ternak(fn_kavid)
    }

    suspend fun list_perkembangan(fn_kavid : String ) = withContext(Dispatchers.IO) {
        api.list_perkembangan(fn_kavid)
    }

    suspend fun list_perawatan(fn_kavid : String ) = withContext(Dispatchers.IO) {
        api.list_perawatan(fn_kavid)
    }

    suspend fun list_kesehatan(fn_kavid : String ) = withContext(Dispatchers.IO) {
        api.list_kesehatan(fn_kavid)
    }

    suspend fun list_kematian(fn_kavid : String ) = withContext(Dispatchers.IO) {
        api.list_kematian(fn_kavid)
    }

    suspend fun list_kelahiran(fn_kavid : String ) = withContext(Dispatchers.IO) {
        api.list_kelahiran(fn_kavid)
    }

    suspend fun list_display(fn_kavid : String, fc_status : String ) = withContext(Dispatchers.IO) {
        api.list_display(fn_kavid, fc_status)
    }

    //-----------detail

    suspend fun detail_ternak(fn_sheepid : String ) = withContext(Dispatchers.IO) {
        api.detail_ternak(fn_sheepid)
    }

    suspend fun detail_perkembangan(fn_sheepid : String ) = withContext(Dispatchers.IO) {
        api.detail_perkembangan(fn_sheepid)
    }

    suspend fun detail_perawatan(fn_sheepid : String ) = withContext(Dispatchers.IO) {
        api.detail_perawatan(fn_sheepid)
    }

    suspend fun detail_kesehatan(fn_sheepid : String ) = withContext(Dispatchers.IO) {
        api.detail_kesehatan(fn_sheepid)
    }

    suspend fun detail_kematian(fn_sheepid : String ) = withContext(Dispatchers.IO) {
        api.detail_kematian(fn_sheepid)
    }

    suspend fun detail_kelahiran(fc_nodoc : String ) = withContext(Dispatchers.IO) {
        api.detail_kelahiran(fc_nodoc)
    }
    suspend fun detail_display(fn_sheepid : String, fc_status : String ) = withContext(Dispatchers.IO) {
        api.detail_display(fn_sheepid, fc_status)
    }

    //-----------filter
    suspend fun filter_perkembangan(fn_sheepid : String, awal : String, akhir : String, kondisi : String ) = withContext(Dispatchers.IO) {
        api.filter_perkembangan(fn_sheepid, awal, akhir, kondisi)
    }

    suspend fun filter_perawatan(fn_sheepid : String, awal : String, akhir : String, kondisi : String ) = withContext(Dispatchers.IO) {
        api.filter_perawatan(fn_sheepid, awal, akhir, kondisi)
    }

    suspend fun filter_kesehatan(fn_sheepid : String, awal : String, akhir : String, kondisi : String ) = withContext(Dispatchers.IO) {
        api.filter_kesehatan(fn_sheepid, awal, akhir, kondisi)
    }

    suspend fun filter_kelahiran(fn_kavid : String, awal : String, akhir : String, kondisi : String ) = withContext(Dispatchers.IO) {
        api.filter_kelahiran(fn_kavid, awal, akhir, kondisi)
    }

    suspend fun filter_kematian(fn_kavid : String, awal : String, akhir : String, kondisi : String ) = withContext(Dispatchers.IO) {
        api.filter_kematian(fn_kavid, awal, akhir, kondisi)
    }
}