package com.example.topiccourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.topiccourses.ui.theme.TopicCoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopicCoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TopicGrid()
                }
            }
        }
    }
}

@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {
        items(DataSource.topics) { topic ->
            CourseCard(topic)
        }
    }
}

@Composable
fun CourseCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(elevation = 4.dp) {
        Row {
            Box {
                Image(
                    painter = painterResource(topic.imageRes),
                    contentDescription = null,
                    modifier = modifier
                        .size(height = 68.dp, width = 68.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
            Column {
                Text(
                    text = stringResource(topic.name),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(
                        bottom = 8.dp,
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .size(12.dp)
                            .padding(start = 16.dp)
                    )
                    Text(
                        text = topic.numberOfCourses.toString(),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
            }
        }
    }
}