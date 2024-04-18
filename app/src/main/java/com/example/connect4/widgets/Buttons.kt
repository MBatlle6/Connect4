package com.example.connect4.widgets

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.connect4.Connect4ViewModel
import com.example.connect4.MainActivity
import com.example.connect4.R
import kotlinx.coroutines.delay

@Composable
fun AliasWrittingButton(activity: MainActivity, viewModel: Connect4ViewModel){
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        leadingIcon = {
            Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "")
        },
        value = viewModel.alias.value!!,
        onValueChange = {
            if(it.length < 13) viewModel.setAlias(it)
        },
        label = { Text(text = activity.getString(R.string.alias)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        modifier =
        Modifier
            .padding(0.dp, 50.dp, 0.dp, 20.dp)
            .fillMaxWidth(),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
    )
}

@Composable
fun LittleGridButton(viewModel: Connect4ViewModel){
    RadioButton(
        selected = viewModel.gridSize.value == 5 ,
        onClick = {
            viewModel.setGridSize(5)
        }
    )
}

@Composable
fun MediumGridButton(viewModel: Connect4ViewModel){
    RadioButton(
        selected = viewModel.gridSize.value == 6 ,
        onClick = {
            viewModel.setGridSize(6)
        }
    )
}

@Composable
fun BigGridButton(viewModel: Connect4ViewModel){
    RadioButton(
        selected = viewModel.gridSize.value == 7 ,
        onClick = {
            viewModel.setGridSize(7)
        }
    )

}

@Composable
fun TimeControlButton(viewModel: Connect4ViewModel){
    Checkbox(
        checked = viewModel.timeControl.value!!,
        onCheckedChange = {
            if (!viewModel.timeControl.value!!) viewModel.setTimeControl(true)
            else viewModel.setTimeControl(false)
        },
    )
}

@Composable
fun Timer(activity: MainActivity, viewModel: Connect4ViewModel) {

    if(viewModel.timeControl.value!!){
        LaunchedEffect(Unit) {
            while (viewModel.countdownTime.value!! > 0) {
                if(viewModel.gameFinished.value!!){
                    viewModel.addToLog("\n" + activity.getString(R.string.totalTime) + ": " + viewModel.time.value.toString() + " s")
                    break
                }
                delay(1000L)
                viewModel.decrementTime() //Countdown time
                viewModel.incrementTime()  //Normal Time
            }
            if(!viewModel.gameFinished.value!!){
                Toast.makeText(activity,activity.getString(R.string.timeOver),Toast.LENGTH_LONG).show()
                viewModel.addToLog("\n" + activity.getString(R.string.totalTime) + ": " + viewModel.time.value.toString() + " s")
                viewModel.addToLog("\n" + activity.getString(R.string.timeFinished))
                viewModel.setGameScreen(false)
                viewModel.setLogScreen(true)
                viewModel.setGameFinished(true)
            }


        }
        Text(
            text = viewModel.countdownTime.value!!.toString() + 's',  //Numero segons dins de la partida
            color = Color.Red
        )
    }
    else{
        LaunchedEffect(Unit) {
            while (viewModel.time.value!! < 5000) { //Contador segon 5000 para
                if(viewModel.gameFinished.value!!){
                    viewModel.addToLog("\n" + activity.getString(R.string.totalTime) + ": " + viewModel.time.value.toString() + " s")
                    break
                }
                delay(1000L)
                viewModel.incrementTime()
            }
        }
        Text(
            text = viewModel.time.value!!.toString() + " s", //Numero segons dins de la partida
            color = Color.Blue
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailWrittingButton(viewModel: Connect4ViewModel){
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()
    val controller = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Email, contentDescription = "")
        },
        value = TextFieldValue(viewModel.email.value!!, selection = TextRange(viewModel.email.value!!.length)),
        onValueChange = {
            if(it.text.length < 35) viewModel.setEmail(it.text)  //Llargada opcional
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        modifier =
            Modifier
                .onFocusEvent { controller?.hide() }
                .fillMaxWidth()
                .focusRequester(focusRequester),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()

    }
}
