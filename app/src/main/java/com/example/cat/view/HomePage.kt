package com.example.cat.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cat.models.CatFacts
import com.example.cat.models.CatViewModel
import com.example.cat.models.NetworkResponse

@Composable
fun HomeData(viewModel: CatViewModel){
    val catData by viewModel.catResults.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cat Facts:", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        when(val results= catData){
            is NetworkResponse.Error -> {
                Text(text = results.message)
            }
            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success -> {
                HomePage(facts = results.data)
            }
            null -> TODO()
        }

    }


}

@Composable
fun HomePage(facts: CatFacts){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = facts.fact, fontSize = 40.sp)

    }
}