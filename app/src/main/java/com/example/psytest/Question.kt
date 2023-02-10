package com.example.psytest

data class Question (
        val id : Int,
        val question : String,
        val image : String,
        val optionOne :  String,
        val optionTwo :  String,
        val optionThree :  String,
        val optionFour : String,
        val correctAnswer : Int,
        )

