package com.example.dongsexchange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dongsexchange.ui.theme.DongsExchangeTheme
import java.text.DecimalFormat
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DongsExchangeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Exchanger()
                }
            }
        }
    }
}

@Composable
fun Exchanger() {
    var inputDongs by remember { mutableStateOf("")}
    val dongsToAmount = inputDongs.toDoubleOrNull()?:0.0
    val dec = DecimalFormat("#,###.##")

    val outLiras = dec.format(dongsToAmount*0.0012)
    val outDollars = dec.format(dongsToAmount*0.000041)

   Column (
       horizontalAlignment = Alignment.Start
   ){
       NumberField(value = inputDongs, onValueChange = {inputDongs=it})
       Spacer(modifier = Modifier.height(20.dp))
       Text(text = "$outLiras лир")
       Text(text = NumberFormat.getCurrencyInstance().format(dongsToAmount*0.0038*1.1))
       Text(text = "$outDollars $")
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberField(
    value: String,
    onValueChange: (String) -> Unit
){
    TextField(
        value = value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = onValueChange,
        label = {Text(text = "Сколько донгов поменять?")},
        modifier = Modifier.fillMaxWidth()
    )

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExchangerPreview() {
    DongsExchangeTheme {
        Exchanger()
    }
}