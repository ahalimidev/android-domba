package com.izzancode.kompoengdomba.view

import android.app.Dialog
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.izzancode.kompoengdomba.adapter.list_ternak_kandangkuAdapter
import com.izzancode.kompoengdomba.databinding.ActivityListTernakKandangkuBinding
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.kandangkuViewModel
import kotlinx.coroutines.launch

class list_ternak_kandangku : AppCompatActivity() {
    private val binding by lazy { ActivityListTernakKandangkuBinding.inflate(layoutInflater) }
    lateinit var  vm : kandangkuViewModel
    lateinit var dialog : Dialog
    private var rKandangkuAdapter : list_ternak_kandangkuAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(kandangkuViewModel::class.java)
        dialog = progressDialog(this@list_ternak_kandangku)
        binding.ivBack.setOnClickListener { finish() }

        tampil_kandang()
    }

    private fun tampil_kandang (){
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.list_ternak(intent.getStringExtra("fn_kavid").toString())
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result = authResponse.body()!!.data
                    rKandangkuAdapter = list_ternak_kandangkuAdapter(this@list_ternak_kandangku,result)
                    if (this@list_ternak_kandangku.resources.getConfiguration().orientation === Configuration.ORIENTATION_PORTRAIT) {
                        binding.rvTampil.setLayoutManager(GridLayoutManager(this@list_ternak_kandangku, 2))
                    } else {
                        binding.rvTampil.setLayoutManager(GridLayoutManager(this@list_ternak_kandangku, 4))
                    }
                    binding.rvTampil.setAdapter(rKandangkuAdapter)
                    rKandangkuAdapter!!.notifyDataSetChanged()
                }else{
                    dialog.dismiss()

                    rKandangkuAdapter!!.clear()
                }
            } catch (throwable: Throwable) {
                dialog.dismiss()
                Log.e("ERROR",throwable.toString())
            }
        }
    }
}