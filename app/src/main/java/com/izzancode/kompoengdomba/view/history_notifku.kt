package com.izzancode.kompoengdomba.view

import android.app.Dialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.izzancode.kompoengdomba.adapter.notifAdapter
import com.izzancode.kompoengdomba.adapter.saldoAdapter
import com.izzancode.kompoengdomba.databinding.ActivityHistoryNotifkuBinding
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.customerViewModel
import kotlinx.coroutines.launch

class history_notifku : AppCompatActivity() {
    private val binding by lazy { ActivityHistoryNotifkuBinding.inflate(layoutInflater) }
    lateinit var vm : customerViewModel
    lateinit var dialog : Dialog
    private var rAdapter : notifAdapter? = null
    var days : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(customerViewModel::class.java)
        dialog = progressDialog(this@history_notifku)
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.tvHari1.setOnClickListener {
            days = "1"
            tampil_notif(days)
            binding.tvHari1.setTextColor(Color.parseColor("#ffffff"))
            binding.tvHari1.setBackgroundColor(Color.parseColor("#6FD94E"))
            binding.tvHari7.setTextColor(Color.parseColor("#FF922C"))
            binding.tvHari7.setBackgroundColor(Color.parseColor("#DEF2D7"))
            binding.tvHari14.setTextColor(Color.parseColor("#FF922C"))
            binding.tvHari14.setBackgroundColor(Color.parseColor("#DEF2D7"))
            binding.tvHari30.setTextColor(Color.parseColor("#FF922C"))
            binding.tvHari30.setBackgroundColor(Color.parseColor("#DEF2D7"))
        }
        binding.tvHari7.setOnClickListener {
            days = "7"
            tampil_notif(days)
            binding.tvHari1.setTextColor(Color.parseColor("#FF922C"))
            binding.tvHari1.setBackgroundColor(Color.parseColor("#DEF2D7"))
            binding.tvHari7.setTextColor(Color.parseColor("#ffffff"))
            binding.tvHari7.setBackgroundColor(Color.parseColor("#6FD94E"))
            binding.tvHari14.setTextColor(Color.parseColor("#FF922C"))
            binding.tvHari14.setBackgroundColor(Color.parseColor("#DEF2D7"))
            binding.tvHari30.setTextColor(Color.parseColor("#FF922C"))
            binding.tvHari30.setBackgroundColor(Color.parseColor("#DEF2D7"))
        }
        binding.tvHari14.setOnClickListener {
            days = "14"
            tampil_notif(days)
            binding.tvHari1.setTextColor(Color.parseColor("#FF922C"))
            binding.tvHari1.setBackgroundColor(Color.parseColor("#DEF2D7"))
            binding.tvHari7.setTextColor(Color.parseColor("#FF922C"))
            binding.tvHari7.setBackgroundColor(Color.parseColor("#DEF2D7"))
            binding.tvHari14.setTextColor(Color.parseColor("#ffffff"))
            binding.tvHari14.setBackgroundColor(Color.parseColor("#6FD94E"))
            binding.tvHari30.setTextColor(Color.parseColor("#FF922C"))
            binding.tvHari30.setBackgroundColor(Color.parseColor("#DEF2D7"))
        }
        binding.tvHari30.setOnClickListener {
            days = "30"
            tampil_notif(days)
            binding.tvHari1.setTextColor(Color.parseColor("#FF922C"))
            binding.tvHari1.setBackgroundColor(Color.parseColor("#DEF2D7"))
            binding.tvHari7.setTextColor(Color.parseColor("#FF922C"))
            binding.tvHari7.setBackgroundColor(Color.parseColor("#DEF2D7"))
            binding.tvHari14.setTextColor(Color.parseColor("#FF922C"))
            binding.tvHari14.setBackgroundColor(Color.parseColor("#DEF2D7"))
            binding.tvHari30.setTextColor(Color.parseColor("#ffffff"))
            binding.tvHari30.setBackgroundColor(Color.parseColor("#6FD94E"))
        }
        tampil_notif("")
    }
    private fun tampil_notif (days : String){
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.notif(this@history_notifku,days)
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result = authResponse.body()!!.data
                    val linearLayoutManager = LinearLayoutManager(this@history_notifku, LinearLayoutManager.VERTICAL, false)
                    linearLayoutManager.scrollToPositionWithOffset(0, 0)
                    rAdapter = notifAdapter(this@history_notifku,result)
                    binding.rvTampil.setLayoutManager(linearLayoutManager)
                    binding.rvTampil.setAdapter(rAdapter)
                    rAdapter!!.notifyDataSetChanged()
                }else{
                    rAdapter!!.clear()
                    dialog.dismiss()
                }
            } catch (throwable: Throwable) {
                dialog.dismiss()
                Log.e("ERROR",throwable.toString())
            }
        }
    }
}