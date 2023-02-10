package com.example.psytest

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btn_daftar : Button
    lateinit var btnLogin : Button
    lateinit var txtUsername : EditText
    lateinit var txtPassword : EditText
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        btn_daftar = findViewById(R.id.btn_daftar)
        btn_daftar.setOnClickListener(this)

        init()

        btnLogin.setOnClickListener{
            val dbHelper = SQLiteHelper(this)
            val cursor = dbHelper.allData()

            var flag = 0
            while (cursor.moveToNext()){
                if(txtUsername.text.toString().equals(cursor.getString(3)) && txtPassword.text.toString().equals(cursor.getString(5))){
                    val dbHelper = SQLiteHelper(this)
                    val mDb = dbHelper.writableDatabase
                    val inputFields = arrayOf(txtUsername, txtPassword)
                    val inputValues = Array(inputFields.size) { "" }
                    for (i in inputFields.indices) {
                        inputValues[i] = inputFields[i].text.toString()
                    }
                    val username = inputValues[0]
                    val password = inputValues[1]

// Generate session ID
                    val sessionId = UUID.randomUUID().toString()

// Update user dengan session ID
                    val values = ContentValues().apply {
                        put(ProfileActivity.UserContract.UserEntry.COLUMN_NAME_SESSION_ID, sessionId)
                    }

                    val selection = "${ProfileActivity.UserContract.UserEntry.COLUMN_NAME_USERNAME} = ?"
                    val selectionArgs = arrayOf(username)

                    val count = mDb.update(
                        ProfileActivity.UserContract.UserEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs
                    )

                    // Save session ID
                    saveSessionId(sessionId)

                    // ...

                    flag = 1
                    val intent = Intent( this, HomeActivity::class.java)
                    startActivity(intent)
                    break
                }

            }
            if (flag == 0 ) {
                Toast.makeText(this, "User not Found!", Toast.LENGTH_SHORT).show()
            }
        }



    }
    private fun init() {
        btnLogin = findViewById(R.id.btn_masuk)
        txtUsername = findViewById(R.id.edt_username)
        txtPassword = findViewById(R.id.edt_password)
    }
    private fun saveSessionId(sessionId: String) {
        val sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("session_id", sessionId)
        editor.apply()
    }
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.btn_daftar -> run {
                    val intentBiasa = Intent(this@LoginActivity, DaftarActivity::class.java)
                    startActivity(intentBiasa)
                }
            }
        }
    }


    fun getSessionId(): String? {
        val sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE)
        return sharedPref.getString("session_id", null)
    }

}


