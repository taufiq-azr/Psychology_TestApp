package com.example.psytest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ProfileActivity : AppCompatActivity() {
    private lateinit var mDb: SQLiteDatabase
    private lateinit var txtUsername: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtPassword: TextView
    private lateinit var txtNama: TextView

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        setContentView(R.layout.activity_profile)
        txtUsername = findViewById(R.id.txt_cl_pengguna);
        txtEmail = findViewById(R.id.txt_cl_email);
        txtPassword = findViewById(R.id.txt_cl_password);
        txtNama = findViewById(R.id.txt_cl_lengkap);

        val sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE)
        val sessionId = sharedPref.getString("session_id", null)
        if (sessionId != null) {
            // Buat objek dari kelas UserDbHelper
            val dbHelper = SQLiteHelper(this)
            // Dapatkan objek dari SQLiteDatabase
            mDb = dbHelper.readableDatabase

            // Query untuk mengambil data user

            // Query untuk mengambil data user
            val projection = arrayOf(
                UserContract.UserEntry._ID,
                UserContract.UserEntry.COLUMN_NAME_USERNAME,
                UserContract.UserEntry.COLUMN_NAME_EMAIL,
                UserContract.UserEntry.COLUMN_NAME_NAMA,
                UserContract.UserEntry.COLUMN_NAME_PASSWORD
            )

            val selection = "${UserContract.UserEntry.COLUMN_NAME_SESSION_ID} = ?"
            val selectionArgs = arrayOf(sessionId)

            val cursor = mDb.query(
                UserContract.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
            )

            while (cursor.moveToNext()) {
                val itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(UserContract.UserEntry._ID)
                )
                val username = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_USERNAME)
                )
                val email = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_EMAIL)
                )
                val nama = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_NAMA)
                )
                val password = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_PASSWORD)
                )
                // tampilkan data user di halaman yang sesuai
                txtUsername.text = username
                txtEmail.text = email
                txtPassword.text = password
                txtNama.text = nama
            }
            cursor.close()
            mDb.close()
        }
        val btn_kembali = findViewById<Button>(R.id.btn_kembali)
        val btn_ubah = findViewById<Button>(R.id.btn_ubah)
        btn_kembali.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        btn_ubah.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

        class UserContract {
            class UserEntry : BaseColumns {
                companion object {

                    val TABLE_NAME = "tbl_user"
                    val _ID = "id"
                    val COLUMN_NAME_USERNAME = "username"
                    val COLUMN_NAME_EMAIL = "email"
                    val COLUMN_NAME_NAMA = "nama"
                    val COLUMN_NAME_PASSWORD = "password"
                    val COLUMN_NAME_SESSION_ID = "session_id"
                }
            }
        }
    }
