package com.example.eksamensoving

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pizzaButton = binding.pizzabutton
        val pizzaText = binding.pizzatext

        viewModel.getPizza().observe(this) {
            pizzaText.text = it.name
        }

        pizzaButton.setOnClickListener {
            viewModel.loadPizza()
        }
    }
}

class MainActivityViewModel: ViewModel() {
    private val pizzaDataSource = PizzaDataSource()
    private val pizza: MutableLiveData<Pizza> by lazy { MutableLiveData<Pizza>() }

    fun getPizza(): LiveData<Pizza> {
        return pizza
    }

    fun loadPizza() {
        viewModelScope.launch(Dispatchers.IO) {
            pizzaDataSource.fetchRandomPizza()?.also {
                pizza.postValue(it)
            }
        }
    }
}

class PizzaDataSource {
    suspend fun fetchRandomPizza(): Pizza? {
        val path = "https://getrandomfood.com/pizza"
        val gson = Gson()

        try {
            Log.d("MAIN", Fuel.get(path).awaitString())
            return gson.fromJson(Fuel.get(path).awaitString(), Pizza::class.java)
        } catch (exception: Exeption) {
            Log.e("MAIN", "${exception.message}")
        }
        return null
    }
}

data class Pizza(val id: String, val name: String)