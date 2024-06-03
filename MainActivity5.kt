package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

var stateLoad : Boolean by mutableStateOf(true)
var globalCounter: Float by mutableFloatStateOf(105F)


class MainActivity5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val gradientColorlistThree = listOf(
                orange_1,
                orange_2,
                orange_3,
                orange_4,
                orange_5,
                orange_6,
                orange_7,
                orange_8,
                orange_9
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = gradientColorlistThree
                        )
                    )
            )
            {
                GameStartBoard()
                GameTip()
                Button(
                    onClick = {

                        if(globalPlayer != "2")
                        {
                            stateLoad = false
                            val intentGameOne = Intent(this@MainActivity5,MainActivity6::class.java)
                            startActivity(intentGameOne)

                        }
                        else
                        {
                            stateLoad = false
                            Intent(applicationContext, MainActivity7::class.java).also {
                                startActivity(it)
                            }
                        }

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier.offset((-15).dp, (30).dp)
                )
                {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow icon",
                        modifier = Modifier.size(48.dp),
                        tint = darkBlue
                    )

                }

                LaunchedEffect(stateLoad) {
                    if(stateLoad)
                    {
                        while (globalCounter < 990F) {
                            delay(1000L)
                            if(globalCounter <900F)
                            {
                                globalCounter += 70F
                            }
                            else
                            {
                                globalCounter += 10F
                            }

                        }
                    }
                    else
                    {
                        if(globalCounter >980F)
                        {
                            globalCounter = 980F
                        }
                        else
                        {
                            globalCounter = 105F
                        }

                    }
                }
                if(globalCounter > 980F)
                {
                    stateLoad = false
                    if(globalMode == "NORMAL MODE")
                    {
                        if(globalPlayer == "2")
                        {
                            Intent(applicationContext, MainActivity6::class.java).also {
                                startActivity(it)
                            }
                        }
                        else if(globalPlayer == "3")
                        {
                            Intent(applicationContext, MainActivity7::class.java).also {
                                startActivity(it)
                            }
                        }

                    }
                    else if(globalMode == "TIMER MODE")
                    {
                        Intent(applicationContext, MainActivity8::class.java).also {
                            startActivity(it)
                        }
                    }
                    else if(globalMode == "GOD MODE")
                    {

                        Intent(applicationContext, MainActivity9::class.java).also {
                            startActivity(it)
                        }

                    }

                }
                GameLoad(z = globalCounter)
            }
        }
    }
}
@Composable
fun GameLoad(z:Float)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind()
            {
                val pathThree = Path().apply {
                    moveTo(100f, 1900f)
                    lineTo(1000f, 1900f)
                    lineTo(1000f, 2000f)
                    lineTo(100f, 2000f)
                }
                val pathFour = Path().apply {
                    moveTo(110f, 1910f)
                    lineTo(z, 1910f)
                    lineTo(z, 1990f)
                    lineTo(110f, 1990f)

                }

                drawPath(
                    path = pathThree,
                    color = androidx.compose.ui.graphics.Color.Black
                )
                drawPath(
                    path = pathFour,
                    color =  androidx.compose.ui.graphics.Color.Red
                )


            }
    )
}

@Composable
fun GameTip()
{
    Box(modifier = Modifier.fillMaxSize()
        .drawBehind {
            val pathSeven = Path().apply {
                moveTo(150f,600f)
                lineTo(940f,600f)
                lineTo(940f,1800f)
                lineTo(150f,1800f)

                moveTo(150f,650f)
                val cirOne = Rect(100f,600f,200f,700f)
                arcTo(
                    rect = cirOne,
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )

                moveTo(940f,650f)
                val cirTwo = Rect(890f,600f,990f,700f)
                arcTo(
                    rect = cirTwo,
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                moveTo(150f,1750f)
                val cirThree = Rect(100f,1700f,200f,1800f)
                arcTo(
                    rect = cirThree,
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                moveTo(940f,1750f)
                val cirFour = Rect(890f,1700f,990f,1800f)
                arcTo(
                    rect = cirFour,
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )

                moveTo(150f,650f)
                lineTo(100f,650f)
                lineTo(100f,1750f)
                lineTo(150f,1750f)

                moveTo(940f,650f)
                lineTo(990f,650f)
                lineTo(990f,1750f)
                lineTo(940f,1750f)

            }
            drawPath(
                path = pathSeven,
                color = darkBlue
            )
        })

}


@Composable
fun GameStartBoard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                val xo = Path().apply {
                    moveTo(200f, 300f)
                    lineTo(900f, 300f)
                    lineTo(900f, 500f)
                    lineTo(200f, 500f)
                    moveTo(150f, 400f)
                    val re = Rect(120f, 370f, 180f, 430f)
                    arcTo(
                        rect = re,
                        startAngleDegrees = 120f,
                        sweepAngleDegrees = 120f,
                        forceMoveTo = false
                    )
                    moveTo(135f, 374f)
                    lineTo(200f, 300f)
                    lineTo(200f, 500f)
                    lineTo(135f, 425.9f)


                    moveTo(950f, 400f)
                    val ree = Rect(920f, 370f, 980f, 430f)
                    arcTo(
                        rect = ree,
                        startAngleDegrees = 60f,
                        sweepAngleDegrees = -120f,
                        forceMoveTo = false
                    )
                    moveTo(965f, 374f)
                    lineTo(900f, 300f)
                    lineTo(900f, 500f)
                    lineTo(965f, 425.9f)

                }

                val kun = Path().apply {
                    moveTo(200f, 310f)
                    lineTo(900f, 310f)
                    lineTo(900f, 490f)
                    lineTo(200f, 490f)
                    moveTo(150f, 400f)
                    val re = Rect(130f, 380f, 170f, 420f)
                    arcTo(
                        rect = re,
                        startAngleDegrees = 120f,
                        sweepAngleDegrees = 120f,
                        forceMoveTo = false
                    )
                    moveTo(140f, 382.6f)
                    lineTo(200f, 310f)
                    lineTo(200f, 490f)
                    lineTo(135f, 417.3f)
                    moveTo(950f, 400f)
                    val ree = Rect(930f, 380f, 970f, 420f)
                    arcTo(
                        rect = ree,
                        startAngleDegrees = 60f,
                        sweepAngleDegrees = -120f,
                        forceMoveTo = false
                    )
                    moveTo(960f, 382.6f)
                    lineTo(900f, 310f)
                    lineTo(900f, 490f)
                    lineTo(960f, 417.3f)
                }

                drawPath(
                    path = xo,
                    color = lightBrownTwo
                )
                drawPath(
                    path = kun,
                    color = lightBrown
                )
            }
    )
    {
        Text(
            text = "GAME TIP",
            modifier = Modifier.offset(140.dp, 125.dp),
            fontSize = 26.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
    }
}