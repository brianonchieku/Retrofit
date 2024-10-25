package com.example.cat

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cat.models.CatFacts
import com.example.cat.ui.theme.CatTheme
import com.example.cat.utils.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScreenData()

        }
    }
}

@Composable
fun ScreenData(){
    var fact by remember {
        mutableStateOf(CatFacts())
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    LaunchedEffect (Unit){
        scope.launch {
            val response = try {
                RetrofitInstance.api.getRandomFact()

            }catch (e: HttpException){
                Toast.makeText(context, "http error: ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            }catch (e: IOException){
                Toast.makeText(context, "app error: ${e.message}", Toast.LENGTH_SHORT).show()
                return@launch
            }
            if (response.isSuccessful&&response.body()!= null){
                fact = response.body()!!
            }
        }

    }
    ScreenUi(fact = fact)
}

@Composable
fun ScreenUi(fact: CatFacts){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center) {
        Text(text = "Cat Facts:", modifier = Modifier.padding(bottom = 25.dp), fontSize = 26.sp)
        Text(text = fact.fact, fontSize = 26.sp, fontWeight = FontWeight.Bold, lineHeight = 40.sp)

    }
}