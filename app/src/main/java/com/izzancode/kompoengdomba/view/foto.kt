package com.izzancode.kompoengdomba.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.izzancode.kompoengdomba.databinding.ActivityFotoBinding

class foto : AppCompatActivity() {
    private  val binding by lazy { ActivityFotoBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.ivBack.setOnClickListener { finish() }
        Glide.with(this@foto)
            .load(intent.getStringExtra("foto"))
            .into(binding.ivGambar)
    }
}