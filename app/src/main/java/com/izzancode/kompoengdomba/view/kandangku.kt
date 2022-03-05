package com.izzancode.kompoengdomba.view

import android.app.Dialog
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.izzancode.kompoengdomba.adapter.kandangAdapter
import com.izzancode.kompoengdomba.adapter.kandangkuAdapter
import com.izzancode.kompoengdomba.adapter.notifAdapter
import com.izzancode.kompoengdomba.databinding.ActivityKandangkuBinding
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.kandangViewModel
import com.izzancode.kompoengdomba.viewmodel.kandangkuViewModel
import kotlinx.coroutines.launch

class kandangku : AppCompatActivity() {
    private val binding by lazy { ActivityKandangkuBinding.inflate(layoutInflater) }
    lateinit var vm : kandangkuViewModel
    lateinit var dialog : Dialog
    private var rAdapter : kandangkuAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(kandangkuViewModel::class.java)
        dialog = progressDialog(this@kandangku)
        binding.ivBack.setOnClickListener { finish() }
        tampil_kandangku()
    }
    private fun tampil_kandangku (){
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.kandangku(this@kandangku)
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result = authResponse.body()!!.data
                    val linearLayoutManager = LinearLayoutManager(this@kandangku, LinearLayoutManager.VERTICAL, false)
                    linearLayoutManager.scrollToPositionWithOffset(0, 0)
                    rAdapter = kandangkuAdapter(this@kandangku,result)
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