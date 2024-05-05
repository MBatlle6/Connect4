package com.example.connect4.widgets



/*
fun comprobarsihemguanyat( grid: Int,text: String):Boolean{
    // Verifica filas
    var gridNum = 0
    if (grid == 1){
        gridNum = 7
    }else if (grid == 2){
        gridNum = 6
    }else if(grid == 3){
        gridNum = 5
    }
    val w:String = "W"
    val currentGrid = _bigGrid.value
    currentGrid?.let { grid->
        //verificar files

        for (row in 0 until gridNum) {
            for (col in 0 until gridNum-3) {
                if (grid[row][col].second == grid[row][col + 1].second &&
                    grid[row][col].second == grid[row][col + 2].second &&
                    grid[row][col].second == grid[row][col + 3].second )  {
                    var positionToCheck:String?=  getCellColorBigGrid(row,col)
                    if(positionToCheck != w){
                        return true
                    }

                }
            }
        }

        //verifica columnes
        for (col in 0 until gridNum) {
            for (row in 0 until gridNum-3) {
                if (grid[row][col].second == grid[row][col + 1].second &&
                    grid[row][col].second == grid[row][col + 2].second &&
                    grid[row][col].second == grid[row][col + 3].second &&
                    (grid[row][col].second == text || grid[row][col].second == text)) {
                    var positionToCheck:String?=  getCellColorBigGrid(row,col)
                    if(positionToCheck != w){
                        return true
                    }
                }
            }
        }
        //verificar diagonals descendents (esquerra a dreta)
        for (row in 0 until gridNum-3) {
            for (col in 0 until gridNum-3) {
                if (grid[row][col].second == grid[row + 1][col + 1].second &&
                    grid[row][col].second == grid[row + 2][col + 2].second &&
                    grid[row][col].second == grid[row + 3][col + 3].second &&
                    (grid[row][col].second == text || grid[row][col].second == text)) {
                    var positionToCheck:String?=  getCellColorBigGrid(row,col)
                    if(positionToCheck != w){
                        return true
                    }
                }
            }
        }
        //verificar diagonals ascendets (dreta a esquerra)
        for (row in gridNum-4 until gridNum) {
            for (col in 0 until gridNum-3) {
                if (grid[row][col].second == grid[row - 1][col + 1].second &&
                    grid[row][col].second == grid[row - 2][col + 2].second &&
                    grid[row][col].second == grid[row - 3][col + 3].second &&
                    (grid[row][col].second == text || grid[row][col].second == text)) {
                    var positionToCheck:String?=  getCellColorBigGrid(row,col)
                    if(positionToCheck != w){
                        return true
                    }
                }
            }
        }
    }
    return false
}*/

/*fun comprobarsihemguanyatMitja():Boolean{
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
}*/

/*    fun comprobarsihemguanyatPetit():Boolean{
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
    }*/