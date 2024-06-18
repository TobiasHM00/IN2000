package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var result by remember { mutableStateOf(1) }
    var squeez by remember { mutableStateOf(0) }
    val imageResource = when(result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val textResource = when(result){
        1 -> R.string.s1
        2 -> R.string.s2
        3 -> R.string.s3
        else -> R.string.s4
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = textResource), fontSize = 20.sp)
        Spacer(modifier = Modifier.padding(12.dp))
        Button(onClick = {
               if (result == 4){
                   result = 1
                   squeez = 0
               } else if (result == 2 && squeez != 5) {
                    squeez++
               } else {
                   result++
               }
            }) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = null,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeAppTheme {
        LemonadeApp()
    }
}