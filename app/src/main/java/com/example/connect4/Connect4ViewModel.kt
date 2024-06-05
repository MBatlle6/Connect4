package com.example.connect4

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect4.data.SettingsDataStore
import kotlinx.coroutines.launch
import kotlin.random.Random


class Connect4ViewModel : ViewModel() {

    private val _mainMenu = MutableLiveData<Boolean>(true)
    val mainMenu: LiveData<Boolean> = _mainMenu

    private val _dbAccess = MutableLiveData<Boolean>(true)
    val dbAccess: LiveData<Boolean> = _dbAccess

    private val _helpScreen = MutableLiveData<Boolean>(false)
    val helpScreen: LiveData<Boolean> = _helpScreen

    private val _configurationScreen = MutableLiveData<Boolean>(false)
    val configurationScreen: LiveData<Boolean> = _configurationScreen

    private val _alias = MutableLiveData<String>("Player1")
    val alias: LiveData<String> = _alias

    private val _log = MutableLiveData<String>("") //log final
    val log: LiveData<String> = _log

    private val _gameLog = MutableLiveData<String>("") //log que es va creant durant la partida
    val gameLog: LiveData<String> = _gameLog

    private val _email = MutableLiveData<String>("mbg29@alumnes.udl.cat")
    val email: LiveData<String> = _email

    private val _bigGrid = MutableLiveData<Array<Array<Pair<Color, String>>>>(Array(7) { Array(7) { Pair(Color.White, "W") } })
    val bigGrid: LiveData<Array<Array<Pair<Color,String>>>> = _bigGrid

    private val _mediumGrid = MutableLiveData<Array<Array<Pair<Color, String>>>>(Array(6) { Array(6) { Pair(Color.White, "W") } })
    val mediumGrid: LiveData<Array<Array<Pair<Color,String>>>> = _mediumGrid

    private val _littleGrid = MutableLiveData<Array<Array<Pair<Color, String>>>>(Array(5) { Array(5) { Pair(Color.White, "W") } })
    val littleGrid: LiveData<Array<Array<Pair<Color,String>>>> = _littleGrid

    private val _timeControl = MutableLiveData<Boolean>(true)
    val timeControl: LiveData<Boolean> = _timeControl

    private val _gameScreen = MutableLiveData<Boolean>(false)
    val gameScreen: LiveData<Boolean> = _gameScreen

    private val _logScreen = MutableLiveData<Boolean>(false)
    val logScreen: LiveData<Boolean> = _logScreen

    private val _time = MutableLiveData<Int>(0)
    val time: LiveData<Int> = _time

    private val _countdownTime = MutableLiveData<Int>(15)
    val countdownTime: LiveData<Int> = _countdownTime

    private val _gameFinished = MutableLiveData<Boolean>(false)
    val gameFinished: LiveData<Boolean> = _gameFinished

    private val _fromMainMenu = MutableLiveData<Boolean>(false)
    val fromMainMenu: LiveData<Boolean> = _fromMainMenu

    private val _fromLogScreen = MutableLiveData<Boolean>(false)
    val fromLogScreen: LiveData<Boolean> = _fromLogScreen

    private val _logWritten = MutableLiveData<Boolean>(false)
    val logWritten: LiveData<Boolean> = _logWritten

    private val _logDBWritten = MutableLiveData<Boolean>(false)
    val logDBWritten: LiveData<Boolean> = _logDBWritten

    private val _numeroDeFixes = MutableLiveData<Int>(0)
    val numeroDeFixes: LiveData<Int> = _numeroDeFixes

    private val _resultat = MutableLiveData<String>("")
    val resultat: LiveData<String> = _resultat

    fun setLogWritten(value: Boolean){
        _logWritten.value = value
    }

    fun setLogDBWritten(value: Boolean){
        _logDBWritten.value = value
    }

    fun setFromMainMenu(value: Boolean){
        _fromMainMenu.value = value
    }

    fun setFromLogScreen(value: Boolean){
        _fromLogScreen.value = value
    }


