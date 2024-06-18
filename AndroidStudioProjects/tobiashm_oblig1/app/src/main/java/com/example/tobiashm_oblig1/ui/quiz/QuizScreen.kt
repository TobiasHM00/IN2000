package com.example.tobiashm_oblig1.ui.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tobiashm_oblig1.Screens
import com.example.tobiashm_oblig1.data.Question

@Composable
fun QuizScreen(navController: NavController) {
    var poeng by remember { mutableStateOf(0) } //var to have control of points

    //list of questions sent to QuizUiState
    var quizUiState = QuizUiState(
        listOf(
            Question("Mt.Everest er høyere enn 8800 m.o.h", true),
            Question("Bergen er norges største by", false),
            Question("Kakaobønner dyrkes i jorden", false),
            Question("Bananer vokser på trær", true),
            Question("Simula var det første objektoritenterte programmeringsspårket", true),
            Question("Du kan se den Kinesiske muren fra verdensrommet", false),
            Question("Neshornets horn er laget av kompakt hår", true),
            Question("Den sterkeste muskelen i kroppen er tungen", true),
            Question("Hovedstaden i Tyrkia er Istanbul", false),
            Question("Denne quizen er bra", true)
        ), poeng
    )

    var disableButton by remember { mutableStateOf(true) } //to make button not clickable
    var questionCounter by remember { mutableStateOf(0) } //count what question the quiz is on
    var questionObj by remember { mutableStateOf(quizUiState.list[questionCounter]) }   //to hold the objekt of each question
    var questionStr by remember { mutableStateOf(questionObj.spm) } //get the question string

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(125.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "QuizScreen",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(text = questionStr, fontSize = 15.sp, textAlign = TextAlign.Center)
            Row(modifier = Modifier.padding(20.dp)) {
                Button(
                    onClick = {
                        questionCounter++
                        if (questionCounter > quizUiState.list.size-1 && questionObj.sannEllerUsann) {
                            poeng++
                            quizUiState = quizUiState.copy(poeng = poeng+1)
                            questionStr = "Quizen er ferdig!"
                            disableButton = false
                        } else if (questionCounter > quizUiState.list.size-1){
                            questionStr = "Quizen er ferdig!"
                            disableButton = false
                        } else if (questionObj.sannEllerUsann) {
                            poeng++
                            quizUiState = quizUiState.copy(poeng = poeng+1)
                            questionObj = quizUiState.list[questionCounter]
                            questionStr = questionObj.spm
                        } else {
                            questionObj = quizUiState.list[questionCounter]
                            questionStr = questionObj.spm
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                    modifier = Modifier
                        .height(50.dp)
                        .width(150.dp),
                    enabled = disableButton
                ) {
                    Text(text = "Fakta")
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {
                        questionCounter++
                        if (questionCounter > quizUiState.list.size-1 && !questionObj.sannEllerUsann) {
                            poeng++
                            quizUiState = quizUiState.copy(poeng = poeng+1)
                            questionStr = "Quizen er ferdig!"
                            disableButton = false
                        } else if (questionCounter > quizUiState.list.size-1){
                            questionStr = "Quizen er ferdig!"
                            disableButton = false
                        } else if (!questionObj.sannEllerUsann) {
                            poeng++
                            quizUiState = quizUiState.copy(poeng = poeng+1)
                            questionObj = quizUiState.list[questionCounter]
                            questionStr = questionObj.spm
                        } else {
                            questionObj = quizUiState.list[questionCounter]
                            questionStr = questionObj.spm
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier
                        .height(50.dp)
                        .width(150.dp),
                    enabled = disableButton
                ) {
                    Text(text = "Fleip")
                }
            }
            Text(text = "Poeng: [$poeng/${quizUiState.list.size}]", fontSize = 16.sp)
        }
        BottomButton(navController)
    }
}

@Composable
fun BottomButton(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = {
                navController.navigate(Screens.QuizScreen.name) {
                    popUpTo(Screens.QuizScreen.name) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RectangleShape
        ) {
            Text(text = "Trykk for å restarte Quiz")
        }
    }
}