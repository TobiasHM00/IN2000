package com.example.twitterlite.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.twitterlite.model.Post

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCard(postData: Post) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(20.dp))

            Text(postData.title)
            Text(postData.body)
        }
    }
}