    //mira si la posició on volem ficar la fixa esta lliure
    fun ifposiciocorrecta(rowIndex: Int, columnIndex: Int,grid:Int):Boolean{
        val w:String = "W"
        if (grid == 1){
            val positionToCheck:String?=  getCellColorBigGrid(rowIndex,columnIndex)
            if (positionToCheck == w){return true}
        }else if (grid == 2){
            val positionToCheck:String?=  getCellColorMediumGrid(rowIndex,columnIndex)
            if (positionToCheck == w){return true}
        }else if(grid == 3){
            val positionToCheck:String?=  getCellColorLittleGrid(rowIndex,columnIndex)
            if (positionToCheck == w){return true}
        }
        return false
    }

    //---------------BIG GRID---------------//
    fun setCellColorBigGrid(rowIndex: Int, columnIndex: Int, color: Color, text: String) {

        if(ifposiciocorrecta(rowIndex,columnIndex,1)){
            val currentGrid = _bigGrid.value
            currentGrid?.let { grid->
                for(i in 6 downTo rowIndex ){
                    if(ifposiciocorrecta(i,columnIndex,1)){
                        grid[i][columnIndex] = Pair(color,text)
                        println("Fitxa ficada a la posició $i,$columnIndex ")
                        break
                    }
                }
                _bigGrid.postValue(grid)
            }
        }else{
            println("Position $rowIndex $columnIndex incorrecta")
        }
    }
    fun getCellColorBigGrid(rowIndex: Int,columnIndex: Int): String? {
        val currentGrid = _bigGrid.value
        return if (rowIndex >= 0 && rowIndex < 7 && columnIndex >= 0 && columnIndex < 7) {
            currentGrid?.let { grid ->
                grid[rowIndex][columnIndex].second
            }
        } else {
            null
        }
    }


    //---------------MEDIUM GRID---------------//
    fun setCellColorMediumGrid(rowIndex: Int, columnIndex: Int, color: Color, text: String) {

        if(ifposiciocorrecta(rowIndex,columnIndex,2)){
            val currentGrid = _mediumGrid.value
            currentGrid?.let { grid->
                for(i in 5 downTo rowIndex ){
                    if(ifposiciocorrecta(i,columnIndex,2)){
                        grid[i][columnIndex] = Pair(color,text)
                        println("Fitxa ficada a la posició $i,$columnIndex ")
                        break
                    }
                }
                _mediumGrid.postValue(grid)
            }
        }else{
            println("Position $rowIndex $columnIndex incorrecta")
        }
    }

    fun getCellColorMediumGrid(rowIndex: Int,columnIndex: Int): String? {
        val currentGrid = _mediumGrid.value
        return if (rowIndex >= 0 && rowIndex < 7 && columnIndex >= 0 && columnIndex < 7) {
            currentGrid?.let { grid ->
                grid[rowIndex][columnIndex].second
            }
        } else {
            null
        }
    }

    //---------------SMALL GRID---------------//
    fun setCellColorLittleGrid(rowIndex: Int, columnIndex: Int, color: Color, text: String) {

        if(ifposiciocorrecta(rowIndex,columnIndex,3)){
            val currentGrid = _littleGrid.value
            currentGrid?.let { grid->
                for(i in 4 downTo rowIndex ){
                    if(ifposiciocorrecta(i,columnIndex,3)){
                        grid[i][columnIndex] = Pair(color,text)
                        println("Fitxa ficada a la posició $i,$columnIndex ")
                        break
                    }
                }
                _littleGrid.postValue(grid)
            }
        }else{
            println("Position $rowIndex $columnIndex incorrecta")
        }
    }
    fun getCellColorLittleGrid(rowIndex: Int,columnIndex: Int): String? {
        val currentGrid = _littleGrid.value
        return if (rowIndex >= 0 && rowIndex < 7 && columnIndex >= 0 && columnIndex < 7) {
            currentGrid?.let { grid ->
                grid[rowIndex][columnIndex].second
            }
        } else {
            null
        }
    }

