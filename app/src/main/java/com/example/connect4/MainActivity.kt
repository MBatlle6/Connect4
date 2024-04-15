package com.example.connect4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.connect4.screens.ConfigurationScreen
import com.example.connect4.screens.GameScreen
import com.example.connect4.screens.HelpScreen
import com.example.connect4.screens.LogScreen
import com.example.connect4.screens.MainMenu
import com.example.connect4.ui.theme.Connect4Theme

class MainActivity : ComponentActivity() {


    private val onBackPressedCallback = object : OnBackPressedCallback(true){  //Gestionar cuanda el user hace return
        override fun handleOnBackPressed() {
            backAction(viewModel)
        }
    }

    private val viewModel by viewModels<Connect4ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        setContent {
            viewModel.mainMenu.observeAsState().value
            viewModel.helpScreen.observeAsState().value
            viewModel.configurationScreen.observeAsState().value
            viewModel.gameScreen.observeAsState().value
            viewModel.logScreen.observeAsState().value
            viewModel.alias.observeAsState().value
            viewModel.gridSize.observeAsState().value
            viewModel.timeControl.observeAsState().value
            viewModel.time.observeAsState().value
            viewModel.time.observeAsState().value
            viewModel.countdownTime.observeAsState().value
            viewModel.gameFinished.observeAsState().value
            viewModel.log.observeAsState().value
            viewModel.email.observeAsState().value
            viewModel.bigGrid.observeAsState().value
            viewModel.mediumGrid.observeAsState().value
            viewModel.littleGrid.observeAsState().value

            Connect4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if(viewModel.mainMenu.value == true) MainMenu(this, viewModel)
                    else if(viewModel.helpScreen.value == true) HelpScreen(this, viewModel)
                    else if (viewModel.configurationScreen.value == true) ConfigurationScreen(this, viewModel)
                    else if(viewModel.gameScreen.value == true) GameScreen(this, viewModel)
                    else LogScreen(this, viewModel)
                }
            }
        }
    }

}
fun backAction(viewModel: Connect4ViewModel) {
    if(viewModel.helpScreen.value == true){
        viewModel.setHelpScreen(false)
        viewModel.setMainMenu(true)
    }
}

