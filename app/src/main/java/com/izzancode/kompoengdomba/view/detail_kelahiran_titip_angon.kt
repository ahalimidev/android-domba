package com.izzancode.kompoengdomba.view

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.izzancode.kompoengdomba.databinding.ActivityDetailKelahiranKandangkuBinding
import com.izzancode.kompoengdomba.databinding.ActivityDetailTernakKandangkuBinding
import com.izzancode.kompoengdomba.utils.foto_kambing
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.kandangkuViewModel
import com.izzancode.kompoengdomba.viewmodel.titipangonViewModel
import kotlinx.coroutines.launch

class detail_kelahiran_titip_angon : AppCompatActivity() {
    private  val binding by lazy { ActivityDetailKelahiranKandangkuBinding.inflate(layoutInflater) }
    lateinit var  vm : titipangonViewModel
    lateinit var dialog : Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(titipangonViewModel::class.java)
        dialog = progressDialog(this@detail_kelahiran_titip_angon)
        binding.ivBack.setOnClickListener { finish() }

        tampil()
    }
    private  fun tampil(){
        lifecycleScope.launch {
            dialog.show()
            try {
                val authResponse = vm.detail_kelahiran(intent.getStringExtra("id").toString())
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result =authResponse.body()!!.data
                    var foto = foto_kambing +result.fv_image
                    Glide.with(this@detail_kelahiran_titip_angon)
                        .load(foto)
                        .into(binding.ivGambar)
                    val x = result.fd_birthdate!!.split(" ")
                    val y = x[0].split("-")
                    val c = y[2]+"-"+y[1]+"-"+y[0]
                    binding.tvBerat.text = result.fn_weight
                    binding.tvJenisKelamin.text = result.fn_gender
                    binding.tvKeterangan.text  = result.fv_characteristics
                    binding.tvKeturunan.text = result.fn_perentid
                    binding.tvKodeKambing.text = result.fv_codesheep
                    binding.tvTanggalLahir.text = c
                    binding.tvTinggi.text = result.fn_height
                    binding.tvTypeKambing.text = result.fn_sheeptype
                    binding.tvUmur.text = result.fn_age
                    binding.ivGambar.setOnClickListener {
                        startActivity(Intent(this@detail_kelahiran_titip_angon,com.izzancode.kompoengdomba.view.foto::class.java).putExtra("foto", foto_kambing +result.fv_image))
                    }
                } else {
                    dialog.dismiss()
                    Toast.makeText(
                        this@detail_kelahiran_titip_angon,
                        authResponse.body()!!.pesan,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (throwable: Throwable) {
                dialog.dismiss()
                Log.e("ERROR", throwable.toString())
            }
        }
    }
}