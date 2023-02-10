package com.example.psytest

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast


class ResultActivity : AppCompatActivity() {

    private lateinit var tv_score: TextView
    private lateinit var tv_correct: TextView
    private lateinit var tv_incorrect: TextView
    private lateinit var tv_percentage: TextView
    private lateinit var btn_finish: Button
    private lateinit var mDb: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_result)
        val score = intent.getIntExtra("score", 0)
        val playerName = intent.getStringExtra("playerName")
        tv_score = findViewById(R.id.tv_score_value)
        tv_correct = findViewById(R.id.tv_correct_value)
        tv_incorrect = findViewById(R.id.tv_incorrect_value)
        tv_percentage = findViewById(R.id.tv_percentage_value)
        btn_finish = findViewById(R.id.btn_finish)

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        val incorrectAnswers = totalQuestions - correctAnswers
        val percentage = (correctAnswers.toFloat() / totalQuestions.toFloat()) * 100

        tv_score.text = "$correctAnswers"
        tv_correct.text = "$correctAnswers"
        tv_incorrect.text = "$incorrectAnswers"
        tv_percentage.text = "${percentage}%"

        btn_finish.setOnClickListener {
            if (playerName != null) {
                val db = SQLiteHelper(this)
                val success = db.insertScore(playerName, correctAnswers)
                finish()
            } else {
                Toast.makeText(this, "Player name is null, cannot insert score", Toast.LENGTH_SHORT)
                    .show()
            }
            val intent = Intent( this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}