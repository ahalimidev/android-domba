package com.izzancode.kompoengdomba.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.izzancode.kompoengdomba.R

var foto_profil = "http://192.168.100.30/domba/customer/image/"
var foto_kandang = "http://192.168.100.30/domba/file/kandang/"
var foto_kambing = "http://192.168.100.30/domba/file/kambing/"
fun progressDialog(context: Context): Dialog {

    val dialog = Dialog(context)
    val inflate = LayoutInflater.from(context).inflate(R.layout.progress, null)
    dialog.setContentView(inflate)
    dialog.setCancelable(false)
    dialog.window!!.setBackgroundDrawable(
        ColorDrawable(Color.TRANSPARENT)
    )
    return dialog
}