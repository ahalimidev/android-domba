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

class titipangonViewModel : ViewModel() {
    var api: Api = retrofitFatory.retrofitService()
    //-----list
    suspend fun list_ternak(context: Context ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)

        api.list_ternak_titip_angon(sharedPreferences.getString("fn_cusid", null).toString())
    }

    suspend fun list_perkembangan(context: Context ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)

        api.list_perkembangan_titip_angon(sharedPreferences.getString("fn_cusid", null).toString())
    }

    suspend fun list_perawatan(context: Context ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)

        api.list_perawatan_titip_angon(sharedPreferences.getString("fn_cusid", null).toString())
    }

    suspend fun list_kesehatan(context: Context ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)

        api.list_kesehatan_titip_angon(sharedPreferences.getString("fn_cusid", null).toString())
    }

    suspend fun list_kematian(context: Context ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)

        api.list_kematian_titip_angon(sharedPreferences.getString("fn_cusid", null).toString())
    }

    suspend fun list_kelahiran(context: Context ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)

        api.list_kelahiran_titip_angon(sharedPreferences.getString("fn_cusid", null).toString())
    }

    suspend fun list_display(context: Context, fc_status : String ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)

        api.list_display_titip_angon(sharedPreferences.getString("fn_cusid", null).toString(), fc_status)
    }

    //-----------detail

    suspend fun detail_ternak(fn_sheepid : String ) = withContext(Dispatchers.IO) {
        api.detail_ternak_titip_angon(fn_sheepid)
    }

    suspend fun detail_perkembangan(fn_sheepid : String ) = withContext(Dispatchers.IO) {
        api.detail_perkembangan_titip_angon(fn_sheepid)
    }

    suspend fun detail_perawatan(fn_sheepid : String ) = withContext(Dispatchers.IO) {
        api.detail_perawatan_titip_angon(fn_sheepid)
    }

    suspend fun detail_kesehatan(fn_sheepid : String ) = withContext(Dispatchers.IO) {
        api.detail_kesehatan_titip_angon(fn_sheepid)
    }

    suspend fun detail_kematian(fn_sheepid : String ) = withContext(Dispatchers.IO) {
        api.detail_kematian_titip_angon(fn_sheepid)
    }

    suspend fun detail_kelahiran(fc_nodoc : String ) = withContext(Dispatchers.IO) {
        api.detail_kelahiran_titip_angon(fc_nodoc)
    }
    suspend fun detail_display(fn_sheepid : String, fc_status : String ) = withContext(Dispatchers.IO) {
        api.detail_display_titip_angon(fn_sheepid, fc_status)
    }

    //-----------filter
    suspend fun filter_perkembangan(fn_sheepid : String, awal : String, akhir : String, kondisi : String ) = withContext(Dispatchers.IO) {
        api.filter_perkembangan_titip_angon(fn_sheepid, awal, akhir, kondisi)
    }

    suspend fun filter_perawatan(fn_sheepid : String, awal : String, akhir : String, kondisi : String ) = withContext(Dispatchers.IO) {
        api.filter_perawatan_titip_angon(fn_sheepid, awal, akhir, kondisi)
    }

    suspend fun filter_kesehatan(fn_sheepid : String, awal : String, akhir : String, kondisi : String ) = withContext(Dispatchers.IO) {
        api.filter_kesehatan_titip_angon(fn_sheepid, awal, akhir, kondisi)
    }

    suspend fun filter_kelahiran(context: Context, awal : String, akhir : String, kondisi : String ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)

        api.filter_kelahiran_titip_angon(sharedPreferences.getString("fn_cusid", null).toString(), awal, akhir, kondisi)
    }

    suspend fun filter_kematian(context: Context, awal : String, akhir : String, kondisi : String ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)

        api.filter_kematian_titip_angon(sharedPreferences.getString("fn_cusid", null).toString(), awal, akhir, kondisi)
    }
}