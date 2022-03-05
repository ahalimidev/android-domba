package com.izzancode.kompoengdomba.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.izzancode.kompoengdomba.R
import com.izzancode.kompoengdomba.databinding.ActivityTitipAngonBinding

class titip_angon : AppCompatActivity() {
    private val binding by lazy { ActivityTitipAngonBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var sharedPreferences = getSharedPreferences("App", Context.MODE_PRIVATE)
        binding.tvKodeCostumer.text = sharedPreferences.getString("fv_codecus", null).toString()
        binding.tvNamaCostumber.text = sharedPreferences.getString("fv_namecus", null).toString()
        binding.llListDisplay.setOnClickListener {
            startActivity(Intent(this@titip_angon,list_display_titip_angon::class.java))
        }
        binding.llListKelahiran.setOnClickListener {
            startActivity(Intent(this@titip_angon,list_kelahiran_titip_angon::class.java))
        }
        binding.llListKematian.setOnClickListener {
            startActivity(Intent(this@titip_angon,list_kematian_titip_angon::class.java))
        }
        binding.llListKesehatan.setOnClickListener {
            startActivity(Intent(this@titip_angon,list_kesehatan_titip_angon::class.java))
        }
        binding.llListPerawatan.setOnClickListener {
            startActivity(Intent(this@titip_angon,list_perawatan_titip_angon::class.java))
        }
        binding.llListPerkembangan.setOnClickListener {
            startActivity(Intent(this@titip_angon,list_perkembangan_titip_angon::class.java))
        }
        binding.llListTernak.setOnClickListener {
            startActivity(Intent(this@titip_angon,list_ternak_titip_angon::class.java))
        }
        binding.ivBack.setOnClickListener { finish() }
    }
}