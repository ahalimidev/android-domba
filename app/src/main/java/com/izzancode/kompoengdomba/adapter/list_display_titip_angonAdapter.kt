package com.izzancode.kompoengdomba.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.izzancode.kompoengdomba.databinding.CostumListDisplayBinding
import com.izzancode.kompoengdomba.model.data.display
import com.izzancode.kompoengdomba.utils.foto_kandang
import com.izzancode.kompoengdomba.view.detail_display_kandangku

class list_display_titip_angonAdapter(private val context: Context, results: ArrayList<display>) : RecyclerView.Adapter<list_display_titip_angonAdapter.MyViewHolder>() {

    private var Items = ArrayList<display>()

    init {
        this.Items = results

    }

    inner class MyViewHolder(val binding: CostumListDisplayBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(
            CostumListDisplayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result = Items[position]
        with(holder) {
            val foto = foto_kandang +result.fv_image
            if(result.fc_status == "D"){
                binding.ivGambarX.visibility = View.GONE
            }else{
                binding.ivGambarX.visibility = View.VISIBLE

            }
            Glide.with(context)
                .load(foto)
                .into(binding.ivGambar)
            binding.tvBobot.text = result.fn_weight
            binding.tvJenisKelamin.text = result.fn_gender
            binding.tvKodeKambing.text = result.fm_price
            binding.tvTglLahir.text = result.fd_dateofbirth
            binding.tvTypeKambing.text = result.fv_namesheep
            binding.tvUmur.text = result.fn_age
            holder.itemView.setOnClickListener {
                context.startActivity(Intent(context, detail_display_kandangku::class.java).putExtra("id",result.fn_sheepid).putExtra("status",result.fc_status))

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