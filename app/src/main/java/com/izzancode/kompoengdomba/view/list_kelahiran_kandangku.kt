package com.izzancode.kompoengdomba.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.izzancode.kompoengdomba.adapter.list_kelahiran_kandangkuAdapter
import com.izzancode.kompoengdomba.databinding.ActivityListKelahiranKandangkuBinding
import com.izzancode.kompoengdomba.databinding.CostumFilterPeriodeBinding
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.kandangkuViewModel
import kotlinx.coroutines.launch
import java.util.*

class list_kelahiran_kandangku : AppCompatActivity() {
    private val binding by lazy { ActivityListKelahiranKandangkuBinding.inflate(layoutInflater) }
    lateinit var vm: kandangkuViewModel
    lateinit var dialog: Dialog
    var tgl_awal : String = ""
    var tgl_akhir : String = ""
    var bulan : String = ""
    var kondisi : String = "ASC"
    private var rKandangkuAdapter : list_kelahiran_kandangkuAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(kandangkuViewModel::class.java)
        dialog = progressDialog(this@list_kelahiran_kandangku)
        binding.ivBack.setOnClickListener { finish() }
        binding.llFilter.setOnClickListener {
            dialog()
        }
        binding.llUrutkan.setOnClickListener {
            if (kondisi == "ASC") {
                kondisi = "DESC"
            } else if (kondisi == "DESC") {
                kondisi = "ASC"
            }
            tampil_filter(tgl_awal, tgl_akhir, kondisi)
        }
        tampil()
    }

    private fun dialog() {
        val dialog = Dialog(this@list_kelahiran_kandangku)
        val x by lazy { CostumFilterPeriodeBinding.inflate(layoutInflater) }
        dialog.setContentView(x.root)
        x.tvAwal.setOnClickListener {
            val c = Calendar.getInstance()
            val mYear = c.get(Calendar.YEAR)
            val mMonth = c.get(Calendar.MONTH)
            val mDay = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this, { view, year, monthOfYear, dayOfMonth ->
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
                    x.tvAwal.text = "$dayOfMonth-$bulan-$year"
                    tgl_awal = "$year-$bulan-$dayOfMonth"
                    if (tgl_akhir == "") {

                    } else if (tgl_awal == "") {

                    } else {

                    }
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }
        x.tvAkhir.setOnClickListener {
            val c = Calendar.getInstance()
            val mYear = c.get(Calendar.YEAR)
            val mMonth = c.get(Calendar.MONTH)
            val mDay = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this, { view, year, monthOfYear, dayOfMonth ->
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
                    x.tvAkhir.text = "$dayOfMonth-$bulan-$year"
                    tgl_akhir = "$year-$bulan-$dayOfMonth"
                    if (tgl_akhir == "") {

                    } else if (tgl_awal == "") {

                    } else {

                    }
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }
        x.btKirim.setOnClickListener {
            if (tgl_awal == "" && tgl_akhir == "") {
                Toast.makeText(
                    this@list_kelahiran_kandangku,
                    "Pilih periode kelahiran",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                tampil_filter(tgl_awal, tgl_akhir, kondisi)
            }
        }
        dialog.show()
    }

    private fun tampil() {
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.list_kelahiran(intent.getStringExtra("fn_kavid").toString())
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result = authResponse.body()!!.data
                    rKandangkuAdapter = list_kelahiran_kandangkuAdapter(this@list_kelahiran_kandangku, result)
                    if (this@list_kelahiran_kandangku.resources.getConfiguration().orientation === Configuration.ORIENTATION_PORTRAIT) {
                        binding.rvTampil.setLayoutManager(
                            GridLayoutManager(
                                this@list_kelahiran_kandangku,
                                2
                            )
                        )
                    } else {
                        binding.rvTampil.setLayoutManager(
                            GridLayoutManager(
                                this@list_kelahiran_kandangku,
                                4
                            )
                        )
                    }
                    binding.rvTampil.setAdapter(rKandangkuAdapter)
                    rKandangkuAdapter!!.notifyDataSetChanged()
                } else {
                    dialog.dismiss()

                    rKandangkuAdapter!!.clear()
                }
            } catch (throwable: Throwable) {
                dialog.dismiss()
                Log.e("ERROR", throwable.toString())
            }
        }
    }

    private fun tampil_filter(awal: String, akhir: String, kondisi: String) {
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.filter_kelahiran(
                    intent.getStringExtra("fn_kavid").toString(),
                    awal,
                    akhir,
                    kondisi
                )
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result = authResponse.body()!!.data
                    rKandangkuAdapter = list_kelahiran_kandangkuAdapter(this@list_kelahiran_kandangku, result)
                    if (this@list_kelahiran_kandangku.resources.getConfiguration().orientation === Configuration.ORIENTATION_PORTRAIT) {
                        binding.rvTampil.setLayoutManager(
                            GridLayoutManager(
                                this@list_kelahiran_kandangku,
                                2
                            )
                        )
                    } else {
                        binding.rvTampil.setLayoutManager(
                            GridLayoutManager(
                                this@list_kelahiran_kandangku,
                                4
                            )
                        )
                    }
                    binding.rvTampil.setAdapter(rKandangkuAdapter)
                    rKandangkuAdapter!!.notifyDataSetChanged()
                } else {
                    dialog.dismiss()

                    rKandangkuAdapter!!.clear()
                }
            } catch (throwable: Throwable) {
                dialog.dismiss()
                Log.e("ERROR", throwable.toString())
            }
        }
    }

}