    fun setGameFinished(value: Boolean){
        _gameFinished.value = value
    }

    fun resetGame(){
        _time.value = 0
        _countdownTime.value = 5
        _gameLog.value = ""
        _log.value = ""
        _bigGrid.value = Array(7){ Array(7){Pair(Color.White, "W")} }
        _mediumGrid.value = Array(6){ Array(6){Pair(Color.White, "W")} }
        _littleGrid.value = Array(5){ Array(5){Pair(Color.White, "W")} }
        _numeroDeFixes.value = 0
        _gameFinished.value = false
        _logWritten.value = false
        _logDBWritten.value = false
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

    fun setAlias(value: String){
        _alias.value = value
    }

    fun setEmail(value: String){
        _email.value = value
    }

    fun addToLog(value: String){
        _log.value += value
    }

    fun addToGameLog(value: String){

        var i= 1
        var j = 1
        _gameLog.value = _gameLog.value +i + j + value
    }

    fun setMainMenu(value: Boolean){
        _mainMenu.value = value
    }

    fun setDBAccess(value: Boolean){
        _dbAccess.value = value
    }

    fun setHelpScreen(value: Boolean){
        _helpScreen.value = value
    }

    fun setConfigurationScreen(value: Boolean){
        _configurationScreen.value = value
    }
    fun turnoJugador(i: Int, j:Int ,viewModel: Connect4ViewModel,grid:Int){
        if(grid == 1){//BigGrid
            if(ifposiciocorrecta(i,j,grid)){
                viewModel.setCellColorBigGrid(i, j, Color.Red,"R")
                //per mirar si el tauler esta complet
                _numeroDeFixes.value = +1
                if(_numeroDeFixes.value == 49){
                    if (comprobarsihemguanyatGran()){
                        println("Joc Finalitzat")
                        _resultat.value = "Victoria del jugador ROIG"
                        setGameFinished(true)
                    }
                }else if (_numeroDeFixes.value == 48){
                    _resultat.value = "EMPAT"
                    setGameFinished(true)
                }
                if (comprobarsihemguanyatGran()){
                    println("Joc Finalitzat")
                    _resultat.value = "Victoria del jugador ROIG"
                    setGameFinished(true)
                }else{
                    turnoIA(viewModel,grid)
                    _numeroDeFixes.value = +1
                }
            }else{
                println("Position $i,$j incorrecta")
            }
        }

        if (grid == 2){//MediumGrid
            if(ifposiciocorrecta(i,j,grid)){
                viewModel.setCellColorMediumGrid(i, j, Color.Red,"R")
                _numeroDeFixes.value = +1
                if (comprobarSiHemGuanyatMitja()){
                    println("Joc Finalitzat")
                    _resultat.value = "Victoria del jugador ROIG"
                    setGameFinished(true)
                }else{
                    turnoIA(viewModel,grid)
                    _numeroDeFixes.value = +1
                }
            }else{
                println("Position $i,$j incorrecta")
            }
            if(_numeroDeFixes.value == 36){
                setGameFinished(true)
                _resultat.value = "ningú, EMPAT"
            }
        }

        if (grid == 3){//LittleGrid
            if(ifposiciocorrecta(i,j,grid)){
                viewModel.setCellColorLittleGrid(i, j, Color.Red,"R")
                _numeroDeFixes.value = +1
                if(_numeroDeFixes.value == 25){
                    if (comprobarsihemguanyatPetit()){
                        println("Joc Finalitzat")
                        _resultat.value = "Victoria del jugador ROIG"
                        setGameFinished(true)
                    }
                    _resultat.value = "EMPAT"
                    setGameFinished(true)
                    return
                }
                if (comprobarsihemguanyatPetit()){
                    println("Joc Finalitzat")
                    _resultat.value = "Vicotira del jugador ROIG"
                    setGameFinished(true)
                }else{
                    turnoIA(viewModel,grid)
                    _numeroDeFixes.value = +1
                }
            }else{
                println("Position $i,$j incorrecta")
            }
        }
    }

    private fun turnoIA(viewModel: Connect4ViewModel, grid: Int){
        if (grid == 1){
            val i = Random.nextInt(0,7)
            val j = Random.nextInt(0,7)
            if(ifposiciocorrecta(i,j,grid)){
                viewModel.setCellColorBigGrid(i,j,Color.Yellow,"Y")
                if (comprobarsihemguanyatGran()){
                    println("Joc Finalitzat, guanya groc BigGrid")
                    _resultat.value = "Victoria del jugador GROC"
                    setGameFinished(true)
                }
            }else{
                turnoIA(viewModel,1)
            }
        }
        if (grid == 2){
            val i = Random.nextInt(0,6)
            val j = Random.nextInt(0,6)
            if(ifposiciocorrecta(i,j,grid)){
                viewModel.setCellColorMediumGrid(i,j,Color.Yellow,"Y")
                if (comprobarSiHemGuanyatMitja()){
                    println("Joc Finalitzat")
                    _resultat.value = "Vicotira del jugador GROC"
                    setGameFinished(true)
                }
            }else{
                turnoIA(viewModel,2)
            }
        }
        if (grid == 3){
            val i = Random.nextInt(0,5)
            val j = Random.nextInt(0,5)
            if(ifposiciocorrecta(i,j,grid)){
                viewModel.setCellColorLittleGrid(i,j,Color.Yellow,"Y")
                if (comprobarsihemguanyatPetit()){
                    println("Joc Finalitzat")
                    _resultat.value = "Vicotira del jugador GROC"
                    setGameFinished(true)
                }
            }else{
                turnoIA(viewModel,3)
            }
        }
    }

    private fun comprobarsihemguanyatGran():Boolean{
        val w:String = "W"
        val i = 1
        val currentGrid = _bigGrid.value
        //----------matriu gran----------
        currentGrid?.let { grid->
            var positionToCheck:String?
            // mirem files
            for (row in 0 until 7) {
                for (col in 0 until 4) {
                    if (grid[row][col].second == grid[row][col + 1].second &&
                        grid[row][col].second == grid[row][col + 2].second &&
                        grid[row][col].second == grid[row][col + 3].second) {
                        positionToCheck =  getCellColorBigGrid(row,col)
                        if(positionToCheck != w){
                            return true
                        }
                    }
                }
            }

            // mirem columnes
            for (col in 0 until 7) {
                for (row in 0 until 4) {
                    if (grid[row][col].second == grid[row + 1][col].second &&
                        grid[row][col].second == grid[row + 2][col].second &&
                        grid[row][col].second == grid[row + 3][col].second) {
                        positionToCheck =  getCellColorBigGrid(row,col)
                        if(positionToCheck != w){
                            return true
                        }
                    }
                }
            }

            // diagonals (esquerra a dalt- dreta a baix )
            for (row in 0 until 4) {
                for (col in 0 until 4) {
                    if (grid[row][col].second == grid[row + 1][col + 1].second &&
                        grid[row][col].second == grid[row + 2][col + 2].second &&
                        grid[row][col].second == grid[row + 3][col + 3].second) {
                        positionToCheck =  getCellColorBigGrid(row,col)
                        if(positionToCheck != w){
                            return true
                        }
                    }
                }
            }

            // diagonals (dreta dalt- esquerra baix) (right-top to left-bottom)
            for (row in 0 until 4) {
                for (col in 3 until 7) {
                    if (grid[row][col].second == grid[row + 1][col - 1].second &&
                        grid[row][col].second == grid[row + 2][col - 2].second &&
                        grid[row][col].second == grid[row + 3][col - 3].second) {
                        positionToCheck =  getCellColorBigGrid(row,col)
                        if(positionToCheck != w){
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    private fun comprobarSiHemGuanyatMitja():Boolean{
        val w:String = "W"
        //----------matriu mitjana----------
        val currentGrid2 = _mediumGrid.value
        currentGrid2?.let { grid->
            //verificar files
            var positionToCheck:String?
            // mire files
            for (row in 0 until 6) {
                for (col in 0 until 3) {
                    if (grid[row][col].second == grid[row][col + 1].second &&
                        grid[row][col].second == grid[row][col + 2].second &&
                        grid[row][col].second == grid[row][col + 3].second) {
                        positionToCheck =  getCellColorMediumGrid(row,col)
                        if(positionToCheck != w){
                            return true
                        }
                    }
                }
            }

            // mire columnes
            for (col in 0 until 6) {
                for (row in 0 until 3) {
                    if (grid[row][col].second == grid[row + 1][col].second &&
                        grid[row][col].second == grid[row + 2][col].second &&
                        grid[row][col].second == grid[row + 3][col].second) {
                        positionToCheck =  getCellColorMediumGrid(row,col)
                        if(positionToCheck != w){
                            return true
                        }
                    }
                }
            }

            // diagonals (esquerra a dalt- dreta a baix )
            for (row in 0 until 3) {
                for (col in 0 until 3) {
                    if (grid[row][col].second == grid[row + 1][col + 1].second &&
                        grid[row][col].second == grid[row + 2][col + 2].second &&
                        grid[row][col].second == grid[row + 3][col + 3].second) {
                        positionToCheck =  getCellColorMediumGrid(row,col)
                        if(positionToCheck != w){
                            return true
                        }
                    }
                }
            }

            // diagonals (dreta dalt- esquerra baix) (right-top to left-bottom)
            for (row in 0 until 3) {
                for (col in 3 until 6) {
                    if (grid[row][col].second == grid[row + 1][col - 1].second &&
                        grid[row][col].second == grid[row + 2][col - 2].second &&
                        grid[row][col].second == grid[row + 3][col - 3].second) {
                        positionToCheck =  getCellColorMediumGrid(row,col)
                        if(positionToCheck != w){
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

    private fun comprobarsihemguanyatPetit():Boolean{
        val w:String = "W"
        //----------matriu petita----------
        val currentGrid3 = _littleGrid.value
        currentGrid3?.let { grid->
            //verificar files
            var positionToCheck:String?
            // mire files
            for (row in 0 until 5) {
                for (col in 0 until 2) {
                    if (grid[row][col].second == grid[row][col + 1].second &&
                        grid[row][col].second == grid[row][col + 2].second &&
                        grid[row][col].second == grid[row][col + 3].second) {
                        positionToCheck =  getCellColorLittleGrid(row,col)
                        if(positionToCheck != w){
                            return true
                        }
                    }
                }
            }

            // mire columnes
            for (col in 0 until 5) {
                for (row in 0 until 2) {
                    if (grid[row][col].second == grid[row + 1][col].second &&
                        grid[row][col].second == grid[row + 2][col].second &&
                        grid[row][col].second == grid[row + 3][col].second) {
                        positionToCheck =  getCellColorLittleGrid(row,col)
                        if(positionToCheck != w){
                            return true
                        }
                    }
                }
            }

            // diagonals (esquerra a dalt- dreta a baix )
            for (row in 0 until 2) {
                for (col in 0 until 2) {
                    if (grid[row][col].second == grid[row + 1][col + 1].second &&
                        grid[row][col].second == grid[row + 2][col + 2].second &&
                        grid[row][col].second == grid[row + 3][col + 3].second) {
                        positionToCheck =  getCellColorLittleGrid(row,col)
                        if(positionToCheck != w){
                            return true
                        }
                    }
                }
            }

            // diagonals (dreta dalt- esquerra baix) (right-top to left-bottom)
            for (row in 0 until 2) {
                for (col in 3 until 5) {
                    if (grid[row][col].second == grid[row + 1][col - 1].second &&
                        grid[row][col].second == grid[row + 2][col - 2].second &&
                        grid[row][col].second == grid[row + 3][col - 3].second) {
                        positionToCheck =  getCellColorLittleGrid(row,col)
                        if(positionToCheck != w){
                            return true
                        }
                    }
                }
            }
        }
        return false
    }
}