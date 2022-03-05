package com.izzancode.kompoengdomba.view

import android.app.Dialog
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.izzancode.kompoengdomba.adapter.list_display_titip_angonAdapter
import com.izzancode.kompoengdomba.databinding.ActivityListDisplayKandangkuBinding
import com.izzancode.kompoengdomba.utils.progressDialog
import com.izzancode.kompoengdomba.viewmodel.kandangkuViewModel
import com.izzancode.kompoengdomba.viewmodel.titipangonViewModel
import kotlinx.coroutines.launch

class list_display_titip_angon : AppCompatActivity() {
    private val binding by lazy { ActivityListDisplayKandangkuBinding.inflate(layoutInflater) }
    lateinit var  vm : titipangonViewModel
    lateinit var dialog : Dialog
    private var rKandangkuAdapter : list_display_titip_angonAdapter? = null
    var status : String = "D"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(titipangonViewModel::class.java)
        dialog = progressDialog(this@list_display_titip_angon)
        binding.ivBack.setOnClickListener { finish() }
        binding.tvDijual.setOnClickListener {
            status = "D"
            tampil(status)

            binding.tvDijual.setTextColor(Color.parseColor("#ffffff"))
            binding.tvDijual.setBackgroundColor(Color.parseColor("#6FD94E"))
            binding.tvTerjual.setTextColor(Color.parseColor("#BEBEBE"))
            binding.tvTerjual.setBackgroundColor(Color.parseColor("#DEF2D7"))
        }
        binding.tvTerjual.setOnClickListener {

            status = "S"
            tampil(status)

            binding.tvTerjual.setTextColor(Color.parseColor("#ffffff"))
            binding.tvTerjual.setBackgroundColor(Color.parseColor("#6FD94E"))
            binding.tvDijual.setTextColor(Color.parseColor("#BEBEBE"))
            binding.tvDijual.setBackgroundColor(Color.parseColor("#DEF2D7"))
        }
        binding.ivFilter.setOnClickListener {
        }
        binding.ivPencarian.setOnClickListener {
            if(!binding.etName.text.toString().isEmpty()){
            }
        }
        binding.etName.setOnKeyListener { view, i, keyEvent ->
            if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                if(!binding.etName.text.toString().isEmpty()){
                }
            }
            false
        }
        tampil(status)
    }
    private fun tampil (status : String){
        dialog.show()
        lifecycleScope.launch {
            try {
                val authResponse = vm.list_display(this@list_display_titip_angon,status)
                if (authResponse.body()!!.success == 1) {
                    dialog.dismiss()
                    val result = authResponse.body()!!.data
                    rKandangkuAdapter = list_display_titip_angonAdapter(this@list_display_titip_angon,result)
                    if (this@list_display_titip_angon.resources.getConfiguration().orientation === Configuration.ORIENTATION_PORTRAIT) {
                        binding.rvTampil.setLayoutManager(GridLayoutManager(this@list_display_titip_angon, 2))
                    } else {
                        binding.rvTampil.setLayoutManager(GridLayoutManager(this@list_display_titip_angon, 4))
                    }
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
