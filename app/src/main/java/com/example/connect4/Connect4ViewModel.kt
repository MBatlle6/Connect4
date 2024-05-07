package com.example.connect4

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlin.random.Random


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

    private val _bigGrid = MutableLiveData<Array<Array<Pair<Color, String>>>>(Array(7) { Array(7) { Pair(Color.White, "W") } })
    val bigGrid: LiveData<Array<Array<Pair<Color,String>>>> = _bigGrid

    private val _mediumGrid = MutableLiveData<Array<Array<Pair<Color, String>>>>(Array(6) { Array(6) { Pair(Color.White, "W") } })
    val mediumGrid: LiveData<Array<Array<Pair<Color,String>>>> = _mediumGrid

    private val _littleGrid = MutableLiveData<Array<Array<Pair<Color, String>>>>(Array(5) { Array(5) { Pair(Color.White, "W") } })
    val littleGrid: LiveData<Array<Array<Pair<Color,String>>>> = _littleGrid

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

    private val _countdownTime = MutableLiveData<Int>(15)
    val countdownTime: LiveData<Int> = _countdownTime

    private val _gameFinished = MutableLiveData<Boolean>(false)
    val gameFinished: LiveData<Boolean> = _gameFinished

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
        _log.value = ""
        _alias.value = "Player1"
        _bigGrid.value = Array(7){ Array(7){Pair(Color.White, "W")} }
        _mediumGrid.value = Array(6){ Array(6){Pair(Color.White, "W")} }
        _littleGrid.value = Array(5){ Array(5){Pair(Color.White, "W")} }
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
    fun turnoJugador(i: Int, j:Int ,viewModel: Connect4ViewModel,grid:Int){
        //1 == BigGrid, 2 == MediumGrid, 3 == LittleGrid
        var a_retornar:String? = null
        if(grid == 1){//BigGrid
            if(ifposiciocorrecta(i,j,grid)){
                viewModel.setCellColorBigGrid(i, j, Color.Red,"R")
                turnoIA(viewModel,grid)
            }else{
                println("Position $i $j incorrecta")
            }
            a_retornar = getCellColorBigGrid(i,j)
            if (comprobarsihemguanyatGran()){
                println("Joc Finalitzat")
                victoria(MainActivity())
            }
        }
        if (grid == 2){//MediumGrid
            if(ifposiciocorrecta(i,j,grid)){
                viewModel.setCellColorMediumGrid(i, j, Color.Red,"R")
                turnoIA(viewModel,grid)
            }else{
                println("Position $i $j incorrecta")
            }
            a_retornar = getCellColorMediumGrid(i,j)
            if (comprobarsihemguanyatMitja()){
                println("Joc Finalitzat")
                victoria(MainActivity())
            }
        }
        if (grid == 3){//LittleGrid
            if(ifposiciocorrecta(i,j,grid)){
                viewModel.setCellColorLittleGrid(i, j, Color.Red,"R")
                turnoIA(viewModel,grid)
            }else{
                println("Position $i $j incorrecta")
            }
            a_retornar = getCellColorLittleGrid(i,j)
            if (comprobarsihemguanyatPetit()){
                println("Joc Finalitzat")
                victoria(MainActivity())
            }

        }
        println("color de la posicio $i,$j = \"$a_retornar\"" )

    }

    fun turnoIA(viewModel: Connect4ViewModel, grid: Int){
        if (grid == 1){
            var i = Random.nextInt(0,7)
            var j = Random.nextInt(0,7)
            if(ifposiciocorrecta(i,j,grid)){
                viewModel.setCellColorBigGrid(i,j,Color.Yellow,"Y")
            }else{
                turnoIA(viewModel,1)
            }
        }
        if (grid == 2){
            var i = Random.nextInt(0,6)
            var j = Random.nextInt(0,6)
            if(ifposiciocorrecta(i,j,grid)){
                viewModel.setCellColorMediumGrid(i,j,Color.Yellow,"Y")

            }else{
                turnoIA(viewModel,2)
            }
        }
        if (grid == 3){
            var i = Random.nextInt(0,5)
            var j = Random.nextInt(0,5)
            if(ifposiciocorrecta(i,j,grid)){
                viewModel.setCellColorLittleGrid(i,j,Color.Yellow,"Y")

            }else{
                turnoIA(viewModel,3)
            }
        }
    }
    fun comprobarsihemguanyatGran():Boolean{
        val w:String = "W"
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

    fun comprobarsihemguanyatMitja():Boolean{
        val w:String = "W"
        //----------matriu mitjana----------
        val currentGrid2 = _mediumGrid.value
        currentGrid2?.let { grid->
            //verificar files
            var positionToCheck:String?
            // mirem files
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

            // mirem columnes
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

    fun comprobarsihemguanyatPetit():Boolean{
        val w:String = "W"
        //----------matriu petita----------
        val currentGrid3 = _littleGrid.value
        currentGrid3?.let { grid->
            //verificar files
            var positionToCheck:String?
            // mirem files
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

            // mirem columnes
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
    fun victoria(activity: MainActivity){
        //addToLog("\n" + activity.getString(R.string.totalTime) + ": " + time.value.toString() + " s")
        //addToLog("\n" + activity.getString(R.string.timeFinished))
        setGameFinished(true)
        setLogScreen(true)
        setGameScreen(false)
    }

}