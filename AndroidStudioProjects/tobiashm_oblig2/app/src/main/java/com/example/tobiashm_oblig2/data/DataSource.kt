package com.example.tobiashm_oblig2.data

import com.example.tobiashm_oblig2.model.AlpacaParty
import com.example.tobiashm_oblig2.model.PartiResultat
import com.example.tobiashm_oblig2.model.Votes
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.gson.*
import java.io.InputStream

class DataSource {
    private val client = HttpClient {
        install(ContentNegotiation) {
            gson()
        }
    }

    suspend fun getAlpacas(partier: String): List<AlpacaParty> {
        val alpacasList: AlpacaUiState = client.get(partier).body()
        return alpacasList.parties
    }

    suspend fun getVotesJSON(path: String): List<PartiResultat> {
        val votesList: List<Votes> = client.get(path).body()

        var parti1 = 0
        var parti2 = 0
        var parti3 = 0
        var parti4 = 0
        val resultatList: MutableList<PartiResultat> = mutableListOf()

        votesList.forEach {
            when (it.id) {
                "1" -> parti1++
                "2" -> parti2++
                "3" -> parti3++
                "4" -> parti4++
            }
        }
        resultatList.add(PartiResultat(1,parti1))
        resultatList.add(PartiResultat(2,parti2))
        resultatList.add(PartiResultat(3,parti3))
        resultatList.add(PartiResultat(4,parti4))

        return resultatList
    }

    suspend fun getVotesXML(path: String): List<PartiResultat> {
        val districtThree: String = client.get(path).body()
        val inputStream: InputStream = districtThree.byteInputStream()
        return XmlParse().parse(inputStream)
    }
}