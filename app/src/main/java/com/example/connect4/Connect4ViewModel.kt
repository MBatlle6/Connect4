package com.example.connect4

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class Connect4ViewModel : ViewModel() {

    private val _mainMenu = MutableLiveData<Boolean>(true)
    val mainMenu: LiveData<Boolean> = _mainMenu

    private val _helpScreen = MutableLiveData<Boolean>(false)
    val helpScreen: LiveData<Boolean> = _helpScreen

    private val _configurationScreen = MutableLiveData<Boolean>(false)
    val configurationScreen: LiveData<Boolean> = _configurationScreen

    private val _alias = MutableLiveData<String>("Player1")
    val alias: LiveData<String> = _alias

    private val _log = MutableLiveData<String>("")
    val log: LiveData<String> = _log

    private val _email = MutableLiveData<String>("mbg29@alumnes.udl.cat")
    val email: LiveData<String> = _email

    private val _bigGrid = MutableLiveData<Array<Array<Color>>>(Array(7){ Array(7){Color.White} })
    val bigGrid: LiveData<Array<Array<Color>>> = _bigGrid

    private val _mediumGrid = MutableLiveData<Array<Array<Color>>>(Array(6){ Array(6){Color.White} })
    val mediumGrid: LiveData<Array<Array<Color>>> = _mediumGrid

    private val _littleGrid = MutableLiveData<Array<Array<Color>>>(Array(5){ Array(5){Color.White} })
    val littleGrid: LiveData<Array<Array<Color>>> = _littleGrid

    private val _gridSize = MutableLiveData<Int>(6)
    val gridSize: LiveData<Int> = _gridSize

    private val _timeControl = MutableLiveData<Boolean>(false)
    val timeControl: LiveData<Boolean> = _timeControl

    private val _gameScreen = MutableLiveData<Boolean>(false)
    val gameScreen: LiveData<Boolean> = _gameScreen

    private val _logScreen = MutableLiveData<Boolean>(false)
    val logScreen: LiveData<Boolean> = _logScreen

    private val _time = MutableLiveData<Int>(0)
    val time: LiveData<Int> = _time

    private val _countdownTime = MutableLiveData<Int>(5)
    val countdownTime: LiveData<Int> = _countdownTime

    private val _gameFinished = MutableLiveData<Boolean>(false)
    val gameFinished: LiveData<Boolean> = _gameFinished


    fun setCellColorBigGrid(rowIndex: Int, columnIndex: Int, color: Color) {
        _bigGrid.value!![rowIndex][columnIndex] = color
    }

    fun setCellColorMediumGrid(rowIndex: Int, columnIndex: Int, color: Color) {
        _mediumGrid.value!![rowIndex][columnIndex] = color
    }

    fun setCellColorLittleGrid(rowIndex: Int, columnIndex: Int, color: Color) {
        _littleGrid.value!![rowIndex][columnIndex] = color
    }

    fun setGameFinished(value: Boolean){
        _gameFinished.value = value
    }

    fun resetGame(){
        _time.value = 0
        _countdownTime.value = 5
        _log.value = ""
        _alias.value = "Player1"
        _bigGrid.value = Array(7){ Array(7){Color.White} }
        _mediumGrid.value = Array(6){ Array(6){Color.White} }
        _littleGrid.value = Array(5){ Array(5){Color.White} }
        _gameFinished.value = false

    }

    fun incrementTime(){
        _time.value = _time.value!! + 1
    }

    fun decrementTime(){
        _countdownTime.value = _countdownTime.value!! - 1
    }

    fun setLogScreen(value: Boolean){
        _logScreen.value = value
    }

    fun setGameScreen(value: Boolean){
        _gameScreen.value = value
    }

    fun setTimeControl(value: Boolean){
        _timeControl.value = value
    }

    fun setGridSize(size: Int){
        _gridSize.value = size
    }

    fun setAlias(value: String){
        _alias.value = value
    }

    fun setEmail(value: String){
        _email.value = value
    }

    fun addToLog(value: String){
        _log.value += value
    }

    fun setMainMenu(value: Boolean){
        _mainMenu.value = value
    }

    fun setHelpScreen(value: Boolean){
        _helpScreen.value = value
    }

    fun setConfigurationScreen(value: Boolean){
        _configurationScreen.value = value
    }

    fun tirarturnoPlayer (){

        //tot lo que fa al turno

        tirarturnoIA()//no se segur si aixo aqui
    }

    fun tirarturnoIA(){

    }
}