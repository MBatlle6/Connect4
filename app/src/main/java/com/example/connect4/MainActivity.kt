package com.example.connect4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.asLiveData
import com.example.connect4.bbdd.LogApplication
import com.example.connect4.bbdd.LogViewModel
import com.example.connect4.bbdd.WordViewModelFactory
import com.example.connect4.data.SettingsDataStore
import com.example.connect4.screens.ConfigurationScreen
import com.example.connect4.screens.DBAccesScreen
import com.example.connect4.screens.GameScreen
import com.example.connect4.screens.HelpScreen
import com.example.connect4.screens.LogScreen
import com.example.connect4.screens.MainMenu
import com.example.connect4.ui.theme.Connect4Theme

class MainActivity : ComponentActivity() {

    private val onBackPressedCallback = object : OnBackPressedCallback(true){  //Gestionar cuanda el user hace return
        override fun handleOnBackPressed() {
            backAction(viewModel,this@MainActivity, settingsDataStore)
        }
    }

    private val viewModel by viewModels<Connect4ViewModel>()
    private val logVM: LogViewModel by viewModels {
        WordViewModelFactory((application as LogApplication).repository)
    }

    lateinit var settingsDataStore: SettingsDataStore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingsDataStore = SettingsDataStore(this)

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        setContent {
            viewModel.mainMenu.observeAsState().value
            viewModel.helpScreen.observeAsState().value
            viewModel.configurationScreen.observeAsState().value
            viewModel.gameScreen.observeAsState().value
            viewModel.logScreen.observeAsState().value
            viewModel.alias.observeAsState().value
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
            viewModel.fromMainMenu.observeAsState().value
            viewModel.fromLogScreen.observeAsState().value
            viewModel.logWritten.observeAsState().value
            viewModel.gameLog.observeAsState().value
            viewModel.dbAccess.observeAsState().value

            Connect4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if(viewModel.mainMenu.value == true) MainMenu(this, viewModel)
                    else if(viewModel.logScreen.value == true) LogScreen(this, viewModel, logVM)
                    else if(viewModel.gameScreen.value == true) GameScreen(this, viewModel, settingsDataStore)
                    else if (viewModel.configurationScreen.value == true) ConfigurationScreen(this, viewModel, settingsDataStore)
                    else if(viewModel.dbAccess.value == true) DBAccesScreen(this, viewModel, settingsDataStore,logVM)
                    else if(viewModel.helpScreen.value == true) HelpScreen(this, viewModel, settingsDataStore)

                }
            }
        }
    }
}
fun backAction(viewModel: Connect4ViewModel, activity: MainActivity, settingsDataStore: SettingsDataStore) {

    settingsDataStore.preferenceFlowAlias.asLiveData().observe(activity) {
        if (it == "") {
            viewModel.setAlias("")
        }
        else{
            viewModel.setAlias(it)
        }
    }

    if(viewModel.alias.value == ""){
        return
    }
    if(viewModel.dbAccess.value == true){
        viewModel.setDBAccess(false)
        viewModel.setMainMenu(true)
        return
    }
    if(viewModel.helpScreen.value == true){
        viewModel.setHelpScreen(false)
        viewModel.setMainMenu(true)
        return
    }
    if(viewModel.configurationScreen.value == true){
        if(viewModel.fromMainMenu.value == true){
            viewModel.setConfigurationScreen(false)
            viewModel.setMainMenu(true)
            viewModel.setFromMainMenu(false)
        }
        else{
            viewModel.setConfigurationScreen(false)
            viewModel.setLogScreen(true)
            viewModel.setFromLogScreen(false)
        }
    }
    else{
        activity.finish()
    }
}