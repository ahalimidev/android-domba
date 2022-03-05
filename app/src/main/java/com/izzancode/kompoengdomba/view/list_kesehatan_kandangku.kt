package com.izzancode.kompoengdomba.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.izzancode.kompoengdomba.adapter.list_kesehatan_kandangkuAdapter
import com.izzancode.kompoengdomba.databinding.ActivityListKesehatanKandangkuBinding
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.kandangkuViewModel
import kotlinx.coroutines.launch

class list_kesehatan_kandangku : AppCompatActivity() {
    private val binding by lazy { ActivityListKesehatanKandangkuBinding.inflate(layoutInflater) }
    lateinit var  vm : kandangkuViewModel
    lateinit var dialog : Dialog
    private var rAdapter : list_kesehatan_kandangkuAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(kandangkuViewModel::class.java)
        dialog = progressDialog(this@list_kesehatan_kandangku)
        binding.ivBack.setOnClickListener { finish() }
        tampil()
    }
    private fun tampil (){
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.list_kesehatan(intent.getStringExtra("fn_kavid").toString())
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result = authResponse.body()!!.data
                    val linearLayoutManager = LinearLayoutManager(this@list_kesehatan_kandangku, LinearLayoutManager.VERTICAL, false)
                    linearLayoutManager.scrollToPositionWithOffset(0, 0)
                    rAdapter = list_kesehatan_kandangkuAdapter(this@list_kesehatan_kandangku,result)
                    binding.rvTampil.setLayoutManager(linearLayoutManager)
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
}