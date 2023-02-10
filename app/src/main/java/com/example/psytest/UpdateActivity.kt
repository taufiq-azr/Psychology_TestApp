package com.example.psytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UpdateActivity : AppCompatActivity() {

    private lateinit var edNama: EditText
    private lateinit var edUsername: EditText
    private lateinit var edPassword: EditText
    private lateinit var btnUpdate: Button

    private lateinit var sqLiteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        supportActionBar?.hide()

        initView()
        sqLiteHelper = SQLiteHelper(this)

        btnUpdate.setOnClickListener { updateUser() }

        val btn_kembali = findViewById<Button>(R.id.btn_kembali)
        btn_kembali.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun updateUser() {
        val nama = edNama.text.toString()
        val username = edUsername.text.toString()
        val password = edPassword.text.toString()
        val sessionId = sqLiteHelper.getSessionByUsername(username)

        if (nama.isEmpty() || username.isEmpty() || password.isEmpty() || sessionId.isEmpty()) {
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        } else {
            val std = UserModel(nama = nama, username = username, password = password)
            val status = sqLiteHelper.updateUser(std, sessionId)

            if (status > -1) {
                Toast.makeText(this, "User Berhasil Diperbarui...", Toast.LENGTH_SHORT).show()
                clearEdit()
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "User Gagal Diperbarui...", Toast.LENGTH_SHORT).show()
            }
        }
    }







    private fun clearEdit() {
        edNama.setText("")
        edUsername.setText("")
        edPassword.setText("")
    }

    private fun initView() {
        edNama = findViewById(R.id.txt_cl_lengkap)
        edUsername = findViewById(R.id.txt_cl_pengguna)
        edPassword = findViewById(R.id.txt_cl_password)
        btnUpdate = findViewById(R.id.btn_simpan)
    }


}
