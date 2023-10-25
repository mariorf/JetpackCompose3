package com.example.gtareas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gtareas.ui.theme.GTareasTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp



data class Tarea(val nombre: String, var completada: MutableState<Boolean>)

class MainActivity : ComponentActivity() {

    private val tareas = mutableStateListOf<Tarea>()

    private fun borrarTarea(tarea: Tarea) {
        tareas.remove(tarea)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GTareasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Red
                ) {
                    // Anotar
                    var nombreTarea by remember { mutableStateOf(TextFieldValue("")) }

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .height(38.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            BasicTextField(
                                value = nombreTarea,
                                onValueChange = {
                                    // it es lo que contiene en el momento
                                    nombreTarea = it
                                },
                                modifier = Modifier
                                    .border(1.dp, Color.Black)
                                    .fillMaxSize()
                                    .background(Color.White),
                                textStyle = TextStyle(color = Color.Black, fontSize = 36.sp)
                            )
                        }

                        Button(
                            onClick = {

                                //Agregar la tarea a la lista de tareas
                                if (nombreTarea.text.isNotBlank()) {
                                    tareas.add(Tarea(nombreTarea.text, mutableStateOf(false)))
                                    //Limpiar el campo de texto después de agregar la tarea
                                    nombreTarea = TextFieldValue("")
                                }
                            }
                        ) {
                            Text("Añadir tarea")
                        }

                        // Lista de tareas dentro de un LazyColumn para que pueda scrollear
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            items(tareas) { tarea ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .background(Color.White)
                                ) {
                                    Text(
                                        text = tarea.nombre,
                                        modifier = Modifier.weight(1f),
                                        fontSize = 24.sp
                                    )
                                    Checkbox(
                                        checked = tarea.completada.value,
                                        onCheckedChange = { isChecked ->
                                            tarea.completada.value = isChecked
                                        }
                                    )
                                    Button(
                                        onClick = { borrarTarea(tarea) },
                                        modifier = Modifier.padding(start = 8.dp)
                                    ) {
                                        Text("Borrar")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

