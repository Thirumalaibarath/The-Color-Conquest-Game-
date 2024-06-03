package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

var row : Int by mutableIntStateOf(globalRow.toInt())
var column : Int by mutableIntStateOf(globalColumn.toInt())


class MainActivity9 : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            Box(modifier = Modifier.fillMaxSize())
            {
                GridFour(globalRow.toInt(),globalColumn.toInt())
                PlayerNameTwoDisplay()
                PlayerNameOneDisplay()
                PlayerOneDisplay()
                PlayerTwoDisplay()
                ExitButton()

                val infoList = mutableListOf(
                    playerNameOne,globalPlayerOne.toString(),"-",playerNameTwo,globalPlayerTwo.toString(),"-"
                )

                if(restart)
                {
                    Display(onDismiss = {state = true})
                    game = 0
                    colorChangerRed.clear()
                    colorChangerBlue.clear()
                    playerOneClicked.clear()
                    playerTwoClicked.clear()
                    score.fill(0)
                    globalPlayerOne = 0
                    globalPlayerTwo = 0
                    restart = false
                }

                if(returnhome)
                {
                    Display(onDismiss = {state = true})
                    game = 0
                    colorChangerRed.clear()
                    colorChangerBlue.clear()
                    playerOneClicked.clear()
                    playerTwoClicked.clear()
                    score.fill(0)
                    globalPlayerOne = 0
                    globalPlayerTwo = 0
                    returnhome = false
                }


                if (game >= 2 && (globalPlayerOne == 0 || globalPlayerTwo == 0))
                {
                    if (globalPlayerOne == 0) {
                        com.example.myapplication.extractor(playerNameOne)
                    } else {
                        com.example.myapplication.extractor(playerNameTwo)
                    }
                    Display(onDismiss = { state = false })
                    primeReciever.add(infoList)
                }



            }
        }
    }
}


