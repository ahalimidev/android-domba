package com.izzancode.kompoengdomba.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.izzancode.kompoengdomba.databinding.CostumListKandangBinding
import com.izzancode.kompoengdomba.utils.foto_kandang
import com.izzancode.kompoengdomba.view.kandang_detail
import com.izzancode.kompoengdomba.model.data.kandang as data_kandang

class kandangAdapter(private val context: Context, results: ArrayList<data_kandang>) : RecyclerView.Adapter<kandangAdapter.MyViewHolder>() {

    private var Items = ArrayList<data_kandang>()

    init {
        this.Items = results

    }

    inner class MyViewHolder(val binding: CostumListKandangBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(
            CostumListKandangBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result = Items[position]
        with(holder) {
            val foto = foto_kandang +result.fv_picture
            if(result.fc_status == "D"){
                binding.ivGambarX.visibility = View.GONE
            }else{
                binding.ivGambarX.visibility = View.VISIBLE

            }
            Glide.with(context)
                .load(foto)
                .into(binding.ivGambar)
            binding.tvHarga.text = result.fm_price
            binding.tvKodeKandang.text = result.fv_codekav
            binding.tvKambing.text = result.fn_totalsheep
            binding.tvUkur.text = result.fn_size
            binding.tvTypeKandang.text = result.fv_configname
            binding.tvNamaKavaling.text = result.fv_namekav
            holder.itemView.setOnClickListener {
                context.startActivity(Intent(context,kandang_detail::class.java).putExtra("id",result.fn_kavid).putExtra("status",result.fc_status))
            }
        }
    }

    override fun getItemCount(): Int {
        return Items.size
    }
    fun clear() {
        val size: Int = Items.size
        Items.clear()
        notifyItemRangeRemoved(0, size)
    }
}