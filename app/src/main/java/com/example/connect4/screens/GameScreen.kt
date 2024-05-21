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
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.connect4.Connect4ViewModel
import com.example.connect4.MainActivity
import com.example.connect4.R
import com.example.connect4.data.SettingsDataStore
import com.example.connect4.widgets.BigGrid
import com.example.connect4.widgets.LittleGrid
import com.example.connect4.widgets.MediumGrid
import com.example.connect4.widgets.Timer
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GameScreen(activity: MainActivity, viewModel: Connect4ViewModel, settingsDataStore: SettingsDataStore){
    val gridSizeState = settingsDataStore.preferenceFlowGridSize.asLiveData().observeAsState()
    val aliasState = settingsDataStore.preferenceFlowAlias.asLiveData().observeAsState()

    if (viewModel.logWritten.value == false &&
        gridSizeState.value != null &&
        aliasState.value != null
    )
    {
        viewModel.addToLog(activity.getString(R.string.alias) + ": " + aliasState.value!!)
        viewModel.addToLog(" " + activity.getString(R.string.gridSize) + ": " + gridSizeState.value!!)
        viewModel.setLogWritten(true)
    }


    val windowSizeClass = calculateWindowSizeClass(activity = activity)
    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact){
        PhonePortrait(activity = activity, viewModel = viewModel, gridSizeState, settingsDataStore)
    }
    if (windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact){
        PhoneLandscape(activity = activity, viewModel = viewModel, gridSizeState, settingsDataStore)
    }
}




@Composable
private fun PhonePortrait(activity: MainActivity, viewModel: Connect4ViewModel, gridSizeState: State<Int?>, settingsDataStore: SettingsDataStore){
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
            Timer(activity = activity, viewModel = viewModel, settingsDataStore)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            if (gridSizeState.value == 5) LittleGrid(viewModel = viewModel)
            if (gridSizeState.value == 6) MediumGrid(viewModel = viewModel)
            if(gridSizeState.value == 7) BigGrid(viewModel = viewModel)

        }

    }
}

@Composable
private fun PhoneLandscape(activity: MainActivity, viewModel: Connect4ViewModel, gridSizeState: State<Int?>, settingsDataStore: SettingsDataStore){
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
            Timer(activity = activity, viewModel = viewModel, settingsDataStore)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            if (gridSizeState.value == 5) LittleGrid(viewModel = viewModel)
            if (gridSizeState.value == 6) MediumGrid(viewModel = viewModel)
            if(gridSizeState.value == 7) BigGrid(viewModel = viewModel)
        }

    }

}
