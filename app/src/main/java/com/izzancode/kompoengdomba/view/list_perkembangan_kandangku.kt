package com.izzancode.kompoengdomba.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.izzancode.kompoengdomba.adapter.list_perkembangan_kandangkuAdapter
import com.izzancode.kompoengdomba.databinding.ActivityListPerkembanganKandangkuBinding
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.kandangkuViewModel
import kotlinx.coroutines.launch

class list_perkembangan_kandangku : AppCompatActivity() {
    private val binding by lazy { ActivityListPerkembanganKandangkuBinding.inflate(layoutInflater) }
    lateinit var  vm : kandangkuViewModel
    lateinit var dialog : Dialog
    private var rKandangkuAdapter : list_perkembangan_kandangkuAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(kandangkuViewModel::class.java)
        dialog = progressDialog(this@list_perkembangan_kandangku)
        binding.ivBack.setOnClickListener { finish() }
        tampil()
    }
    private fun tampil (){
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.list_perkembangan(intent.getStringExtra("fn_kavid").toString())
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result = authResponse.body()!!.data
                    val linearLayoutManager = LinearLayoutManager(this@list_perkembangan_kandangku, LinearLayoutManager.VERTICAL, false)
                    linearLayoutManager.scrollToPositionWithOffset(0, 0)
                    rKandangkuAdapter = list_perkembangan_kandangkuAdapter(this@list_perkembangan_kandangku,result)
                    binding.rvTampil.setLayoutManager(linearLayoutManager)
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