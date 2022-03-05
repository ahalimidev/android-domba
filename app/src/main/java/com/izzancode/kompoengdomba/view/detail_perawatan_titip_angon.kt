package com.izzancode.kompoengdomba.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.izzancode.kompoengdomba.adapter.detail_perawatan_kandangkuAdapter
import com.izzancode.kompoengdomba.adapter.detail_perawatan_titip_angonAdapter
import com.izzancode.kompoengdomba.databinding.ActivityDetailPerawatanKandangkuBinding
import com.izzancode.kompoengdomba.databinding.CostumFilterPeriodeBinding
import com.izzancode.kompoengdomba.utils.foto_kambing
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.kandangkuViewModel
import com.izzancode.kompoengdomba.viewmodel.titipangonViewModel
import kotlinx.coroutines.launch
import java.util.*


class detail_perawatan_titip_angon : AppCompatActivity() {
    private  val binding by lazy { ActivityDetailPerawatanKandangkuBinding.inflate(layoutInflater) }
    lateinit var  vm : titipangonViewModel
    lateinit var dialog : Dialog
    private var rKandangkuAdapter : detail_perawatan_titip_angonAdapter? = null
    var tgl_awal : String = ""
    var tgl_akhir : String = ""
    var bulan : String = ""
    var kondisi : String = "ASC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(titipangonViewModel::class.java)
        dialog = progressDialog(this@detail_perawatan_titip_angon)
        binding.ivBack.setOnClickListener { finish() }
        binding.llUrutkan.setOnClickListener {
            if (kondisi == "ASC") {
                kondisi = "DESC"
            } else if (kondisi == "DESC") {
                kondisi = "ASC"
            }
            tampil_filter(tgl_awal, tgl_akhir, kondisi)
        }
        binding.llFilter.setOnClickListener {
            dialog()
        }
        tampil()
    }
    private fun dialog() {
        val dialog = Dialog(this@detail_perawatan_titip_angon)
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
                    this@detail_perawatan_titip_angon,
                    "Pilih periode Perawatan",
                    Toast.LENGTH_LONG
                ).show()
                dialog.dismiss()
            } else {
                tampil_filter(tgl_awal, tgl_akhir, kondisi)
            }
        }
        dialog.show()
    }
    private fun tampil (){
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.detail_perawatan(intent.getStringExtra("id").toString())
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result_one = authResponse.body()!!.data_one
                    val result_all = authResponse.body()!!.data_all
                    var foto = foto_kambing +result_one.fv_image
                    Glide.with(this@detail_perawatan_titip_angon)
                        .load(foto)
                        .into(binding.ivGambar)
                    binding.tvBerat.text = result_one.fn_weight
                    binding.tvTinggi.text = result_one.fn_height
                    binding.tvJenisKelamin.text = result_one.fn_gender
                    binding.tvKodeKambing.text = result_one.fv_codesheep
                    binding.tvTypeKambing.text = result_one.fn_sheeptype
                    binding.tvUmur.text = result_one.fn_age
                    val linearLayoutManager = LinearLayoutManager(this@detail_perawatan_titip_angon, LinearLayoutManager.VERTICAL, false)
                    linearLayoutManager.scrollToPositionWithOffset(0, 0)
                    rKandangkuAdapter = detail_perawatan_titip_angonAdapter(this@detail_perawatan_titip_angon,result_all)
                    binding.recyclerView.setLayoutManager(linearLayoutManager)
                    binding.recyclerView.setAdapter(rKandangkuAdapter)
                    binding.recyclerView.setNestedScrollingEnabled(false)
                    binding.recyclerView.setHasFixedSize(false)
                    rKandangkuAdapter!!.notifyDataSetChanged()
                    binding.ivGambar.setOnClickListener {
                        startActivity(Intent(this@detail_perawatan_titip_angon,com.izzancode.kompoengdomba.view.foto::class.java).putExtra("foto", foto_kambing +result_one.fv_image))
                    }
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
    private fun tampil_filter(awal: String, akhir: String, kondisi: String) {
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.filter_perawatan(
                    intent.getStringExtra("id").toString(),
                    awal,
                    akhir,
                    kondisi
                )
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result_one = authResponse.body()!!.data_one
                    val result_all = authResponse.body()!!.data_all
                    var foto = foto_kambing +result_one.fv_image
                    Glide.with(this@detail_perawatan_titip_angon)
                        .load(foto)
                        .into(binding.ivGambar)
                    binding.tvBerat.text = result_one.fn_weight
                    binding.tvTinggi.text = result_one.fn_height
                    binding.tvJenisKelamin.text = result_one.fn_gender
                    binding.tvKodeKambing.text = result_one.fv_codesheep
                    binding.tvTypeKambing.text = result_one.fn_sheeptype
                    binding.tvUmur.text = result_one.fn_age
                    val linearLayoutManager = LinearLayoutManager(this@detail_perawatan_titip_angon, LinearLayoutManager.VERTICAL, false)
                    linearLayoutManager.scrollToPositionWithOffset(0, 0)
                    rKandangkuAdapter = detail_perawatan_titip_angonAdapter(this@detail_perawatan_titip_angon,result_all)
                    binding.recyclerView.setLayoutManager(linearLayoutManager)
                    binding.recyclerView.setAdapter(rKandangkuAdapter)
                    binding.recyclerView.setNestedScrollingEnabled(false)
                    binding.recyclerView.setHasFixedSize(false)
                    rKandangkuAdapter!!.notifyDataSetChanged()
                    binding.ivGambar.setOnClickListener {
                        startActivity(Intent(this@detail_perawatan_titip_angon,com.izzancode.kompoengdomba.view.foto::class.java).putExtra("foto", foto_kambing +result_one.fv_image))
                    }
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