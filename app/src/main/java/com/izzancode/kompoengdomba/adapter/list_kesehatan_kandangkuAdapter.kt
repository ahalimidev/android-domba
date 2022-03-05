package com.izzancode.kompoengdomba.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.izzancode.kompoengdomba.databinding.CostumListKesehatanBinding
import com.izzancode.kompoengdomba.model.data.kesehatan
import com.izzancode.kompoengdomba.utils.foto_kambing
import com.izzancode.kompoengdomba.view.detail_kesehetan_kandangku

class list_kesehatan_kandangkuAdapter(private val context: Context, results: ArrayList<kesehatan>) : RecyclerView.Adapter<list_kesehatan_kandangkuAdapter.MyViewHolder>() {

    private var Items = ArrayList<kesehatan>()

    init {
        this.Items = results

    }

    inner class MyViewHolder(val binding: CostumListKesehatanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(
            CostumListKesehatanBinding.inflate(
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
            binding.tvTglPerawatan.text = result.fd_healthdate
            binding.tvKeluhan.text = result.fv_disease
            binding.tvPenanganan.text = result.fv_diseasetreatment

            holder.itemView.setOnClickListener {
                context.startActivity(Intent(context, detail_kesehetan_kandangku::class.java).putExtra("id",result.fn_sheepid))

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