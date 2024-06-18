package com.example.twitterlite.ui.screens

fun convertCmToOther(cmValue: Float, unit: String) : Float {
    val convertedValue = when(unit) {
        "mm" -> cmValue * 10
        "m" -> cmValue / 100
        else -> 0f
    }

    return convertedValue
}