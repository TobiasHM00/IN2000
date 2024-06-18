package com.example.tobiashm_oblig2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.tobiashm_oblig2.data.AlpacaUiState
import com.example.tobiashm_oblig2.model.AlpacaParty
import com.example.tobiashm_oblig2.viewmodel.AlpacaViewModel

@Composable
fun AlpacaScreen(alpacaViewModel: AlpacaViewModel = viewModel()) {
    val alpacaUiState by alpacaViewModel.alpacaUiState.collectAsState()
    AlpacaView(alpacaUiState.parties, alpacaUiState, alpacaViewModel)
}

@Composable
fun AlpacaView(alpacas: List<AlpacaParty>, alpacaUiState: AlpacaUiState, alpacaViewModel: AlpacaViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        DistrictDropDownMenu(alpacaViewModel)
        LazyColumn {
            items(alpacas) { alpacaData ->
                AlpacaCards(alpacaData, alpacaUiState)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DistrictDropDownMenu(alpacaViewModel: AlpacaViewModel) {
    //burde lage en string array i string.xml men gidder ikke
    val districts = listOf("Distrikt 1", "Distrikt 2", "Distrikt 3")
    var expanded by remember { mutableStateOf(false) }
    var districtText by remember { mutableStateOf("Distrikt 1") }
    val focusManager = LocalFocusManager.current

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            readOnly = true,
            value = districtText,
            onValueChange = {},
            label = { Text("Velg distrikt") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            districts.forEach { distriktOptions ->
                DropdownMenuItem(
                    text = { Text(distriktOptions) },
                    onClick = {
                        districtText = distriktOptions
                        expanded = false
                        focusManager.clearFocus()
                        alpacaViewModel.loadDistricts(districtText)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlpacaCards(alpacaData: AlpacaParty, alpacaUiState: AlpacaUiState) {
    Card(
        modifier = Modifier.padding(5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
                    .background(color = Color(android.graphics.Color.parseColor(alpacaData.color)))
            )
            Text(
                text = alpacaData.name,
                fontSize = 22.sp,
                modifier = Modifier.padding(5.dp),
            )
            AsyncImage(
                model = alpacaData.img,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "Leader: ${alpacaData.leader}",
                modifier = Modifier.padding(top = 5.dp),
                fontSize = 18.sp
            )

            val votes = alpacaUiState.currentDistrikt?.resultat?.get(alpacaData.id.toInt()-1)?.votes
            val procent = ((votes?.toFloat()?.div(alpacaUiState.currentDistrikt!!.totalVotes))?.times(100))
            val rounded = String.format("%.2f", procent)

            Text(
                text = "Votes: $votes - $rounded%",
                modifier = Modifier.padding(bottom = 5.dp),
                fontSize = 20.sp
            )
        }
    }
}