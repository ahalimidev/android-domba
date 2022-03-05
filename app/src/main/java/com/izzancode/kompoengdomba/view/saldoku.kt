package com.izzancode.kompoengdomba.view

import android.app.DatePickerDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.izzancode.kompoengdomba.R
import com.izzancode.kompoengdomba.adapter.saldoAdapter
import com.izzancode.kompoengdomba.databinding.ActivitySaldokuBinding
import com.izzancode.kompoengdomba.utils.foto_profil
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.customerViewModel
import com.izzancode.kompoengdomba.viewmodel.saldoViewModel
import kotlinx.coroutines.launch
import java.util.*

class saldoku : AppCompatActivity() {
    private val binding by lazy { ActivitySaldokuBinding.inflate(layoutInflater) }
    lateinit var vm: saldoViewModel
    lateinit var dialog : Dialog
    private var rAdapter : saldoAdapter? = null
    var tgl_awal : String = ""
    var tgl_akhir : String = ""
    var bulan : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(saldoViewModel::class.java)
        dialog = progressDialog(this@saldoku)
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.tvAwal.setOnClickListener {
            val c = Calendar.getInstance()
            val mYear = c.get(Calendar.YEAR)
            val mMonth = c.get(Calendar.MONTH)
            val mDay = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
                    val data = (monthOfYear + 1).toString()
                    if ("1" == data) {
                        bulan = "01"
                    } else if ("2" == data) {
                        bulan = "02"
                    } else if ("3" == data) {
                        bulan = "03"
                    } else if ("4" == data) {
                        bulan = "04"
                    } else if ("5" == data) {
                        bulan = "05"
                    } else if ("6" == data) {
                        bulan = "06"
                    } else if ("7" == data) {
                        bulan = "07"
                    } else if ("8" == data) {
                        bulan = "08"
                    } else if ("9" == data) {
                        bulan = "09"
                    } else if ("10" == data) {
                        bulan = "10"
                    } else if ("11" == data) {
                        bulan = "11"
                    } else if ("12" == data) {
                        bulan = "12"
                    }
                    binding.tvAwal.text = "$dayOfMonth-$bulan-$year"
                    tgl_awal = "$year-$bulan-$dayOfMonth"
                    if (tgl_akhir == "") {

                    } else if (tgl_awal == "") {

                    } else {
                        tampil_materi(tgl_awal,tgl_akhir)

                    }
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }
        binding.tvAkhir.setOnClickListener {
            val c = Calendar.getInstance()
            val mYear = c.get(Calendar.YEAR)
            val mMonth = c.get(Calendar.MONTH)
            val mDay = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
                    val data =  (monthOfYear + 1).toString()
                    if ("1" == data) {
                        bulan = "01"
                    } else if ("2" == data) {
                        bulan = "02"
                    } else if ("3" == data) {
                        bulan = "03"
                    } else if ("4" == data) {
                        bulan = "04"
                    } else if ("5" == data) {
                        bulan = "05"
                    } else if ("6" == data) {
                        bulan = "06"
                    } else if ("7" == data) {
                        bulan = "07"
                    } else if ("8" == data) {
                        bulan = "08"
                    } else if ("9" == data) {
                        bulan = "09"
                    } else if ("10" == data) {
                        bulan = "10"
                    } else if ("11" == data) {
                        bulan = "11"
                    } else if ("12" == data) {
                        bulan = "12"
                    }
                    binding.tvAkhir.text = "$dayOfMonth-$bulan-$year"
                    tgl_akhir = "$year-$bulan-$dayOfMonth"
                    if (tgl_akhir == ""){

                    }else if (tgl_awal ==""){

                    }else{
                        tampil_materi(tgl_awal,tgl_akhir)

                    }
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }
        total_saldo()
        tampil_materi("","")
    }
    private  fun total_saldo(){
        lifecycleScope.launch {
            try {
                val authResponse = vm.saldo_total(this@saldoku)
                binding.totalSaldo. text = authResponse.body()!!.total

            } catch (throwable: Throwable) {

                Log.e("ERROR", throwable.toString())
            }
        }
    }
    private fun tampil_materi (awal : String, akhir : String){
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.saldo_detail(this@saldoku,awal,akhir)
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result = authResponse.body()!!.data
                    val linearLayoutManager = LinearLayoutManager(this@saldoku, LinearLayoutManager.VERTICAL, false)
                    linearLayoutManager.scrollToPositionWithOffset(0, 0)
                    rAdapter = saldoAdapter(this@saldoku,result)
                    binding.rvTampil.setLayoutManager(linearLayoutManager)
                    binding.rvTampil.setAdapter(rAdapter)
                    rAdapter!!.notifyDataSetChanged()
                }else{
                    dialog.dismiss()
                }
            } catch (throwable: Throwable) {
                dialog.dismiss()
                Log.e("ERROR",throwable.toString())
            }
        }
    }
}
