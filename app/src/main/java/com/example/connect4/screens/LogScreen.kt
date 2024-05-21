package com.example.connect4.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.connect4.Connect4ViewModel
import com.example.connect4.MainActivity
import com.example.connect4.R
import com.example.connect4.widgets.EmailWrittingButton
import com.example.connect4.widgets.SettingsButton
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun LogScreen(activity: MainActivity, viewModel: Connect4ViewModel) {

    val windowSizeClass = calculateWindowSizeClass(activity = activity)
    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact){
        PhonePortrait(activity = activity, viewModel = viewModel)
    }
    if (windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact){
        PhoneLandscape(activity = activity, viewModel = viewModel)
    }



}

fun getCurrentDateAndHour(): String {
    val now = Instant.now()
    val zoneId = ZoneId.systemDefault()
    val localDateTime = LocalDateTime.ofInstant(now, zoneId)

    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a")
    val formattedDateTime = localDateTime.format(formatter)

    return formattedDateTime.toString().uppercase()
}
@Composable
private fun PhonePortrait(activity: MainActivity, viewModel: Connect4ViewModel){
    Column {
        Row {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Back Arrow",
                modifier = Modifier
                    .size(48.dp)
                    .padding(0.dp, 7.dp, 0.dp, 0.dp),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = activity.getString(R.string.results).uppercase(),
                fontSize = 40.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.weight(1f))
            SettingsButton(viewModel = viewModel, false)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Column(modifier = Modifier.padding(10.dp, 0.dp))
        {
            Text(text = activity.getString(R.string.dateAndHour))
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    text = getCurrentDateAndHour(),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = activity.getString(R.string.logValues))
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    text = viewModel.log.value!!,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = activity.getString(R.string.recipientEmail))
            EmailWrittingButton(viewModel = viewModel)
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Button(
                    onClick =
                    {
                        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:")
                            putExtra(Intent.EXTRA_EMAIL, arrayOf(viewModel.email.value!!))
                            putExtra(Intent.EXTRA_SUBJECT, "Log - " + getCurrentDateAndHour() )
                            putExtra(Intent.EXTRA_TEXT, viewModel.log.value!!)
                        }
                        activity.startActivity(emailIntent)

                    },
                ) {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "Email")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = activity.getString(R.string.sendEmail))
                }
                Button(
                    onClick = {
                        viewModel.setLogScreen(false)
                        viewModel.setGameScreen(true)
                        viewModel.resetGame()
                    },
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Close")
                    Text(text = activity.getString(R.string.newGame))

                }
                Button(
                    onClick = { activity.finish() },
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "ArrowBack")
                    Text(text = activity.getString(R.string.exit))

                }
            }
        }



    }
}

@Composable
private fun PhoneLandscape(activity: MainActivity, viewModel: Connect4ViewModel){
    Row {
        Column (
            modifier = Modifier.padding(20.dp)
        )
        {
            Row {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Back Arrow",
                    modifier = Modifier
                        .size(48.dp)
                        .padding(0.dp, 7.dp, 0.dp, 0.dp),
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = activity.getString(R.string.results).uppercase(),
                    fontSize = 40.sp,
                    maxLines = 1
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(35.dp)
            ){
                SettingsButton(viewModel = viewModel, false)
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick =
                    {
                        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:")
                            putExtra(Intent.EXTRA_EMAIL, arrayOf(viewModel.email.value!!))
                            putExtra(Intent.EXTRA_SUBJECT, "Log - " + getCurrentDateAndHour() )
                            putExtra(Intent.EXTRA_TEXT, viewModel.log.value!!)
                        }
                        activity.startActivity(emailIntent)

                    },
                ) {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "Email")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = activity.getString(R.string.sendEmail))
                }
                Button(
                    onClick = {
                        viewModel.setLogScreen(false)
                        viewModel.setGameScreen(true)
                        viewModel.resetGame()
                    },
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Close")
                    Text(text = activity.getString(R.string.newGame))

                }
                Button(
                    onClick = { activity.finish() },
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "ArrowBack")
                    Text(text = activity.getString(R.string.exit))

                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))
        Column(modifier = Modifier.padding(10.dp, 0.dp))
        {
            Text(text = activity.getString(R.string.dateAndHour))
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    text = getCurrentDateAndHour(),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = activity.getString(R.string.logValues))
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    text = viewModel.log.value!!,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = activity.getString(R.string.recipientEmail))
            EmailWrittingButton(viewModel = viewModel)

        }

    }
}


