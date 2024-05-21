package com.example.connect4.widgets

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.connect4.Connect4ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun TopBar(viewModel: Connect4ViewModel){
    TopAppBar(
        modifier = Modifier.height(48.dp),
        title = {},
        actions = {
            IconButton(
                onClick = {
                    viewModel.setMainMenu(false)
                    viewModel.setLogScreen(false)
                    viewModel.setConfigurationScreen(true)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier.size(32.dp),
                    tint = Color.White
                )
            }
        },
    )
}