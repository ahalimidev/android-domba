package com.izzancode.kompoengdomba.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.izzancode.kompoengdomba.databinding.CostumListPerkembanganBinding
import com.izzancode.kompoengdomba.model.data.perkembangan
import com.izzancode.kompoengdomba.utils.foto_kambing
import com.izzancode.kompoengdomba.view.detail_perkembangan_kandangku

class list_perkembangan_titip_angonAdapter(private val context: Context, results: ArrayList<perkembangan>) : RecyclerView.Adapter<list_perkembangan_titip_angonAdapter.MyViewHolder>() {

    private var Items = ArrayList<perkembangan>()

    init {
        this.Items = results

    }

    inner class MyViewHolder(val binding: CostumListPerkembanganBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(
            CostumListPerkembanganBinding.inflate(
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
            binding.tvPerkembangan.text = result.fv_desc
            binding.tvTglPerkembangan.text = result.fd_devdate!!

            holder.itemView.setOnClickListener {
                context.startActivity(Intent(context, detail_perkembangan_kandangku::class.java).putExtra("id",result.fn_sheepid))

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