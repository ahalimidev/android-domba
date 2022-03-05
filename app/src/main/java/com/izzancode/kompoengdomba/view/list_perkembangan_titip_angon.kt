package com.izzancode.kompoengdomba.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.izzancode.kompoengdomba.adapter.list_perkembangan_kandangkuAdapter
import com.izzancode.kompoengdomba.adapter.list_perkembangan_titip_angonAdapter
import com.izzancode.kompoengdomba.databinding.ActivityListPerkembanganKandangkuBinding
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.kandangkuViewModel
import com.izzancode.kompoengdomba.viewmodel.titipangonViewModel
import kotlinx.coroutines.launch

class list_perkembangan_titip_angon : AppCompatActivity() {
    private val binding by lazy { ActivityListPerkembanganKandangkuBinding.inflate(layoutInflater) }
    lateinit var  vm : titipangonViewModel
    lateinit var dialog : Dialog
    private var rKandangkuAdapter : list_perkembangan_titip_angonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(titipangonViewModel::class.java)
        dialog = progressDialog(this@list_perkembangan_titip_angon)
        binding.ivBack.setOnClickListener { finish() }
        tampil()
    }
    private fun tampil (){
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.list_perkembangan(this@list_perkembangan_titip_angon)
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result = authResponse.body()!!.data
                    val linearLayoutManager = LinearLayoutManager(this@list_perkembangan_titip_angon, LinearLayoutManager.VERTICAL, false)
                    linearLayoutManager.scrollToPositionWithOffset(0, 0)
                    rKandangkuAdapter = list_perkembangan_titip_angonAdapter(this@list_perkembangan_titip_angon,result)
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