package com.example.lab2_2

import android.content.Intent

import android.os.Bundle

import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.text.BasicTextField

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            MultiplicationApp()

        }

    }

}



@Composable

fun MultiplicationApp() {

    var inputNumber by remember { mutableStateOf("") }

    val context = androidx.compose.ui.platform.LocalContext.current



    Column(

        modifier = Modifier

            .fillMaxSize()

            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {

        Image(

            painter = painterResource(id = R.drawable.multiplication_table),

            contentDescription = "Таблица умножения",

            modifier = Modifier.fillMaxWidth().height(200.dp)

        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            val intent = Intent(context, ExerciseActivity::class.java)

            intent.putExtra("mode", "all")

            context.startActivity(intent)

        }) {

            Text(text = "Упражнение для всех чисел")

        }

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(

            value = inputNumber,

            onValueChange = { inputNumber = it },

            modifier = Modifier.fillMaxWidth(),

            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),

            decorationBox = { innerTextField ->

                if (inputNumber.isEmpty()) {

                    Text(text = "Введите число от 2 до 9", color = Color.Gray)

                }

                innerTextField()

            }

        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            val number = inputNumber.toIntOrNull()

            if (number != null && number in 2..9) {

                val intent = Intent(context, ExerciseActivity::class.java)

                intent.putExtra("mode", "selective")

                intent.putExtra("selectedNumber", number)

                context.startActivity(intent)

            }

        }) {

            Text(text = "Упражнение выборочно")

        }

    }

}



@Preview(showBackground = true)

@Composable

fun DefaultPreview() {

    MultiplicationApp()

}
