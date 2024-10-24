package com.example.lab2_2



import android.os.Bundle

import android.widget.Button

import android.widget.EditText

import android.widget.TextView

import androidx.activity.ComponentActivity

import kotlin.random.Random



class ExerciseActivity : ComponentActivity() {

    private var correctAnswers = 0

    private var totalQuestions = 0

    private lateinit var exerciseText: TextView

    private lateinit var answerInput: EditText

    private lateinit var checkAnswerButton: Button

    private lateinit var resultText: TextView



    private var number1: Int = 0

    private var number2: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_exercise)



        exerciseText = findViewById(R.id.exercise_text)

        answerInput = findViewById(R.id.answer_input)

        checkAnswerButton = findViewById(R.id.check_answer_button)

        resultText = findViewById(R.id.result_text)



        val mode = intent.getStringExtra("mode")

        val selectedNumber = intent.getIntExtra("selectedNumber", -1)



        generateNewExercise(mode, selectedNumber)



        checkAnswerButton.setOnClickListener {

            val userAnswer = answerInput.text.toString().toIntOrNull()

            if (userAnswer != null && userAnswer == number1 * number2) {

                resultText.text = "Правильный ответ"

                correctAnswers++

            } else {

                resultText.text = "Неверный ответ"

            }

            totalQuestions++

            if (totalQuestions < 20) {

                generateNewExercise(mode, selectedNumber)

            } else {

                val percentage = (correctAnswers.toDouble() / totalQuestions) * 100

                resultText.text = "Тест завершён. Правильные ответы: $correctAnswers из 20. Процент: $percentage%"

            }

            answerInput.text.clear()

        }

    }



    private fun generateNewExercise(mode: String?, selectedNumber: Int) {

        number1 = if (mode == "selective") selectedNumber else Random.nextInt(2, 10)

        number2 = Random.nextInt(2, 10)

        exerciseText.text = "$number1 x $number2 = ?"

    }

}
