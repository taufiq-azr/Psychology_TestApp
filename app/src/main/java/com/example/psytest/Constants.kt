package com.example.psytest

object Constants {
    const val TOTAL_QUESTIONS = "total_questions"
    const val CORRECT_ANSWERS = "correct_answers"
    const val SCORE = "score"
    const val PLAYER = "playerName"
    fun getQuestion(): ArrayList<Question> {

        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,
            "Suatu seri: 100-4-90-7-80 seri selanjutnya adalah…",
        "https://cdn.britannica.com/69/5869-004-7D75CD05/Flag-Argentina.jpg",
            "8",
        "9",

        "10",
        "11",

        3
        )

        questionsList.add(que1)

        val que2 = Question(
            2,
            "Suatu seri: 50-40-31-24-18- seri selanjutnya adalah…",
            "https://cdn.britannica.com/48/1648-004-A33B72D8/Flag-Indonesia.jpg",
            "16",
            "15",
            "14",
            "16",
            4
        )
        questionsList.add(que2)
        val que3 = Question(
            3,
            "Suatu seri: 9-5-1-2-10-6-2-3-11 -7- seri selanjutnya adalah…",
            "https://p2k.unkris.ac.id/_sepakbola/_baca_image.php?td=18&kodegb=150px-Flag_of_Afghanistan.png",
            "3",
            "4",
            "12",
            "8",
            1
        )
        questionsList.add(que3)
        val que4 = Question(
            4,
            "Suatu seri: 2-1-2-1-3-3-3-4-4- seri selanjutnya adalah…",
            "https://p2k.unkris.ac.id/_sepakbola/_baca_image.php?td=18&kodegb=150px-Flag_of_Saudi_Arabia.png",
            "3,4,7",
            "4,5,7",
            "5,4,7",
            "6,5,4",
            3
        )
        questionsList.add(que4)
        val que5 = Question(
            5,
            "Suatu seri: 13-14-13-14-11-12-11-12-15-16-15-16-13 seri selanjutnya adalah…",
            "",
            "11-15-13",
            "12-16-14",
            "14-13-14",
            "14-15-13",
            3
        )
        questionsList.add(que5)
        val que6 = Question(
            6,
            "100, 95, 85, 70, 50, ….",
            "https://p2k.unkris.ac.id/_sepakbola/_baca_image.php?td=18&kodegb=150px-Flag_of_the_Netherlands.png",
            "45",
            "40",
            "35",
            "25",
            4
        )
        questionsList.add(que6)
        val que7 = Question(
            7,
            "2, 2, 4, 6, 10, 16, 26, …",
            "https://p2k.unkris.ac.id/_sepakbola/_baca_image.php?td=18&kodegb=167px-Flag_of_Bangladesh.png",
            "30",
            "36",
            "42",
            "48",
            3
        )
        questionsList.add(que7)
        val que8 = Question(
            8,
            "Suatu seri: 50-40-31-24-18- seri selanjutnya adalah…",
            "https://p2k.unkris.ac.id/_sepakbola/_baca_image.php?td=18&kodegb=150px-Flag_of_Bhutan.png",
            "16",
            "15",
            "14",
            "16",
            4
        )
        questionsList.add(que8)
        val que9 = Question(
            9,
            "2, 10, 4, 14, 6, 18, 8, …",
            "https://p2k.unkris.ac.id/_sepakbola/_baca_image.php?td=18&kodegb=167px-Flag_of_Bulgaria.png",
            "14",
            "16",
            "18",
            "22",
            4
        )
        questionsList.add(que9)
        val que10 = Question(
            10,
            "2, 10, 4, 14, 6, 18, 8, …",
            "https://p2k.unkris.ac.id/_sepakbola/_baca_image.php?td=18&kodegb=150px-Flag_of_Ecuador.png",
            "21",
            "22",
            "23",
            "24",
            2
        )
        questionsList.add(que10)
        return questionsList
    }
}