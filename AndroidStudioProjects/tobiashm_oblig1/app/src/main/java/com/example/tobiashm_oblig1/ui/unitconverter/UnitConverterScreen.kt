package com.example.tobiashm_oblig1.ui.unitconverter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tobiashm_oblig1.R
import com.example.tobiashm_oblig1.Screens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverterScreen(navController: NavController) {
    val units = stringArrayResource(id = R.array.units)
    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coScope = rememberCoroutineScope()

    var input by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(units[0]) }
    var resultat by remember { mutableStateOf(0.0) }
    var resultatStr by remember { mutableStateOf("") }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.padding(120.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "UnitConverterScreen",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(20.dp),
                    fontWeight = FontWeight.Bold
                )
                TextField(
                    value = input,
                    onValueChange = { input = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                    label = { Text(text = "Skriv inn et tall") }
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier.padding(5.dp)
                ) {
                    TextField(
                        // The `menuAnchor` modifier must be passed to the text field for correctness.
                        modifier = Modifier.menuAnchor(),
                        readOnly = true,
                        value = selectedOptionText,
                        onValueChange = {},
                        label = { Text("Velg unit") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                    ) {
                        units.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    selectedOptionText = selectionOption
                                    expanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }

                Button(
                    onClick = {
                        val inputDoubleOrNull: Double? = input.toDoubleOrNull()
                        if (inputDoubleOrNull == null) {
                            coScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Ugylidg input",
                                    duration = SnackbarDuration.Short,
                                )
                            }
                        } else {
                            val unitValueToLiter = when (selectedOptionText) {
                                "Fluid ounce (fl oz)" -> 0.02957
                                "Cup" -> 0.23659
                                "Gallon" -> 3.78541
                                else -> 238.481
                            }
                            resultat = inputDoubleOrNull * unitValueToLiter
                            resultatStr = "%.2f".format(resultat) + "L"
                        }
                        input = ""
                        focusManager.clearFocus()
                    }) {
                    Text(text = "Konverter")
                }
                Text(text = resultatStr, fontSize = 16.sp, modifier = Modifier.padding(15.dp))
            }
            BottomButton(navController)
        }
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
                      navController.navigate(Screens.QuizScreen.name) {
                          popUpTo(Screens.PalindromeScreen.name) { inclusive = true }
                      }
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