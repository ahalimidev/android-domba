package com.izzancode.kompoengdomba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izzancode.kompoengdomba.databinding.CostumDetailSaldoBinding
import com.izzancode.kompoengdomba.model.data.saldo as data_saldo;
class saldoAdapter(private val context: Context, results: ArrayList<data_saldo>) : RecyclerView.Adapter<saldoAdapter.MyViewHolder>() {

    private var Items = ArrayList<data_saldo>()

    init {
        this.Items = results

    }

    inner class MyViewHolder(val binding: CostumDetailSaldoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(
            CostumDetailSaldoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result = Items[position]
        with(holder){
                binding.tvTangalSaldo.text = result.fd_withdrawaldate
                binding.tvKeterangan.text = result.fv_desc
                binding.tvNominal.text = result.fm_amount
            }
        }

    override fun getItemCount(): Int {
        return Items.size
    }
}