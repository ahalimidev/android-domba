package com.izzancode.kompoengdomba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izzancode.kompoengdomba.databinding.CostumTimelineKandangkuBinding
import com.izzancode.kompoengdomba.model.data.kesehatan

class detail_kesehatan_titip_angonAdapter(private val context: Context, results: ArrayList<kesehatan>) : RecyclerView.Adapter<detail_kesehatan_titip_angonAdapter.MyViewHolder>() {

    private var Items = ArrayList<kesehatan>()
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
            if(result.fd_healthdate != ""){
                binding.tvNomor.text = "${nomor++}"
            }
            binding.tvKeteraangan.text = result.fv_diseasetreatment
            binding.tvTanggal.text = result.fd_healthdate
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