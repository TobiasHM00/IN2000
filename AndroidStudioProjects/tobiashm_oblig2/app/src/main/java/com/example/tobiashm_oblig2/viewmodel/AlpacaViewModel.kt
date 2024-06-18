package com.example.tobiashm_oblig2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tobiashm_oblig2.data.AlpacaUiState
import com.example.tobiashm_oblig2.data.DataSource
import com.example.tobiashm_oblig2.model.Distrikt
import com.example.tobiashm_oblig2.model.PartiResultat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlpacaViewModel : ViewModel() {
    private val dataSource = DataSource()

    private val _alpacaUiState = MutableStateFlow(AlpacaUiState(parties = listOf(), null))
    val alpacaUiState: StateFlow<AlpacaUiState> = _alpacaUiState.asStateFlow()

    init {
        loadAlpacas()
        loadDistricts("Distrikt 1")
    }

    private fun loadAlpacas() {
        viewModelScope.launch {
            val alpacas = dataSource.getAlpacas("https://in2000-proxy.ifi.uio.no/alpacaapi/alpacaparties")
            _alpacaUiState.value = AlpacaUiState(alpacas, null)
        }
    }

    fun loadDistricts(districtStr: String) {
        viewModelScope.launch {
            if (districtStr == "Distrikt 3") {
                votesDistrict3(districtStr)
            } else {
                votesDistrict1and2(districtStr)
            }
        }
    }

    private suspend fun votesDistrict1and2(districtStr: String) {
        val resultatList: List<PartiResultat>
        val districtDataSource: DataSource
        val result: Distrikt
        val totaleVotes: Int

        if (districtStr == "Distrikt 1") {
            districtDataSource = DataSource()
            resultatList = districtDataSource.getVotesJSON("https://in2000-proxy.ifi.uio.no/alpacaapi/district1")
            totaleVotes = resultatList[0].votes + resultatList[1].votes + resultatList[2].votes + resultatList[3].votes
            result = Distrikt(resultatList, totaleVotes, districtStr)
        } else {
            districtDataSource = DataSource()
            resultatList = districtDataSource.getVotesJSON("https://in2000-proxy.ifi.uio.no/alpacaapi/district2")
            totaleVotes = resultatList[0].votes + resultatList[1].votes + resultatList[2].votes + resultatList[3].votes
            result = Distrikt(resultatList, totaleVotes, districtStr)
        }

        val partier = alpacaUiState.value.parties
        _alpacaUiState.value = AlpacaUiState(partier, result)
    }

    private suspend fun votesDistrict3(distrikt: String) {
        val dataSource = DataSource()
        val resultatList: List<PartiResultat> = dataSource.getVotesXML("https://in2000-proxy.ifi.uio.no/alpacaapi/district3")
        val totalVotes = resultatList[0].votes + resultatList[1].votes + resultatList[2].votes + resultatList[3].votes

        val result = Distrikt(resultatList, totalVotes, distrikt)
        val partier = alpacaUiState.value.parties
        _alpacaUiState.value = AlpacaUiState(partier, result)
    }
}