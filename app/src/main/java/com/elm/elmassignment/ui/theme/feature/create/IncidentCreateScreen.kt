package com.elm.elmassignment.ui.theme.feature.create

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable

fun IncidentCreateScreen(navController: NavController) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val descState = remember { mutableStateOf(TextFieldValue()) }
        val latitudeState = remember { mutableStateOf(TextFieldValue()) }
        val longitudeState = remember { mutableStateOf(TextFieldValue()) }


        TextField(
            value = descState.value,
            onValueChange = { descState.value = it },
            label = { Text("Enter description") }
        )
        TextField(
            value = latitudeState.value,
            onValueChange = { latitudeState.value = it },
            label = { Text("Enter latitude") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)


        )
        TextField(
            value = longitudeState.value,
            onValueChange = { longitudeState.value = it },
            label = { Text("Enter longitude") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

        )

        Button(
            onClick = {
                val desc = descState.value.text
                val latitude = latitudeState.value.text
                val longitude = longitudeState.value.text
                if (desc.isNotEmpty() && latitude.isNotEmpty() && longitude.isNotEmpty()) {
                    Toast.makeText(context, "saved", Toast.LENGTH_LONG).show()
                    navController.navigateUp()
                } else
                    Toast.makeText(context, "enter all fields", Toast.LENGTH_LONG).show()

            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Submit")
        }
    }
}

