package com.izzancode.kompoengdomba.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.izzancode.kompoengdomba.databinding.CostumListPerawatanBinding
import com.izzancode.kompoengdomba.model.data.perawatan
import com.izzancode.kompoengdomba.utils.foto_kambing
import com.izzancode.kompoengdomba.view.detail_perawatan_kandangku

class list_perawatan_titip_angonAdapter(private val context: Context, results: ArrayList<perawatan>) : RecyclerView.Adapter<list_perawatan_titip_angonAdapter.MyViewHolder>() {

    private var Items = ArrayList<perawatan>()

    init {
        this.Items = results

    }

    inner class MyViewHolder(val binding: CostumListPerawatanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(
            CostumListPerawatanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result = Items[position]
        with(holder) {
            val foto = foto_kambing+result.fv_image

            Glide.with(context)
                .load(foto)
                .into(binding.ivGambar)
            binding.tvKodeKambing.text = result.fv_codesheep
            binding.tvJenisPerawatan.text = result.fv_desc
            binding.tvTglPerawatan.text = result.fd_treatdate

            holder.itemView.setOnClickListener {
                context.startActivity(Intent(context, detail_perawatan_kandangku::class.java).putExtra("id",result.fn_sheepid))

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