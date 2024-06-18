package com.example.tobiashm_oblig1.ui.palindrom

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tobiashm_oblig1.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PalindromeScreen(navController: NavController) {
    var input by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.padding(125.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "PalindromeScreen",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            TextField(
                value = input,
                onValueChange = {input = it},
                label = {Text("Skriv inn et palindrom")},
                modifier = Modifier.padding(20.dp)
            )
            Button(
                onClick = {
                    answer = if (checkPalindrome(input)) {
                        "Dette er et Palindrom!"
                    } else {
                        "Dette er ikke et Palindrom"
                    }
                    input = ""
                    focusManager.clearFocus()
                }
            ) {
                Text(text = "Sjekk")
            }
            Text(text = answer, fontSize = 16.sp, modifier = Modifier.padding(15.dp))
        }
        BottomButton(navController)
    }
}

@Composable
fun BottomButton(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                navController.navigate(Screens.UnitConverterScreen.name)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RectangleShape
        ) {
            Text(text = "Trykk for neste skjerm")
        }
    }
}

fun checkPalindrome(text: String): Boolean{
    val tekst = text.lowercase()
    val reversed = tekst.reversed()
    return tekst == reversed
}