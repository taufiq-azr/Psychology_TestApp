package com.example.psytest

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import android.os.Handler
import org.w3c.dom.Text


class QuizActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private lateinit var progressBar: ProgressBar
    private lateinit var tv_progressBar: TextView
    private lateinit var tv_question: TextView
    private lateinit var tv_image: ImageView
    private lateinit var tv_option_one: TextView
    private lateinit var tv_option_two: TextView
    private lateinit var tv_option_three: TextView
    private lateinit var tv_option_four: TextView
    private lateinit var btn_submit: Button
    private var score = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        supportActionBar?.hide()

        score = 0

        progressBar = findViewById(R.id.progressBar)
        tv_progressBar = findViewById(R.id.tv_progress)
        tv_question = findViewById(R.id.tv_question)

        tv_option_one = findViewById(R.id.tv_option_one)
        tv_option_two = findViewById(R.id.tv_option_two)
        tv_option_three = findViewById(R.id.tv_option_three)
        tv_option_four = findViewById(R.id.tv_option_four)
        btn_submit = findViewById(R.id.btn_submit)
        mQuestionList = Constants.getQuestion()

        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }


    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        mSelectedOptionPosition = 0
        val question = mQuestionList!![mCurrentPosition - 1]
        defaultOptionView()

        if (mCurrentPosition == mQuestionList!!.size) {
            btn_submit.text = "FINISH"
        } else {
            btn_submit.text = "SUBMIT"
        }
        println("SepectedOptionPosisition$mSelectedOptionPosition")
        progressBar.progress = mCurrentPosition
        tv_progressBar.text = "$mCurrentPosition" + "/" + progressBar.max
        tv_question.text = question.question

        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
    }


    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(0, tv_option_two)
        options.add(0, tv_option_three)
        options.add(0, tv_option_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7a8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(tv_option_one, 1)
                mSelectedOptionPosition = 1
            }
            R.id.tv_option_two -> {
                selectedOptionView(tv_option_two, 2)
                mSelectedOptionPosition = 2
            }
            R.id.tv_option_three -> {
                selectedOptionView(tv_option_three, 3)
                mSelectedOptionPosition = 3
            }
            R.id.tv_option_four -> {
                selectedOptionView(tv_option_four, 4)
                mSelectedOptionPosition = 4
            }
            R.id.btn_submit -> {
                val question = mQuestionList?.get(mCurrentPosition - 1)
                if (question!!.correctAnswer == mSelectedOptionPosition) {
                    score++
                    when (mSelectedOptionPosition) {
                        1 -> tv_option_one.background =
                            ContextCompat.getDrawable(this, R.drawable.correct_option_bg)
                        2 -> tv_option_two.background =
                            ContextCompat.getDrawable(this, R.drawable.correct_option_bg)
                        3 -> tv_option_three.background =
                            ContextCompat.getDrawable(this, R.drawable.correct_option_bg)
                        4 -> tv_option_four.background =
                            ContextCompat.getDrawable(this, R.drawable.correct_option_bg)
                    }
                } else {
                    when (mSelectedOptionPosition) {
                        1 -> tv_option_one.background =
                            ContextCompat.getDrawable(this, R.drawable.wrong_option_bg)
                        2 -> tv_option_two.background =
                            ContextCompat.getDrawable(this, R.drawable.wrong_option_bg)
                        3 -> tv_option_three.background =
                            ContextCompat.getDrawable(this, R.drawable.wrong_option_bg)
                        4 -> tv_option_four.background =
                            ContextCompat.getDrawable(this, R.drawable.wrong_option_bg)
                    }
                }
                btn_submit.text = "GO TO NEXT QUESTION"
                if (mCurrentPosition == mQuestionList!!.size) {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                    intent.putExtra(Constants.CORRECT_ANSWERS, score)
                    startActivity(intent)
                } else {
                    mCurrentPosition++
                    setQuestion()
                }
            }
        }
    }



    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }



    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
        println("SepectedOptionPosisition$mSelectedOptionPosition")
        val question = mQuestionList?.get(mCurrentPosition - 1)
        println("question "+question!!.correctAnswer)

        if (question!!.correctAnswer == mSelectedOptionPosition) {
            println("true")
        }else {
            println("false")
        }
    }

}