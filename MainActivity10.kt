package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme


var primeReciever: MutableList<MutableList<String>> by mutableStateOf(mutableListOf())



class MainActivity10 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val gradientColorlist = listOf(
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


            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = gradientColorlist
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(70.dp))
                HistoryBoard()

                ExitButton()

                provider()

            }
        }
    }


    @Composable
    fun ExitButton()
    {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red)
            .drawBehind {
                val dr = Path().apply {
                    moveTo(980f, 100f)
                    val rec = Rect(910f, 30f, 1050f, 170f)
                    arcTo(
                        rect = rec,
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 359.999f,
                        forceMoveTo = false
                    )
                }

                drawPath(
                    path = dr,
                    color = Color.White
                )


            }
        )
        {
            Text(text = "X" , modifier = Modifier.offset(348.dp,15.dp), fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Color.Gray)
        }
    }


    @Composable
    fun provider() {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(25.dp))

            primeReciever.forEach { mutStringState ->
                InfoBox(mutStringState)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

    @Composable
    fun InfoBox(mutStringStates: MutableList<String>) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = orange_3, shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center)

        {
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = "#1", fontSize = 40.sp,fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(7.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InfoCard(
                        texts = listOf(mutStringStates[0], mutStringStates[1],mutStringStates[2]),
                        textColor = lightRed,
                        backgroundColor = lightBlue
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    InfoCard(
                        texts = listOf(mutStringStates[3], mutStringStates[4],mutStringStates[5]),
                        textColor = lightBlue,
                        backgroundColor = lightRed
                    )
                }
            }
        }
    }


    @Composable
    fun InfoCard(texts: List<String>, textColor: Color, backgroundColor: Color) {
        Box( modifier = Modifier
            .width(160.dp)
            .height(250.dp) // Increased height to accommodate the image and text
            .padding(10.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center)
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp), // Added padding to the bottom for the text
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(15.dp))

                Image(
                    painter = painterResource(id = R.drawable.gold),
                    contentDescription = "My Image",
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.height(15.dp))

                texts.forEach { text ->
                    Text(
                        text = text.uppercase(),
                        fontSize = 25.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

@Composable
fun HistoryBoard() {
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
            text = "PLAYER HISTORY",
            modifier = Modifier.offset(64.dp, 125.dp),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
}


