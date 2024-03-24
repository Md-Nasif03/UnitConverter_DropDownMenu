package com.example.unitconverter

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.times
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.nio.file.WatchEvent
import kotlin.math.roundToInt
import kotlin.math.roundToLong
import kotlin.time.times

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}
@Composable
fun UnitConverter(){
    var inputValue by remember {
        mutableStateOf("")}
    var outputValue by remember {
        mutableStateOf("0")
    }
    var inputUInt by remember {
        mutableStateOf("Meter")
    }
    var outputUInt by remember {
        mutableStateOf("Meter")
    }
    var iExpand by remember {
        mutableStateOf(false)
    }
    var oExpand by remember {
        mutableStateOf(false)
    }
    val iConversion= remember {
        mutableStateOf(1.00)
    }
    val oConversion= remember {
        mutableStateOf(1.00)
    }
    fun conversion(){
        var input=inputValue.toDoubleOrNull()?: 0.0
        var result= (input * iConversion.value * 1000000.00/oConversion.value).roundToLong()/1000000.00
        outputValue=result.toString()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement= Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //below each other
        Text(text = "Unit Converter")//Text that we want show the user
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = {inputValue=it
                //here goes what should happen, when the values of outlinedText changed
            },
            label = {Text(text = "Enter the value")}
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            //input box
            Box{
                //input button
                Button(onClick = {
                    iExpand=true
                }) {
                    Text(text = inputUInt)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "dropdown arrow")
                }
                DropdownMenu(expanded = iExpand, onDismissRequest = { iExpand=false }) {
                    DropdownMenuItem(text = { Text(text = "Millimeter")}, onClick = {
                        iExpand=false
                        inputUInt="Millimeter"
                        iConversion.value=0.001
                        conversion()

                    })
                    DropdownMenuItem(text = { Text(text = "Centimeter")}, onClick = {
                        iExpand=false
                        inputUInt="Centimeter"
                        iConversion.value=0.01
                        conversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter")}, onClick = {
                        iExpand=false
                        inputUInt="Meter"
                        iConversion.value=1.0
                        conversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Kilometer")}, onClick = {
                        iExpand=false
                        inputUInt="Kilometer"
                        iConversion.value=1000.0
                        conversion()
                    })
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box {
                //out button
                Button(onClick = {
                    oExpand=true
                }) {
                    Text(text = outputUInt)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "dropdown arrow")
                }
                DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand=false }) {
                    DropdownMenuItem(text = { Text(text = "Millimeter")}, onClick = {
                        oExpand=false
                        outputUInt="Millimeter"
                        oConversion.value=0.001
                        conversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Centimeter")}, onClick = {
                        oExpand=false
                        outputUInt="Centimeter"
                        oConversion.value=0.01
                        conversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter")}, onClick = {
                        oExpand=false
                        outputUInt="Meter"
                        oConversion.value=1.0
                        conversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Kilometer")}, onClick = {
                        oExpand=false
                        outputUInt="Kilometer"
                        oConversion.value=1000.0
                        conversion()
                    })
                }
            }

        }
        Text(text = "Result $outputValue $outputUInt")
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}