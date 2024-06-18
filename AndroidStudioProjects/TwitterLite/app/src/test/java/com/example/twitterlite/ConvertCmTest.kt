package com.example.twitterlite

import com.example.twitterlite.ui.screens.convertCmToOther
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ConvertCmTest {
    @Test
    fun testUnitConverterInto_mm() {
        val cmValue= 10f
        val correctValue = 0.1f

        val conversionResult = convertCmToOther(cmValue, "m")

        assertEquals(correctValue, conversionResult)
    }
}