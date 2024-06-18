package com.example.tobiashm_oblig2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tobiashm_oblig2.ui.AlpacaScreen
import com.example.tobiashm_oblig2.ui.theme.Tobiashm_oblig2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tobiashm_oblig2Theme {
                AlpacaScreen()
            }
        }
    }
}