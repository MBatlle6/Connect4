package com.example.connect4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.connect4.Connect4ViewModel
import com.example.connect4.MainActivity
import com.example.connect4.R
import com.example.connect4.widgets.BigGrid
import com.example.connect4.widgets.LittleGrid
import com.example.connect4.widgets.MediumGrid
import com.example.connect4.widgets.Timer

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GameScreen(activity: MainActivity, viewModel: Connect4ViewModel){

    if (viewModel.logWritten.value == false){
        viewModel.addToLog(activity.getString(R.string.alias) + ": " + viewModel.alias.value!!)
        viewModel.addToLog(" " + activity.getString(R.string.gridSize) + ": " + viewModel.gridSize.value!!)
        viewModel.setLogWritten(true)
    }


    val windowSizeClass = calculateWindowSizeClass(activity = activity)
    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact){
        PhonePortrait(activity = activity, viewModel = viewModel)
    }
    if (windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact){
        PhoneLandscape(activity = activity, viewModel = viewModel)
    }
}


@Composable
private fun PhonePortrait(activity: MainActivity, viewModel: Connect4ViewModel){
    Column()
    {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.End),
        )
        {
            Image(
                painter = painterResource(R.drawable.clock),
                modifier = Modifier.size(24.dp),
                contentDescription = "Clock",
            )
            Spacer(modifier = Modifier.width(20.dp))
            Timer(activity = activity, viewModel = viewModel)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            if (viewModel.gridSize.value == 5) LittleGrid(viewModel = viewModel)
            else if (viewModel.gridSize.value == 6) MediumGrid(viewModel = viewModel)
            else BigGrid(viewModel = viewModel)

        }

    }
}

@Composable
private fun PhoneLandscape(activity: MainActivity, viewModel: Connect4ViewModel){
    Row()
    {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Top),
        )
        {
            Image(
                painter = painterResource(R.drawable.clock),
                modifier = Modifier.size(24.dp),
                contentDescription = "Clock",
            )
            Spacer(modifier = Modifier.width(20.dp))
            Timer(activity = activity, viewModel = viewModel)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            if (viewModel.gridSize.value == 5) LittleGrid(viewModel = viewModel)
            else if (viewModel.gridSize.value == 6) MediumGrid(viewModel = viewModel)
            else BigGrid(viewModel = viewModel)

        }

    }

}
