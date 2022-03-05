package com.izzancode.kompoengdomba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izzancode.kompoengdomba.databinding.CostumTimelineKandangkuBinding
import com.izzancode.kompoengdomba.model.data.perawatan

class detail_perawatan_kandangkuAdapter(private val context: Context, results: ArrayList<perawatan>) : RecyclerView.Adapter<detail_perawatan_kandangkuAdapter.MyViewHolder>() {

    private var Items = ArrayList<perawatan>()
    var nomor = 1
    init {
        this.Items = results

    }

    inner class MyViewHolder(val binding: CostumTimelineKandangkuBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(
            CostumTimelineKandangkuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result = Items[position]
        with(holder) {

            binding.tvKeteraangan.text = result.fv_desc
            binding.tvTanggal.text = result.fd_treatdate
            if(result.fd_treatdate != ""){
                binding.tvNomor.text = "${nomor++}"
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