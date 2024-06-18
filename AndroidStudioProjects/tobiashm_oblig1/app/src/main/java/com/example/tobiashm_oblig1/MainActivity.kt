package com.example.tobiashm_oblig1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tobiashm_oblig1.ui.palindrom.PalindromeScreen
import com.example.tobiashm_oblig1.ui.quiz.QuizScreen
import com.example.tobiashm_oblig1.ui.theme.Tobiashm_oblig1Theme
import com.example.tobiashm_oblig1.ui.unitconverter.UnitConverterScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Tobiashm_oblig1Theme {
                NavHost(
                    navController = navController,
                    startDestination = Screens.PalindromeScreen.name
                ) {
                    composable(Screens.PalindromeScreen.name) {
                        PalindromeScreen(navController = navController)
                    }
                    composable(Screens.UnitConverterScreen.name) {
                        UnitConverterScreen(navController = navController)
                    }
                    composable(Screens.QuizScreen.name) {
                        QuizScreen(navController = navController)
                    }
                }
            }
        }
    }
}

enum class Screens {
    PalindromeScreen,
    UnitConverterScreen,
    QuizScreen
}