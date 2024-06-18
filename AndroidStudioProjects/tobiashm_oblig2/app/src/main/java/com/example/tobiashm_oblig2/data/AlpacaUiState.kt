package com.example.tobiashm_oblig2.data

import com.example.tobiashm_oblig2.model.AlpacaParty
import com.example.tobiashm_oblig2.model.Distrikt

data class AlpacaUiState(
    val parties: List<AlpacaParty>,
    var currentDistrikt: Distrikt?,
)
