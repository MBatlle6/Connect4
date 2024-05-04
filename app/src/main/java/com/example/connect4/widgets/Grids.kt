package com.example.connect4.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.connect4.Connect4ViewModel
import org.w3c.dom.Text

@Composable
fun LazyVerticalGrid(tipusGrid: Int, viewModel: Connect4ViewModel){
    val list = (1..10).map { it.toString() }
    var backgroundColor by remember{ mutableStateOf(Color.Gray)}

    LazyVerticalGrid(
        columns = GridCells.Fixed(tipusGrid),
        content = {
            items(tipusGrid*tipusGrid){i ->
                Button(
                    onClick = {
                        //ficar color
                        backgroundColor = if(backgroundColor == Color.White) Color.Red else Color.White
                        //comprobar si es guanye

                        viewModel.tirarturnoIA()
                    },
                    modifier = Modifier
                        //.padding(16.dp)
                        //.fillMaxWidth()
                        //.height(50.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(8.dp)
                ){
                }

            }
        }

    )
}