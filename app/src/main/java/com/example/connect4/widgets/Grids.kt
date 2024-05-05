package com.example.connect4.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.connect4.Connect4ViewModel


private var i = 0
private var j = 0

@Composable
fun BigGrid(viewModel: Connect4ViewModel){
    Column(
        modifier = Modifier
            .background(Color(0xFF0A1172)),

    ) {
        repeat(7) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                repeat(7) {
                    val rowIndex = i
                    val columnIndex = j
                    Button(
                        onClick = {
                            //PER PASSAR LA GRID ES POT FER QUE ES PASSI PER PARAMETRE AL TURNOJUGADOR
                            viewModel.turnoJugador(rowIndex,columnIndex, viewModel,1)
                            //viewModel.setCellColorBigGrid(rowIndex, columnIndex, Color.Red)
                        },
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(59.dp)
                            .padding(3.dp),
                        colors = ButtonDefaults.buttonColors(viewModel.bigGrid.value!![i][j].first)
                    ){
                    }
                    j++
                }
                j = 0
            }
            i ++
        }
        i = 0
    }
}

@Composable
fun MediumGrid(viewModel: Connect4ViewModel){
    Column(
        modifier = Modifier
            .background(Color(0xFF0A1172)),

        ) {
        repeat(6) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                repeat(6) {
                    val rowIndex = i
                    val columnIndex = j
                    Button(
                        onClick = {
                            viewModel.turnoJugador(rowIndex,columnIndex, viewModel,2)
                        },
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(59.dp)
                            .padding(3.dp),
                        colors = ButtonDefaults.buttonColors(viewModel.mediumGrid.value!![i][j].first)
                    ){
                    }
                    j++
                }
                j = 0
            }
            i ++
        }
        i = 0
    }
}

@Composable
fun LittleGrid(viewModel: Connect4ViewModel){
    Column(
        modifier = Modifier
            .background(Color(0xFF0A1172)),

        ) {
        repeat(5) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                repeat(5) {
                    val rowIndex = i
                    val columnIndex = j
                    Button(
                        onClick = {
                            viewModel.turnoJugador(rowIndex,columnIndex, viewModel,3)
                        },
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(59.dp)
                            .padding(3.dp),
                        colors = ButtonDefaults.buttonColors(viewModel.littleGrid.value!![i][j].first)
                    ){
                    }
                    j++
                }
                j = 0
            }
            i ++
        }
        i = 0
    }
}