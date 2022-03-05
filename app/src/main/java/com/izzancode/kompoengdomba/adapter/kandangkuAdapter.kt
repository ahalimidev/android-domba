package com.izzancode.kompoengdomba.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.izzancode.kompoengdomba.databinding.CostumKandangkuBinding
import com.izzancode.kompoengdomba.utils.foto_kandang
import com.izzancode.kompoengdomba.view.kandang_detail
import com.izzancode.kompoengdomba.view.menu_kandangku
import com.izzancode.kompoengdomba.model.data.kandangku as data_kandang

class kandangkuAdapter(private val context: Context, results: ArrayList<data_kandang>) : RecyclerView.Adapter<kandangkuAdapter.MyViewHolder>() {

    private var Items = ArrayList<data_kandang>()

    init {
        this.Items = results

    }

    inner class MyViewHolder(val binding: CostumKandangkuBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(
            CostumKandangkuBinding.inflate(
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

            Glide.with(context)
                .load(foto)
                .into(binding.ivGambar)

            binding.tvKelahiranKambing.text = result.kelahiran_kambing
            binding.tvKematianKambing.text = result.kematian_kambing
            binding.tvTotalKambing.text = result.total_kambing
            binding.tvKodeKandang.text = result.fv_codekav
            holder.itemView.setOnClickListener {
                context.startActivity(Intent(context,menu_kandangku::class.java).putExtra("id",result.fn_kavid))
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