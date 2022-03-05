package com.izzancode.kompoengdomba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izzancode.kompoengdomba.databinding.CostumTimelineKandangkuBinding
import com.izzancode.kompoengdomba.model.data.perkembangan

class detail_perkembangan_kandangkuAdapteer(private val context: Context, results: ArrayList<perkembangan>) : RecyclerView.Adapter<detail_perkembangan_kandangkuAdapteer.MyViewHolder>() {

    private var Items = ArrayList<perkembangan>()
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
            binding.tvTanggal.text = result.fd_devdate
            if(result.fd_devdate != ""){
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