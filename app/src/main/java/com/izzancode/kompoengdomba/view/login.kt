package com.izzancode.kompoengdomba.view

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.izzancode.kompoengdomba.databinding.ActivityLoginBinding
import com.izzancode.kompoengdomba.databinding.CostumVerifikasiEmailBinding
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.customerViewModel
import kotlinx.coroutines.launch


class login : AppCompatActivity() {
    private  val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    lateinit var dialog : Dialog
    lateinit var vm: customerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(customerViewModel::class.java)
        dialog = progressDialog(this@login)

        binding.tvLupaPassword.setOnClickListener {
            dialog()
        }
        binding.btLogin.setOnClickListener {
            if(binding.etEmail.text.toString().isEmpty()) {
                binding.etEmail.requestFocus()
                binding.etEmail.error = "Kosong"
            }else if(binding.etPassword.text.toString().isEmpty()){
                binding.etPassword.requestFocus()
                binding.etPassword.error = "Kosong"

            }else{

                dialog.show()
                lifecycleScope.launch {
                    try {
                        val authResponse = vm.loginuser(
                            binding.etEmail.text.toString(),
                            binding.etPassword.text.toString(),
                            "---------"
                        )
                        if (authResponse.body()!!.success == 1) {
                            dialog.dismiss()
                            vm.setLogin(this@login, this@login, authResponse.body()!!.data)
                        } else {
                            dialog.dismiss()
                            Toast.makeText(
                                this@login,
                                authResponse.body()!!.pesan,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (throwable: Throwable) {
                        dialog.dismiss()
                        Log.e("ERROR",throwable.toString())
                    }
                }
            }
        }
    }

    private  fun dialog(){
        val dialog = Dialog(this@login)
        val x by lazy { CostumVerifikasiEmailBinding.inflate(layoutInflater) }
        dialog.setContentView(x.root)
        x.btKirim.setOnClickListener {
            if(x.etEmail.text.toString().isEmpty()) {
                x.etEmail.requestFocus()
                x.etEmail.error = "Kosong"

            }else{
                dialog.show()
                lifecycleScope.launch {
                    try {
                        val authResponse = vm.verfikasi_email(
                            x.etEmail.text.toString()
                        )
                        if (authResponse.body()!!.success == 1) {
                            dialog.dismiss()
                            startActivity(Intent(this@login,lupa_password::class.java))
                        } else {
                            Toast.makeText(
                                this@login,
                                authResponse.body()!!.pesan,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (throwable: Throwable) {
                        Log.e("ERROR",throwable.toString())
                    }
                }
            }
        }
        dialog.show()
    }
}