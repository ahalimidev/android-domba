package com.izzancode.kompoengdomba.view

import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.izzancode.kompoengdomba.adapter.kandangAdapter
import com.izzancode.kompoengdomba.databinding.ActivityKandangBinding
import com.izzancode.kompoengdomba.databinding.CostumFilterKandangBinding
import com.izzancode.kompoengdomba.model.data.kandang
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.kandangViewModel
import kotlinx.coroutines.launch


class kandang : AppCompatActivity() {
    private val binding by lazy { ActivityKandangBinding.inflate(layoutInflater) }
    lateinit var vm : kandangViewModel
    lateinit var dialog : Dialog
    private var rAdapter : kandangAdapter? = null
    var status : String = "D"
     var  id_spinner :ArrayList<String> = ArrayList()
     var  nama_spinner :ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(kandangViewModel::class.java)
        dialog = progressDialog(this@kandang)
        binding.ivBack.setOnClickListener { finish() }
        binding.tvDijual.setOnClickListener {
            status = "D"
            tampil_kandang(status)
            binding.tvDijual.setTextColor(Color.parseColor("#ffffff"))
            binding.tvDijual.setBackgroundColor(Color.parseColor("#6FD94E"))
            binding.tvTerjual.setTextColor(Color.parseColor("#BEBEBE"))
            binding.tvTerjual.setBackgroundColor(Color.parseColor("#DEF2D7"))
        }
        binding.tvTerjual.setOnClickListener {

            status = "S"
            binding.tvTerjual.setTextColor(Color.parseColor("#ffffff"))
            binding.tvTerjual.setBackgroundColor(Color.parseColor("#6FD94E"))
            binding.tvDijual.setTextColor(Color.parseColor("#BEBEBE"))
            binding.tvDijual.setBackgroundColor(Color.parseColor("#DEF2D7"))
            tampil_kandang(status)
        }
        binding.ivFilter.setOnClickListener {
            kandang_filter()
        }
        binding.ivPencarian.setOnClickListener {
           if(!binding.etName.text.toString().isEmpty()){
               tampil_kandang_pencarian(status,binding.etName.toString())
           }
        }
        binding.etName.setOnKeyListener { view, i, keyEvent ->
            if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                if(!binding.etName.text.toString().isEmpty()){
                    tampil_kandang_pencarian(status,binding.etName.toString())
                }
            }
            false
        }
        tampil_kandang(status)
        type_kandang()
    }
    private fun tampil_kandang (status : String){
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.kandang(status)
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result = authResponse.body()!!.data
                    rAdapter = kandangAdapter(this@kandang,result)
                    if (this@kandang.resources.getConfiguration().orientation === Configuration.ORIENTATION_PORTRAIT) {
                        binding.rvTampil.setLayoutManager(GridLayoutManager(this@kandang, 3))
                    } else {
                        binding.rvTampil.setLayoutManager(GridLayoutManager(this@kandang, 6))
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
    private fun tampil_kandang_pencarian (status : String, name : String){
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.pencarian_kandang(status, name)
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result = authResponse.body()!!.data

                    rAdapter = kandangAdapter(this@kandang,result)
                    if (this@kandang.resources.getConfiguration().orientation === Configuration.ORIENTATION_PORTRAIT) {
                        binding.rvTampil.setLayoutManager(GridLayoutManager(this@kandang, 2))
                    } else {
                        binding.rvTampil.setLayoutManager(GridLayoutManager(this@kandang, 4))
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
    private  fun kandang_filter(){
        var id_type_kandang = ""
        var kondisi = ""
        val x by lazy { CostumFilterKandangBinding.inflate(layoutInflater) }

        val dialog = Dialog(this@kandang)
        dialog.setContentView(x.root)
        x.spKandang.adapter = ArrayAdapter(this@kandang,android.R.layout.simple_spinner_dropdown_item,nama_spinner)
        x.spKandang.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(arg0: AdapterView<*>?, view: View?, arg2: Int, arg3: Long) {
                if(id_spinner[arg2] != "0"){
                    id_type_kandang = id_spinner[arg2]
                    Log.e("DSSSS",id_type_kandang)
                }
                Log.d("DSSSS",id_type_kandang)

            }
            override fun onNothingSelected(arg0: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        })
        x.rgKondisi.setOnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == x.rbTerbaru.id){
                kondisi = "DESC"
            }else if (checkedId == x.rbTerlama.id){
                kondisi = "ASC"
            }
        }
        x.btPakai.setOnClickListener {
            filter(id_type_kandang,kondisi,
                x.tvBatasMin.text.toString(),x.tvBatasMin.text.toString(),
                x.tvKapasitasMin.text.toString(),x.tvKapasitasMax.text.toString(),
                x.tvHargaMin.text.toString(),x.tvHargaMax.text.toString(),
            )
            dialog.dismiss()

        }
        x.btUlang.setOnClickListener {
            val i = Intent(this@kandang, com.izzancode.kompoengdomba.view.kandang::class.java)
            finish()
            overridePendingTransition(0, 0)
            startActivity(i)
            overridePendingTransition(0, 0)
            dialog.dismiss()
        }
        dialog.show()
    }
    private fun filter(kandang : String,kondisi : String,
                       batas_min : String, batas_max : String,
                       kapasitas_min : String, kapasitas_max : String,
                       harga_min : String, harga_max : String){
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.filter_kandang(status,kandang, kondisi, batas_min, batas_max, kapasitas_min, kapasitas_max, harga_min, harga_max)
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result = authResponse.body()!!.data
                    rAdapter = kandangAdapter(this@kandang,result)
                    if (this@kandang.resources.getConfiguration().orientation === Configuration.ORIENTATION_PORTRAIT) {
                        binding.rvTampil.setLayoutManager(GridLayoutManager(this@kandang, 3))
                    } else {
                        binding.rvTampil.setLayoutManager(GridLayoutManager(this@kandang, 6))
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
    private fun type_kandang(){
        lifecycleScope.launch {
            try {
                val authResponse = vm.type_kandang()
                if (authResponse.body()!!.success == 1) {
                    val contacts: ArrayList<kandang> = ArrayList()

                    id_spinner.clear()
                    nama_spinner.clear()
                    id_spinner.add("0")
                    nama_spinner.add("Pilih Type")
                    val result = authResponse.body()!!.data
                    for (i in 0 until result.size) {
                        result[i].fn_typekavid?.let { id_spinner.add(it) }
                        result[i].fv_configname?.let { nama_spinner.add(it) }
                    }

                }
            } catch (throwable: Throwable) {
                Log.e("ERROR",throwable.toString())
            }
        }
    }
}