package com.izzancode.kompoengdomba.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.izzancode.kompoengdomba.R
import com.izzancode.kompoengdomba.databinding.ActivityLupaPasswordBinding
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.customerViewModel
import kotlinx.coroutines.launch

class lupa_password : AppCompatActivity() {
    private val binding by lazy { ActivityLupaPasswordBinding.inflate(layoutInflater) }
    lateinit var dialog : Dialog
    lateinit var vm: customerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(customerViewModel::class.java)
        dialog = progressDialog(this@lupa_password)
        binding.btLogin.setOnClickListener {
            if(binding.etKode.text.toString().isEmpty()) {
                binding.etKode.requestFocus()
                binding.etKode.error = "Kosong"
            }else if(binding.etPassword.text.toString().isEmpty()) {
                binding.etPassword.requestFocus()
                binding.etPassword.error = "Kosong"

            }else if(binding.etPasswordUlang.text.toString().isEmpty()) {
                binding.etPasswordUlang.requestFocus()
                binding.etPasswordUlang.error = "Kosong"

            }else if(binding.etPassword.text.toString().equals(binding.etPasswordUlang.text.toString())){
                dialog.show()
                lifecycleScope.launch {
                try {
                    val authResponse = vm.lupa_password(
                        binding.etKode.text.toString(),
                        binding.etPassword.text.toString(),
                        "---------"
                    )
                    if (authResponse.body()!!.success == 1) {
                        dialog.dismiss()
                        vm.setLogin(this@lupa_password, this@lupa_password, authResponse.body()!!.data)
                    } else {
                        dialog.dismiss()
                        Toast.makeText(
                            this@lupa_password,
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