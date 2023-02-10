package com.example.psytest

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.psytest.ProfileActivity.UserContract.UserEntry.Companion.COLUMN_NAME_NAMA
import com.example.psytest.ProfileActivity.UserContract.UserEntry.Companion.COLUMN_NAME_PASSWORD
import com.example.psytest.ProfileActivity.UserContract.UserEntry.Companion.COLUMN_NAME_SESSION_ID
import com.example.psytest.ProfileActivity.UserContract.UserEntry.Companion.COLUMN_NAME_USERNAME
import com.example.psytest.ProfileActivity.UserContract.UserEntry.Companion.TABLE_NAME

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        companion object {
            private const val DATABASE_VERSION = 7
            private const val DATABASE_NAME =   "psytest.db"
            private const val TBL_USER = "tbl_user"
            private const val ID = "id"
            private const val EMAIL = "email"
            private const val NAMA = "nama"
            private const val USERNAME = "username"
            private const val SESSION = "session_id"
            private const val PASSWORD = "password"
            private const val TBL_SCORE = "tbl_score"
            private const val NAMA_PEMAIN = "nama_pemain"
            private const val SKOR = "skor"


        }


    override fun onCreate(db: SQLiteDatabase?) {
     val createTblUser = ("CREATE TABLE $TBL_USER($ID INTEGER PRIMARY KEY,$EMAIL TEXT,$NAMA TEXT,$USERNAME TEXT,$SESSION TEXT,$PASSWORD TEXT)")
        db?.execSQL(createTblUser)
        val createTblScore = ("CREATE TABLE $TBL_SCORE($ID INTEGER PRIMARY KEY,$NAMA_PEMAIN TEXT,$SKOR INTEGER)")
        db?.execSQL(createTblScore)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        when (oldVersion) {
            7 -> {
                // Proses upgrade dari versi 1 ke versi 2
                // Misalnya, menambah kolom baru pada tabel
                db?.execSQL("ALTER TABLE $TBL_USER ADD COLUMN $SESSION TEXT")
            }
            8 -> {
                // Proses upgrade dari versi 2 ke versi 3
                // Misalnya, menghapus tabel yang tidak digunakan lagi
                db?.execSQL("DROP TABLE IF EXISTS $TBL_USER")
            }
        }
    }
    fun insertScore(playerName: String, score: Int) : Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAMA_PEMAIN, playerName)
        contentValues.put(SKOR, score)
        val success = db.insert(TBL_SCORE, null, contentValues)
        db.close()
        return success
    }

    fun insertUser(std: UserModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(EMAIL, std.email)
        contentValues.put(NAMA, std.nama)
        contentValues.put(USERNAME, std.username)
        contentValues.put(PASSWORD, std.password)

        val success = db.insert(TBL_USER, null, contentValues)
        db.close()
        return success
    }

    fun allData(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TBL_USER", null)
    }

    fun updateUser(user: UserModel, sessionId: String): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME_NAMA, user.nama)
        contentValues.put(COLUMN_NAME_USERNAME, user.username)
        contentValues.put(COLUMN_NAME_PASSWORD, user.password)
        contentValues.put(COLUMN_NAME_SESSION_ID, sessionId)

        val status = db.update(TABLE_NAME, contentValues, "$COLUMN_NAME_SESSION_ID = ?", arrayOf(sessionId))
        db.close()
        return status
    }


    @SuppressLint("Range")
    fun getSessionByUsername(username: String): String {
        val db = this.readableDatabase
        val query = "SELECT session_id FROM tbl_user WHERE username = ?"
        val cursor = db.rawQuery(query, arrayOf(username))

        var session = ""
        if (cursor.moveToFirst()) {
            session = cursor.getString(cursor.getColumnIndex("session_id"))
        }
        cursor.close()
        return session
    }







}