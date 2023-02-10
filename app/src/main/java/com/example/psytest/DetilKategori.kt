package com.example.psytest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.psytest.databinding.DetilKategoriBinding

class DetilKategori : AppCompatActivity() {
    private lateinit var binding: DetilKategoriBinding
    private lateinit var txtnama: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = DetilKategoriBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        txtnama = findViewById(R.id.et_name);

        if (intent.hasExtra("namanya")) {

            val nama: String = this.intent.getStringExtra("namanya").toString()
            val foto: String = this.intent.getStringExtra("fotonya").toString()
            val kategori: String = this.intent.getStringExtra("kategorinya").toString()

            setDetil(foto, nama, kategori)
            binding.btnMulaiQuiz.setOnClickListener {
                val playerName = txtnama.text.toString()
                if (txtnama.text.toString().isEmpty()) {
                    Toast.makeText(this, "Please Enter your name !", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this, QuizActivity::class.java)
                    intent.putExtra("playerName", playerName)
                    intent.putExtra("kategori", kategori)
                    startActivity(intent)
                    finish()
                }
            }
            val btn_kembali = findViewById<Button>(R.id.btn_Kembali_home)
            btn_kembali.setOnClickListener {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


    fun setDetil(foto: String, nama: String, kategori: String) {
        val requestOp = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        binding.namaDetilKategori.text = nama


        Glide.with(this)
            .load(foto)
            .apply(requestOp)
            .centerCrop()
            .into(binding.fotoDetil)
    }
}