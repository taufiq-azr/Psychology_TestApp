package com.example.psytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class DaftarActivity : AppCompatActivity() {

    private lateinit var edEmail : EditText
    private lateinit var edNama : EditText
    private lateinit var edUsername : EditText
    private lateinit var edPassword : EditText
    private lateinit var edBtnDaftar : Button
    private lateinit var sqLiteHelper: SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)
        supportActionBar?.hide()

        initView()
        sqLiteHelper = SQLiteHelper(this)
        edBtnDaftar.setOnClickListener{addUser()}
    }
    private fun addUser(){
        val email = edEmail.text.toString()
        val nama = edNama.text.toString()
        val username = edUsername.text.toString()
        val password = edPassword.text.toString()
        if (email.isEmpty() || nama.isEmpty() || username.isEmpty()||password.isEmpty()) {
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        } else {
            val std = UserModel(email = email, nama = nama, username = username, password = password)
            val status =sqLiteHelper.insertUser(std)
            if (status > -1) {
                Toast.makeText( this, "User Berhasil Ditambahkan...", Toast.LENGTH_SHORT).show()
                clearedit()
                val intent = Intent( this, LoginActivity::class.java)
                startActivity(intent)
            }else {
                Toast.makeText( this, "User Gagal Ditambahkan...", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun clearedit(){
        edEmail.setText("")
        edNama.setText("")
        edUsername.setText("")
        edPassword.setText("")
    }

    private fun initView() {
        edEmail = findViewById(R.id.txt_email)
        edNama = findViewById(R.id.txt_nama)
        edUsername = findViewById(R.id.txt_nama_pengguna)
        edPassword = findViewById(R.id.txt_password)
        edBtnDaftar = findViewById(R.id.btnDaftar)
    }
}