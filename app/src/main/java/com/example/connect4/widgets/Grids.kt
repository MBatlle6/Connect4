package com.example.connect4.widgets

import android.provider.Settings.Global.getString
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.connect4.Connect4ViewModel
import com.example.connect4.MainActivity
import com.example.connect4.R
import org.w3c.dom.Text as Text

@Composable
fun LazyVerticalGrid(tipusGrid: Int, viewModel: Connect4ViewModel){
    val list = (1..tipusGrid*tipusGrid).map { it.toString() }
    LazyVerticalGrid(
        columns = GridCells.Fixed(tipusGrid),
        content = {
            items(tipusGrid*tipusGrid){i ->
                val buttonColor = remember{ mutableStateOf(Color.White)}
                Box (
                    modifier = Modifier
                        .background(Color.Blue),
                ){
                    Button(
                        onClick = {
                            //ficar color
                            if(buttonColor.value == Color.White){ buttonColor.value = Color.Red}
                            else if(buttonColor.value == Color.Red || buttonColor.value == Color.Yellow){
                             //showInGameToast(activity = MainActivity())
                            }
                            //comprobar si es guanye

                            viewModel.tirarturnoIA()
                        },
                        modifier = Modifier

                            .background(buttonColor.value)
                            .fillMaxWidth()
                            .fillMaxHeight(),

                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(Color.Transparent),

                    ){
                        Text(text = list[i],
                            color = Color(0xFFD0BCFF)
                        )
                    }
                }
            }
        }
    )
}
