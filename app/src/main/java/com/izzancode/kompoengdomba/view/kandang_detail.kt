package com.izzancode.kompoengdomba.view

import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.izzancode.kompoengdomba.adapter.kandangAdapter
import com.izzancode.kompoengdomba.databinding.ActivityKandangDetailBinding
import com.izzancode.kompoengdomba.utils.foto_kandang
import com.izzancode.kompoengdomba.utils.foto_profil
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.kandangViewModel
import kotlinx.coroutines.launch

class kandang_detail : AppCompatActivity() {
    private val binding by lazy { ActivityKandangDetailBinding.inflate(layoutInflater) }
    lateinit var vm : kandangViewModel
    lateinit var dialog : Dialog
    private var rAdapter : kandangAdapter? = null
    var status : String = "D"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(kandangViewModel::class.java)
        dialog = progressDialog(this@kandang_detail)
        tampil_kandang(status)
        tampil_kandang_detail()
    }
    private fun tampil_kandang (status : String){
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.kandang(status)
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result = authResponse.body()!!.data
                    rAdapter = kandangAdapter(this@kandang_detail,result)
                    if (this@kandang_detail.resources.getConfiguration().orientation === Configuration.ORIENTATION_PORTRAIT) {
                        binding.rvTampil.setLayoutManager(GridLayoutManager(this@kandang_detail, 3))
                    } else {
                        binding.rvTampil.setLayoutManager(GridLayoutManager(this@kandang_detail, 6))
                    }
                    binding.rvTampil.setAdapter(rAdapter)
                    rAdapter!!.notifyDataSetChanged()
                }else{
                    dialog.dismiss()

                    rAdapter!!.clear()
                }
            } catch (throwable: Throwable) {
                dialog.dismiss()
                Log.e("ERROR",throwable.toString())
            }
        }
    }
    private fun tampil_kandang_detail (){
        lifecycleScope.launch {
            try {
                val authResponse = vm.kandang_detail(intent.getStringExtra("status").toString(),intent.getStringExtra("id").toString())
                if (authResponse.body()!!.success == 1) {
                    val result = authResponse.body()!!.data
                    val foto = foto_kandang +result[0].fv_picture
                    if(result[0].fc_status == "D"){
                        binding.ivGambarX.visibility = View.GONE
                    }else{
                        binding.ivGambarX.visibility = View.VISIBLE

                    }
                    Glide.with(this@kandang_detail)
                        .load(foto)
                        .into(binding.ivGambar)
                    binding.tvHarga.text = result[0].fm_price
                    binding.tvKambing.text = result[0].fn_totalsheep
                    binding.tvKeteraangan.text = result[0].fv_desc
                    binding.tvNamaType.text = result[0].fv_namekav
                    binding.tvTypeKandang.text = result[0].fm_price
                    binding.tvUkur.text = result[0].fn_size
                    binding.llFoto.setOnClickListener {
                        startActivity(Intent(this@kandang_detail,com.izzancode.kompoengdomba.view.foto::class.java).putExtra("foto", foto_kandang +result[0].fv_picture))
                    }
                }
            } catch (throwable: Throwable) {
                Log.e("ERROR",throwable.toString())
            }
        }
    }
}