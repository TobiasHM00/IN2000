package com.example.jetpackcomposetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Image(
                        title = stringResource(R.string.title),
                        t1 = stringResource(R.string.first_text),
                        t2 = stringResource(R.string.second_text)
                    )
                }
            }
        }
    }
}

@Composable
fun Image(title: String, t1: String, t2: String) {
    val image = painterResource(id = R.drawable.bg_compose_background)
    Box {
        Column {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Text(title = title, t1 = t1, t2 = t2)
        }
    }
}

@Composable
fun Text(title: String, t1: String, t2: String) {
    Column{
        Text(
            text = title,
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp
        )
        Text(text = t1, modifier = Modifier.padding(start = 16.dp, end = 16.dp), textAlign = TextAlign.Justify)
        Text(
            text = t2,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTutorialTheme {
        Image(
            title = stringResource(R.string.title),
            t1 = stringResource(R.string.first_text),
            t2 = stringResource(R.string.second_text)
        )
    }
}