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

class customerViewModel : ViewModel() {
    var api: Api = retrofitFatory.retrofitService()

    fun getLogin(activity: Activity, context: Context) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("login", false)) {
            activity.startActivity(Intent(context, menu::class.java))
            activity.finish()
        }else{
            Handler().postDelayed({
                activity.startActivity(Intent(context, login::class.java))
                activity.finish()
            }, 5000)
        }
    }

    fun setLogin(activity: Activity, context: Context, dataUser: user) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("fc_hold", dataUser.fc_hold)
        editor.putString("fn_cusid", dataUser.fn_cusid)
        editor.putString("fv_namecus", dataUser.fv_namecus)
        editor.putString("fv_codecus", dataUser.fv_codecus)
        editor.putBoolean("login", true)
        editor.commit()
        activity.startActivity(Intent(context, menu::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
        activity.finish()
    }

    suspend fun loginuser(
        email: String,
        password: String,
        token: String,
    ) = withContext(Dispatchers.IO) {
        api.login(email, password,token)
    }
    suspend fun verfikasi_email(
        email: String
    ) = withContext(Dispatchers.IO) {
        api.verfikaasi_email(email)
    }


    suspend fun lupa_password(
        code: String,
        password: String,
        token: String,
    ) = withContext(Dispatchers.IO) {
        api.lupa_password(code, password,token)
    }

    suspend fun ganti_password(
        context: Context,
        fv_password: String,
    ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)
        api.ganti_paassword(sharedPreferences.getString("fn_cusid", null).toString(),fv_password)
    }
    suspend fun logout(context: Context ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)
        api.logout(sharedPreferences.getString("fn_cusid", null).toString())
    }

    suspend fun upload(
        context: Context,
        fv_image:  MultipartBody.Part,
    ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)
        api.upload(RequestBody.create("text/plain".toMediaTypeOrNull(),sharedPreferences.getString("fn_cusid", null).toString()),fv_image)
    }

    suspend fun profil(context: Context ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)
        api.profil(sharedPreferences.getString("fn_cusid", null).toString())
    }
    suspend fun notif(context: Context, days : String ) = withContext(Dispatchers.IO) {
        var sharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)
        api.detail_notif(sharedPreferences.getString("fn_cusid", null).toString(),days)
    }
}