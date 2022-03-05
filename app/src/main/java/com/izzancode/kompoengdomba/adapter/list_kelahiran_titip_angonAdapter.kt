package com.izzancode.kompoengdomba.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.izzancode.kompoengdomba.databinding.CostumListTernakBinding
import com.izzancode.kompoengdomba.model.data.kelahiran
import com.izzancode.kompoengdomba.utils.foto_kambing
import com.izzancode.kompoengdomba.view.detail_kelahiran_kandangku

class list_kelahiran_titip_angonAdapter(private val context: Context, results: ArrayList<kelahiran>) : RecyclerView.Adapter<list_kelahiran_titip_angonAdapter.MyViewHolder>() {

    private var Items = ArrayList<kelahiran>()

    init {
        this.Items = results

    }

    inner class MyViewHolder(val binding: CostumListTernakBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(
            CostumListTernakBinding.inflate(
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
            binding.tvBobot.text = result.fn_weight
            binding.tvJenisKelamin.text = result.fn_gender
            binding.tvKodeKambing.text = result.fv_codesheep
            binding.tvTglLahir.text =  result.fd_birthdate
            binding.tvTypeKambing.text = result.fn_sheeptype
            binding.tvUmur.text = result.fn_age

            holder.itemView.setOnClickListener {
                context.startActivity(Intent(context, detail_kelahiran_kandangku::class.java).putExtra("id",result.fc_nodoc))

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