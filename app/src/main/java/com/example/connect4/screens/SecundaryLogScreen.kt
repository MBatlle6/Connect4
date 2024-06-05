package com.example.connect4.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.connect4.Connect4ViewModel
import com.example.connect4.MainActivity
import com.example.connect4.R
import com.example.connect4.backAction
import com.example.connect4.bbdd.LogViewModel
import com.example.connect4.data.SettingsDataStore

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun SecundaryLogScreen(activity: MainActivity, viewModel: Connect4ViewModel,logVM:LogViewModel, settingsDataStore: SettingsDataStore){
    val windowSizeClass = calculateWindowSizeClass(activity = activity)
    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact){
        PhonePortrait(activity = activity, viewModel = viewModel,logVM,  settingsDataStore)
    }
    if (windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact){
        PhoneLandscape(activity = activity, viewModel = viewModel,logVM, settingsDataStore)
    }

}
@Composable
private fun PhonePortrait(activity: MainActivity, viewModel: Connect4ViewModel,logVM: LogViewModel, settingsDataStore: SettingsDataStore){
    Column {
        Text(text =  logVM.allWords.value!![viewModel.entryId.value!!].alias + logVM.allWords.value!![viewModel.entryId.value!!].data)
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = { backAction(viewModel,activity,settingsDataStore) }) {
            Text(text = activity.getString(R.string.GoBack))
        }
    }




}

@Composable
private fun PhoneLandscape(activity: MainActivity, viewModel: Connect4ViewModel,logVM: LogViewModel, settingsDataStore: SettingsDataStore){
    Column {
        Text(text =  logVM.allWords.value!![viewModel.entryId.value!!].alias + logVM.allWords.value!![viewModel.entryId.value!!].data)
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = { backAction(viewModel,activity,settingsDataStore) }) {
            Text(text = activity.getString(R.string.GoBack))
        }
    }
}