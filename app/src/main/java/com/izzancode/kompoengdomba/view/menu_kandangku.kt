package com.izzancode.kompoengdomba.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.izzancode.kompoengdomba.databinding.ActivityMenuKandangkuBinding
import com.izzancode.kompoengdomba.model.data.kelahiran

class menu_kandangku : AppCompatActivity() {
    private val binding by lazy { ActivityMenuKandangkuBinding.inflate(layoutInflater) }
    private  var  id : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        id = intent.getStringExtra("id").toString()
        var sharedPreferences = getSharedPreferences("App", Context.MODE_PRIVATE)
        binding.tvKodeCostumer.text = sharedPreferences.getString("fv_codecus", null).toString()
        binding.tvNamaCostumber.text = sharedPreferences.getString("fv_namecus", null).toString()
        binding.llListDisplay.setOnClickListener {
            startActivity(Intent(this@menu_kandangku,list_display_kandangku::class.java).putExtra("fn_kavid",id))
        }
        binding.llListKelahiran.setOnClickListener {
            startActivity(Intent(this@menu_kandangku,list_kelahiran_kandangku::class.java).putExtra("fn_kavid",id))
        }
        binding.llListKematian.setOnClickListener {
            startActivity(Intent(this@menu_kandangku,list_kematian_kandangku::class.java).putExtra("fn_kavid",id))
        }
        binding.llListKesehatan.setOnClickListener {
            startActivity(Intent(this@menu_kandangku,list_kesehatan_kandangku::class.java).putExtra("fn_kavid",id))
        }
        binding.llListPerawatan.setOnClickListener {
            startActivity(Intent(this@menu_kandangku,list_perawatan_kandangku::class.java).putExtra("fn_kavid",id))
        }
        binding.llListPerkembangan.setOnClickListener {
            startActivity(Intent(this@menu_kandangku,list_perkembangan_kandangku::class.java).putExtra("fn_kavid",id))
        }
        binding.llListTernak.setOnClickListener {
            startActivity(Intent(this@menu_kandangku,list_ternak_kandangku::class.java).putExtra("fn_kavid",id))
        }
        binding.ivBack.setOnClickListener { finish() }
    }
}