@Composable
fun GridFour(rows: Int, columns: Int) {

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = if(game %2 != 0) lightBlue else lightRed) {

        val cellValues = List(rows) { rowIndex ->
            List(columns) { colIndex ->
                rowIndex * columns + colIndex
            }
        }

        val score = remember {
            mutableStateListOf<Int>().apply {
                repeat(rows * columns) { add(0) }
            }
        }


        val cellTexts = remember { mutableStateListOf<String>() }
        repeat(rows * columns) {
            cellTexts.add("")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (rowIndex in cellValues.indices) {
                Row {
                    for (colIndex in cellValues[rowIndex].indices) {
                        val cellValue = cellValues[rowIndex][colIndex]
                        val standard = remember{ mutableStateListOf<Int>()}
                        for(d in 0..rows*columns)
                        {
                            standard.add(d)
                        }
                        val principalValues = listOf(4,5,6,7,8)

                        val clickedValueAuto: MutableState<Int> = remember {
                            mutableIntStateOf(0)
                        }


                        Cell(
                            value = cellValue,
                            changerBlue = colorChangerBlue,
                            changerRed = colorChangerRed,
                            scoree = score,
                            onClick = { clickedValue ->

                                if (game == 0) {
                                    colorChangerRed.add(clickedValue)
                                    playerOneClicked.add(clickedValue)
                                    score[clickedValue] = 3

                                }
                                else if (clickedValue in playerOneClicked && score[clickedValue] == 3 && game % 2 == 0) {
                                    playerOneClicked.remove(clickedValue)
                                    colorChangerRed.remove(clickedValue)
                                    score[clickedValue] = 0

                                    val adderOne: MutableList<Int> = colorChanger(
                                        clicked = clickedValue,
                                        column = globalColumn.toInt()
                                    ).toMutableList()


                                    val workOne: List<List<Int>> = workCommon(
                                        clickedValue,
                                        game,
                                        adderOne,
                                        score,
                                        playerOneClicked,
                                        playerTwoClicked,
                                        colorChangerBlue,
                                        colorChangerRed
                                    )
                                    var count = 0

                                    for (n in workOne[0]) {
                                        score[count] = n
                                        count += 1
                                    }

                                    count = 0

                                    for (y in workOne[1]) {
                                        playerOneClicked[count] = y
                                        count += 1

                                    }
                                    count = 0
                                    for (z in workOne[2]) {
                                        playerTwoClicked[count] = z
                                        count += 1

                                    }
                                    count = 0
                                    for (u in workOne[3]) {
                                        colorChangerBlue[count] = u
                                        count += 1

                                    }
                                    count = 0
                                    for (v in workOne[4]) {
                                        colorChangerRed[count] = v
                                        count += 1
                                    }

                                }
                                else if(clickedValue in playerOneClicked && game%2 == 0 && score[clickedValue] < 3)
                                {
                                    score[clickedValue] += 1
                                }
                                else {
                                    game -= 1
                                    Log.d("GAME", "Rebound")
                                }
                                game += 1
                            }


                        )
                        var extra = 0

                        while(score.any { it in principalValues })
                        {
                            while(gridChecker(score))
                            {
                                val indeXe  = valueReturner(score,4)

                                var countyo = 0

                                val adderExe: MutableList<Int> = colorChanger(
                                    clicked = indeXe,
                                    column = globalColumn.toInt()
                                ).toMutableList()

                                for (cu in fourAdder(indeXe,adderExe,score))
                                {
                                    score[countyo] = cu
                                    countyo+=1
                                }


                                if(indeXe in colorChangerRed)
                                {
                                    for(xui in adderExe)
                                    {
                                        if(xui !in colorChangerRed)
                                        {
                                            colorChangerRed.add(xui)
                                            colorChangerBlue.remove(xui)
                                            playerTwoClicked.remove(xui)
                                            playerOneClicked.add(xui)
                                        }


                                    }

                                }
                                else
                                {
                                    for(vuu in adderExe)
                                    {
                                        if(vuu !in colorChangerBlue)
                                        {
                                            colorChangerBlue.add(vuu)
                                            colorChangerRed.remove(vuu)
                                            playerOneClicked.remove(vuu)
                                            playerTwoClicked.add(vuu)
                                        }
                                    }

                                }

                                if(adderExe.size == 4)
                                {
                                    if(indeXe in colorChangerRed)
                                    {
                                        colorChangerRed.remove(indeXe)
                                    }
                                    else
                                    {
                                        colorChangerBlue.remove(indeXe)
                                    }
                                }

                            }
                            while(moreThanFour(score))
                            {
                                val te = valueReturnerTwo(score)
                                val nume = valueReturner(score,te)

                                var whoe = 0
                                if(nume in colorChangerRed)
                                {
                                    whoe = 0
                                }
                                else
                                {
                                    whoe = 1
                                }


                                extra = score[nume] - 4
                                score[nume] = 4

                                val indEXE  = valueReturner(score,4)

                                var countyy = 0

                                val adderEXE: MutableList<Int> = colorChanger(
                                    clicked = indEXE,
                                    column = globalColumn.toInt()
                                ).toMutableList()

                                for (qu in fourAdder(indEXE,adderEXE,score))
                                {
                                    score[countyy] = qu
                                    countyy+=1
                                }


                                if(indEXE in colorChangerRed)
                                {
                                    for(auo in adderEXE)
                                    {
                                        if(auo !in colorChangerRed)
                                        {
                                            colorChangerRed.add(auo)
                                            colorChangerBlue.remove(auo)
                                            playerTwoClicked.remove(auo)
                                            playerOneClicked.add(auo)
                                        }

                                    }

                                }
                                else
                                {
                                    for(ouo in adderEXE)
                                    {
                                        if(ouo !in colorChangerBlue)
                                        {
                                            colorChangerBlue.add(ouo)
                                            colorChangerRed.remove(ouo)
                                            playerOneClicked.remove(ouo)
                                            playerTwoClicked.add(ouo)
                                        }
                                    }

                                }

                                score[indEXE] = extra

                            }

                        }

                        val handler = android.os.Handler()

                        val postGame = Runnable {
                            if (game%2 == 1)
                            {
                                if(game%2 ==1 && game>2)
                                {
                                    clickedValueAuto.value = compRand(playerTwoClicked)
                                }
                                if (game ==1) {
                                    clickedValueAuto.value = rand()
                                }
                                if (game == 1 && clickedValueAuto.value !in playerOneClicked) {
                                    colorChangerBlue.add(clickedValueAuto.value)
                                    playerTwoClicked.add(clickedValueAuto.value)
                                    score[clickedValueAuto.value] = 3
                                }
                                else if(clickedValueAuto.value in playerTwoClicked && score[clickedValueAuto.value] == 3  && game%2 == 1)
                                {

                                    playerTwoClicked.remove(clickedValueAuto.value)
                                    colorChangerBlue.remove(clickedValueAuto.value)
                                    score[clickedValueAuto.value] = 0


                                    val adderTwo : MutableList<Int> = colorChanger(
                                        clicked =clickedValueAuto.value,
                                        column = globalColumn.toInt()
                                    ).toMutableList()

                                    val workTwo: List<List<Int>> = workCommon(
                                        clickedValueAuto.value,
                                        game,
                                        adderTwo,
                                        score,
                                        playerOneClicked,
                                        playerTwoClicked,
                                        colorChangerBlue,
                                        colorChangerRed
                                    )
                                    var countTwo = 0

                                    for(n in workTwo[0])
                                    {
                                        score[countTwo] = n
                                        countTwo +=1
                                    }

                                    countTwo = 0

                                    for(y in workTwo[1])
                                    {
                                        playerOneClicked[countTwo] = y
                                        countTwo += 1

                                    }
                                    countTwo = 0
                                    for(z in workTwo[2])
                                    {
                                        playerTwoClicked[countTwo] = z
                                        countTwo += 1

                                    }
                                    countTwo = 0
                                    for(u in workTwo[3])
                                    {
                                        colorChangerBlue[countTwo] = u
                                        countTwo += 1

                                    }
                                    countTwo = 0
                                    for(v in workTwo[4])
                                    {
                                        colorChangerRed[countTwo] = v
                                        countTwo += 1

                                    }
                                }
                                else if (clickedValueAuto.value in playerTwoClicked && game % 2 == 1 && score[clickedValueAuto.value] < 3) {
                                    score[clickedValueAuto.value] += 1
                                }


                                game += 1

                            }
                        }

                        if(globalPlayerTwo != 0)
                        {
                            handler.postDelayed(postGame, 2000)
                        }


                       val postFilter = Runnable{
                           var extraTwo = 0

                           while(score.any { it in principalValues })
                           {
                               while(gridChecker(score))
                               {
                                   val indeXy  = valueReturner(score,4)

                                   var county = 0

                                   val adderExy: MutableList<Int> = colorChanger(
                                       clicked = indeXy,
                                       column = globalColumn.toInt()
                                   ).toMutableList()

                                   for (cu in fourAdder(indeXy,adderExy,score))
                                   {
                                       score[county] = cu
                                       county+=1
                                   }


                                   if(indeXy in colorChangerRed)
                                   {
                                       for(xuyz in adderExy)
                                       {
                                           if(xuyz !in colorChangerRed)
                                           {
                                               colorChangerRed.add(xuyz)
                                               colorChangerBlue.remove(xuyz)
                                               playerTwoClicked.remove(xuyz)
                                               playerOneClicked.add(xuyz)
                                           }


                                       }

                                   }
                                   else
                                   {
                                       for(vuyz in adderExy)
                                       {
                                           if(vuyz !in colorChangerBlue)
                                           {
                                               colorChangerBlue.add(vuyz)
                                               colorChangerRed.remove(vuyz)
                                               playerOneClicked.remove(vuyz)
                                               playerTwoClicked.add(vuyz)
                                           }
                                       }

                                   }

                                   if(adderExy.size == 4)
                                   {
                                       if(indeXy in colorChangerRed)
                                       {
                                           colorChangerRed.remove(indeXy)
                                           playerOneClicked.remove(indeXy)
                                       }
                                       else
                                       {
                                           colorChangerBlue.remove(indeXy)
                                           playerTwoClicked.remove(indeXy)
                                       }
                                   }

                               }
                               while(moreThanFour(score))
                               {
                                   val ty = valueReturnerTwo(score)
                                   val numy = valueReturner(score,ty)


                                   extraTwo = score[numy] - 4
                                   score[numy] = 4

                                   val indEXz  = valueReturner(score,4)

                                   var countyTwo = 0

                                   val adderEXz: MutableList<Int> = colorChanger(
                                       clicked = indEXz,
                                       column = globalColumn.toInt()
                                   ).toMutableList()

                                   for (quz in fourAdder(indEXz,adderEXz,score))
                                   {
                                       score[countyTwo] = quz
                                       countyTwo+=1
                                   }


                                   if(indEXz in colorChangerRed)
                                   {
                                       for(auz in adderEXz)
                                       {
                                           if(auz !in colorChangerRed)
                                           {
                                               colorChangerRed.add(auz)
                                               colorChangerBlue.remove(auz)
                                               playerTwoClicked.remove(auz)
                                               playerOneClicked.add(auz)
                                           }

                                       }

                                   }
                                   else
                                   {
                                       for(ouz in adderEXz)
                                       {
                                           if(ouz !in colorChangerBlue)
                                           {
                                               colorChangerBlue.add(ouz)
                                               colorChangerRed.remove(ouz)
                                               playerOneClicked.remove(ouz)
                                               playerTwoClicked.add(ouz)
                                           }
                                       }

                                   }

                                   score[indEXz] = extraTwo

                               }

                           }
                       }
                        handler.postDelayed(postFilter, 2000)


                        globalPlayerOne = scoreDisplayer(score,colorChangerRed)
                        globalPlayerTwo = scoreDisplayer(score,colorChangerBlue)

                    }
                }
            }
        }
    }
}

fun rand(): Int {
    val re = (0..<(row* column)).random()
    return re
}
fun compRand(gugu : List<Int>):Int
{
    val num = gugu.size
    val ze = (0..<num).random()
    return(gugu[ze])
}

