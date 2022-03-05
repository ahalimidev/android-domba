package com.izzancode.kompoengdomba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izzancode.kompoengdomba.databinding.CostumNotifkasiBinding
import com.izzancode.kompoengdomba.model.data.notif as data_notif;
class notifAdapter(private val context: Context, results: ArrayList<data_notif>) : RecyclerView.Adapter<notifAdapter.MyViewHolder>() {

    private var Items = ArrayList<data_notif>()

    init {
        this.Items = results

    }

    inner class MyViewHolder(val binding: CostumNotifkasiBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(
            CostumNotifkasiBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result = Items[position]
        with(holder){
                binding.tvTanggalNotif.text = result.fd_date
                binding.tvKeterangan.text = result.fv_desc
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