package com.example.connect4.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.connect4.Connect4ViewModel
import com.example.connect4.MainActivity
import com.example.connect4.bbdd.LogStrings
import com.example.connect4.bbdd.LogViewModel
import com.example.connect4.data.SettingsDataStore

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun DBAccesScreen(activity: MainActivity, viewModel: Connect4ViewModel, settingsDataStore: SettingsDataStore, logVM: LogViewModel){

    val windowSizeClass = calculateWindowSizeClass(activity = activity)
    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact){
        PhonePortrait(activity = activity, viewModel = viewModel,logVM,  settingsDataStore)
    }
    if (windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact){
        PhoneLandscape(activity = activity, viewModel = viewModel,logVM, settingsDataStore)
    }
    if (windowSizeClass.heightSizeClass == WindowHeightSizeClass.Expanded && windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium){
        TabletPortrait(activity = activity, viewModel = viewModel,logVM, settingsDataStore)
    }
    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded && windowSizeClass.heightSizeClass == WindowHeightSizeClass.Medium){
        TabletLandscape(activity = activity, viewModel = viewModel,logVM, settingsDataStore)
    }

}

@Composable
private fun PhonePortrait(activity: MainActivity, viewModel: Connect4ViewModel,logVM: LogViewModel, settingsDataStore: SettingsDataStore){
    LazyColumn(modifier = Modifier.fillMaxWidth(),verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for (entry in logVM.allWords.value!!){
            item {
                Button(
                    onClick = {activity.finish()}
                )
                {
                    Text(text =entry.alias + "  " +entry.currentTime)
                }
            }

        }
    }

}

@Composable
private fun PhoneLandscape(activity: MainActivity, viewModel: Connect4ViewModel,logVM: LogViewModel, settingsDataStore: SettingsDataStore){
    LazyColumn(modifier = Modifier.fillMaxWidth(),verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for (entry in logVM.allWords.value!!){
            item {
                Button(
                    onClick = {activity.finish()}
                )
                {
                    Text(text =entry.alias + "  " +entry.currentTime)
                }
            }

        }
    }
}

@Composable
private fun TabletPortrait(activity: MainActivity, viewModel: Connect4ViewModel, logVM: LogViewModel,settingsDataStore: SettingsDataStore){
    LazyColumn(modifier = Modifier.fillMaxWidth(),verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for (entry in logVM.allWords.value!!){
            item {
                Button(
                    onClick = {activity.finish()}
                )
                {
                    Text(text =entry.alias + "  " +entry.currentTime)
                }
            }

        }
    }
}

@Composable
private fun TabletLandscape(activity: MainActivity, viewModel: Connect4ViewModel,logVM: LogViewModel, settingsDataStore: SettingsDataStore){
    LazyColumn(modifier = Modifier.fillMaxWidth(),verticalArrangement = Arrangement.spacedBy(8.dp)) {
        for (entry in logVM.allWords.value!!){
            item {
                Button(
                    onClick = {activity.finish()}
                )
                {
                    Text(text =entry.alias + "  " +entry.currentTime)
                }
            }

        }
    }
}


