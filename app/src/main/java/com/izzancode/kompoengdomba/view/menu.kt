package com.izzancode.kompoengdomba.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.izzancode.kompoengdomba.R
import com.izzancode.kompoengdomba.databinding.ActivityMenuBinding

class menu : AppCompatActivity() {
    private val binding by lazy { ActivityMenuBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var sharedPreferences = getSharedPreferences("App", Context.MODE_PRIVATE)
        binding.tvKodeCostumer.text = sharedPreferences.getString("fv_codecus", null).toString()
        binding.tvNamaCostumber.text = sharedPreferences.getString("fv_namecus", null).toString()

        binding.ivKandangku.setOnClickListener {
            startActivity(Intent(this@menu,kandangku::class.java))
        }
        binding.ivKadang.setOnClickListener {
            startActivity(Intent(this@menu,kandang::class.java))

        }
        binding.ivTitipAngon.setOnClickListener {
            startActivity(Intent(this@menu,titip_angon::class.java))

        }
        binding.ivNotifikasi.setOnClickListener {
            startActivity(Intent(this@menu,history_notifku::class.java))

        }
        binding.ivSaldo.setOnClickListener {
            startActivity(Intent(this@menu,saldoku::class.java))

        }
        binding.ivProfil.setOnClickListener {
            startActivity(Intent(this@menu,profil::class.java))
        }
    }
}