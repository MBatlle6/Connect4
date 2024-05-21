package com.example.connect4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.connect4.Connect4ViewModel
import com.example.connect4.MainActivity
import com.example.connect4.R
import com.example.connect4.backAction
import com.example.connect4.widgets.AliasWrittingButton
import com.example.connect4.widgets.BigGridButton
import com.example.connect4.widgets.LittleGridButton
import com.example.connect4.widgets.MediumGridButton
import com.example.connect4.widgets.TimeControlButton

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ConfigurationScreen(activity: MainActivity, viewModel: Connect4ViewModel){

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
    Column(
    ) {
        Row {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Back Arrow",
                modifier = Modifier
                    .size(48.dp)
                    .padding(0.dp, 7.dp, 0.dp, 0.dp),
            )
            Text(
                text = activity.getString(R.string.settings).uppercase(),
                fontSize = 40.sp,
                maxLines = 1
            )
        }
        Column(
            modifier = Modifier.padding(10.dp,0.dp)
        ){
            AliasWrittingButton(activity = activity, viewModel = viewModel)
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Icon(
                    imageVector = Icons.Filled.Build,
                    contentDescription = "Back Arrow",
                    modifier = Modifier
                        .size(24.dp),
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(activity.getString(R.string.gridSize))
            }
            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "5")
                LittleGridButton(viewModel = viewModel)
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "6")
                MediumGridButton(viewModel = viewModel)
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "7")
                BigGridButton(viewModel = viewModel)
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(activity.getString(R.string.timeControl))
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Image(
                    painter = painterResource(R.drawable.clock),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Clock"
                )
                TimeControlButton(viewModel = viewModel)
            }
            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Button(
                    onClick = {
                        if (viewModel.alias.value != ""){
                            backAction(viewModel, activity)
                        }
                    }
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "ArrowBack")
                    Text(text = activity.getString(R.string.goToGame))
                }
            }

        }
    }
}

@Composable
private fun PhoneLandscape(activity: MainActivity, viewModel: Connect4ViewModel){
    Row(
    ) {
        Row {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Back Arrow",
                modifier = Modifier
                    .size(48.dp)
                    .padding(0.dp, 7.dp, 0.dp, 0.dp),
            )
            Text(
                text = activity.getString(R.string.settings).uppercase(),
                fontSize = 40.sp,
                maxLines = 1
            )
        }
        Column(
            modifier = Modifier.padding(10.dp,0.dp)
        ){
            AliasWrittingButton(activity = activity, viewModel = viewModel)
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Icon(
                    imageVector = Icons.Filled.Build,
                    contentDescription = "Back Arrow",
                    modifier = Modifier
                        .size(24.dp),
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(activity.getString(R.string.gridSize))
            }
            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "5")
                LittleGridButton(viewModel = viewModel)
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "6")
                MediumGridButton(viewModel = viewModel)
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "7")
                BigGridButton(viewModel = viewModel)
            }
            Text(activity.getString(R.string.timeControl))
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Image(
                    painter = painterResource(R.drawable.clock),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Clock"
                )
                TimeControlButton(viewModel = viewModel)
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Button(
                    onClick = {
                        if (viewModel.alias.value != ""){
                            backAction(viewModel, activity)
                        }
                    }
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "ArrowBack")
                    Text(text = activity.getString(R.string.goToGame))
                }
            }

        }
    }
}
