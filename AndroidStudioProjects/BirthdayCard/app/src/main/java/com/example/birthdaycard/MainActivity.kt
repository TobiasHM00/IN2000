package com.example.birthdaycard

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.birthdaycard.ui.theme.BirthdayCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthdayCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BirthdayGreetingImage(message = stringResource(R.string.happy_birthday_text),
                        from = stringResource(R.string.from_text))
                }
            }
        }
    }
}

@Composable
fun BirthdayGreetingImage(message: String, from: String){
    val image = painterResource(id = R.drawable.androidparty)
    Box {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.fillMaxHeight().fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        BirthdayGreeting(message = message, from = from)
    }
}


@Composable
fun BirthdayGreeting(message: String, from: String){
    Column {
        Text(
            text = message,
            fontSize = 36.sp,
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .padding(start = 16.dp, top = 16.dp)
        )
        Text(
            text = from,
            fontSize = 32.sp,
            color = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .padding(start = 16.dp, end = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BirthdayCardTheme {
        BirthdayGreetingImage(
            message = stringResource(R.string.happy_birthday_text),
            from = stringResource(R.string.from_text)
                )
    }
}