package com.example.connect4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.connect4.Connect4ViewModel
import com.example.connect4.MainActivity
import com.example.connect4.R

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HelpScreen(activity: MainActivity, viewModel: Connect4ViewModel){

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
    Column {
        Row {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Back Arrow",
                modifier = Modifier
                    .size(48.dp)
                    .padding(0.dp, 7.dp, 0.dp, 0.dp),
            )
            Text(
                text = activity.getString(R.string.help).uppercase(),
                fontSize = 40.sp,
                maxLines = 1
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = activity.getString(R.string.welcome)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = activity.getString(R.string.description)
            )
            Image(
                painter = painterResource(id = R.mipmap.main_icon_foreground),
                contentDescription = "Icon",
                modifier = Modifier.size(300.dp)
            )
            Button(
                onClick =
                {   viewModel.setMainMenu(true)
                    viewModel.setHelpScreen(false)
                },
            ) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "ArrowBack")
                Text(text = activity.getString(R.string.goToGame))
            }
        }
    }
}

@Composable
private fun PhoneLandscape(activity: MainActivity, viewModel: Connect4ViewModel){
    Row {
        Row {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Back Arrow",
                modifier = Modifier
                    .size(48.dp)
                    .padding(0.dp, 7.dp, 0.dp, 0.dp),
            )
            Text(
                text = activity.getString(R.string.help).uppercase(),
                fontSize = 40.sp,
                maxLines = 1
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = activity.getString(R.string.welcome)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = activity.getString(R.string.description)
            )

            Row (verticalAlignment = Alignment.CenterVertically)
            {
                Image(
                    painter = painterResource(id = R.mipmap.main_icon_foreground),
                    contentDescription = "Icon",
                    modifier = Modifier.size(300.dp)
                )
                Button(
                    onClick =
                    {   viewModel.setMainMenu(true)
                        viewModel.setHelpScreen(false)
                    },
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "ArrowBack")
                    Text(text = activity.getString(R.string.goToGame))
                }
            }

        }
    }
}