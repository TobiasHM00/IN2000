package com.example.gruppetimeeksempler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gruppetimeeksempler.ui.theme.GruppetimeEksemplerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val norske_ord = stringArrayResource(R.array.norske_ord)
            val engelske_ord = stringArrayResource(R.array.engelske_ord)

            val snackbarHostState = remember { SnackbarHostState() }
            val coScope = rememberCoroutineScope()
            var expanded by remember { mutableStateOf(false) }
            var selectedOptionText by remember { mutableStateOf(engelske_ord[0]) }

            GruppetimeEksemplerTheme {
                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    content = { innerPadding ->
                        Column(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            //Alle komponenter her!
                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = { expanded = !expanded },
                            ) {
                                TextField(
                                    // The `menuAnchor` modifier must be passed to the text field for correctness.
                                    modifier = Modifier.menuAnchor(),
                                    readOnly = true,
                                    value = selectedOptionText,
                                    onValueChange = {},
                                    label = { Text( "Noe" ) },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false },
                                ) {
                                    engelske_ord.forEach { ord ->
                                        DropdownMenuItem(
                                            text = { Text(ord) },
                                            onClick = {
                                                selectedOptionText = ord
                                                expanded = false
                                            }
                                        )
                                    }
                                }
                            }


                            Button(
                                onClick = {
                                    coScope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "Du valgte $selectedOptionText",
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
                            ) {
                                Text("Trykk for Snackbar!")
                            }
                        }
                    }
                )
            }
        }
    }
}