package com.izzancode.kompoengdomba.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.izzancode.kompoengdomba.R
import com.izzancode.kompoengdomba.databinding.ActivityGantiPasswordBinding
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.customerViewModel
import kotlinx.coroutines.launch

class ganti_password : AppCompatActivity() {
    private val binding by lazy { ActivityGantiPasswordBinding.inflate(layoutInflater) }
    lateinit var dialog : Dialog
    lateinit var vm: customerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(customerViewModel::class.java)

        dialog = progressDialog(this@ganti_password)
        binding.ivBack.setOnClickListener {
            finish()
        }
        Glide.with(this@ganti_password)
            .load(intent.getStringExtra("foto"))
            .into(binding.profileImage)

        binding.btSimpan.setOnClickListener {
            if(binding.etPassword.text.toString().isEmpty()) {
                binding.etPassword.requestFocus()
                binding.etPassword.error = "Kosong"

            }else if(binding.etPasswordUlang.text.toString().isEmpty()) {
                binding.etPasswordUlang.requestFocus()
                binding.etPasswordUlang.error = "Kosong"

            }else if(binding.etPassword.text.toString().equals(binding.etPasswordUlang.text.toString())){
                dialog.show()
                lifecycleScope.launch {
                    try {
                        val authResponse = vm.ganti_password(this@ganti_password,binding.etPassword.text.toString())
                        if (authResponse.body()!!.success == 1) {
                            dialog.dismiss()
                            finish()
                        } else {
                            dialog.dismiss()
                            Toast.makeText(
                                this@ganti_password,
                                authResponse.body()!!.pesan,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (throwable: Throwable) {
                        dialog.dismiss()
                        Log.e("ERROR",throwable.toString())
                    }
                }
            }else{
                binding.etPassword.error = "Tidak Sama"
                binding.etPasswordUlang.error = "Tidak Sama"

            }
        }
    }
}