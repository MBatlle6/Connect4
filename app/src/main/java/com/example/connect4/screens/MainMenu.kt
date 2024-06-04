package com.example.connect4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.connect4.Connect4ViewModel
import com.example.connect4.MainActivity
import com.example.connect4.R
import com.example.connect4.widgets.SettingsButton
import com.example.connect4.widgets.TopBar

@Composable
fun MainMenu(activity: MainActivity, viewModel: Connect4ViewModel){
    Row {
        Image(
            painter = painterResource(id = R.mipmap.main_icon_foreground),
            contentDescription = "Icon",
            modifier = Modifier.size(70.dp)
        )
        Text(
            text = activity.getString(R.string.mainMenu).uppercase(),
            fontSize = 40.sp,
            maxLines = 1
            )
        Spacer(modifier = Modifier.weight(1f))
        SettingsButton(viewModel = viewModel, true)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Button(
            onClick =
            {   viewModel.setMainMenu(false)
                viewModel.setHelpScreen(true)
            },
        ) {
            Text(text = activity.getString(R.string.help))
        }
        Button(
            onClick = {
                viewModel.setMainMenu(false)
                viewModel.setHelpScreen(false)
                viewModel.setGameScreen(true)
                      },
        ) {
            Text(text = activity.getString(R.string.startGame))
        }
        Button(
            onClick = {
                viewModel.setMainMenu(false)
                viewModel.setHelpScreen(false)
                viewModel.setConfigurationScreen(false)
                viewModel.setDBAccess(true)
            },
        ) {
            Text(text = activity.getString(R.string.checkGames))
        }
        Button(
            onClick = { activity.finish() },
        ) {
            Text(text = activity.getString(R.string.exit))
        }
    }
}