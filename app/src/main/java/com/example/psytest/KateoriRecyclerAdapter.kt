package com.example.psytest

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.psytest.databinding.LayoutListKategoriBinding

class KateoriRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<ListObjKategori> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LayoutListKategoriBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return DosenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DosenViewHolder -> {
                holder.bind(items.get(position))
                holder.klik.setOnClickListener {
                    holder.kalau_diklik(items.get(position))
                }
            }
        }
    }
    fun submitList(listDosen: List<ListObjKategori>) {
        items = listDosen
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class DosenViewHolder constructor(val binding: LayoutListKategoriBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val foto_kategori: ImageView = binding.gambarDosen
        val nama_kategori: TextView = binding.namaKategori
        val jumlah_kategori: TextView = binding.jumlahKategori
        val klik: RelativeLayout = binding.rlKlik


        fun bind(listObjKateogri: ListObjKategori) {
            nama_kategori.setText(listObjKateogri.nama)
            jumlah_kategori.setText(listObjKateogri.jumlah_kategori)



            val requestOp = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOp)
                .load(listObjKateogri.gambar)
                .into(foto_kategori)
        }
        fun kalau_diklik(get: ListObjKategori) {
            Toast.makeText(itemView.context, "Kamu memilih: ${get.nama}", Toast.LENGTH_SHORT)
                .show()
            val intent = Intent(itemView.context, DetilKategori::class.java)
            intent.putExtra("namanya", get.nama)
            intent.putExtra("kategorinya", get.jumlah_kategori)
            intent.putExtra("fotonya", get.gambar)

            itemView.context.startActivity(intent)
        }
    }

}