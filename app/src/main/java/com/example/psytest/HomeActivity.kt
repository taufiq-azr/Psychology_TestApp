package com.example.psytest


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.psytest.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var dosenAdapter: KateoriRecyclerAdapter
    private lateinit var navView: NavigationView
    private lateinit var mDb: SQLiteDatabase
    private var sessionId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        tambahDataSet()
        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@HomeActivity,
                drawerLayout,
                R.string.open,
                R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            var navView = findViewById<NavigationView>(R.id.navView)

            // Get session ID
            val sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE)
            sessionId = sharedPref.getString("session_id", null)

            val headerView = navView.getHeaderView(0)
            val txtUsername = headerView.findViewById<TextView>(R.id.nav_username)
            if (sessionId != null) {
                // Buat objek dari kelas UserDbHelper
                val dbHelper = SQLiteHelper(this@HomeActivity)
                // Dapatkan objek dari SQLiteDatabase
                mDb = dbHelper.readableDatabase

                // Query untuk mengambil data user
                val projection = arrayOf(
                    ProfileActivity.UserContract.UserEntry.COLUMN_NAME_USERNAME
                )

                val selection = "${ProfileActivity.UserContract.UserEntry.COLUMN_NAME_SESSION_ID} = ?"
                val selectionArgs = arrayOf(sessionId)

                val cursor = mDb.query(
                    ProfileActivity.UserContract.UserEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
                )

                while (cursor.moveToNext()) {
                    val username = cursor.getString(
                        cursor.getColumnIndexOrThrow(ProfileActivity.UserContract.UserEntry.COLUMN_NAME_USERNAME)
                    )
                    // tampilkan username di navigation drawer
                    txtUsername.text = username
                }
                cursor.close()
                mDb.close()
            }

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.firstItem -> {
                        val intent = Intent(this@HomeActivity, HomeActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.secondtItem -> {
                        val intent = Intent(this@HomeActivity, ProfileActivity::class.java)
                        startActivity(intent)
                    }
                }
                true
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun tambahDataSet() {
        val data = SumberData.buatSetData()
        dosenAdapter.submitList(data)
    }
    private fun initRecyclerView(){
        binding.recylerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            val spacingAtas = DeklarasiSpasiGambar(20)
            addItemDecoration(spacingAtas)
            dosenAdapter =  KateoriRecyclerAdapter()
            adapter = dosenAdapter
        }
    }
}


