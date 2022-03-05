package com.izzancode.kompoengdomba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.izzancode.kompoengdomba.viewmodel.customerViewModel

class MainActivity : AppCompatActivity() {
    lateinit var vm: customerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this).get(customerViewModel::class.java)
        vm.getLogin(this,this)
    }
}