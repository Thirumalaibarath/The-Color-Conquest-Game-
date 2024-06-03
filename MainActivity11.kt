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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

var name : String = ""
var darkBluee = Color(0xFF3D4175)


var restarte : Boolean by mutableStateOf(false)

fun extractor(namePlayer:String)
{
    name = namePlayer
}

class MainActivity11 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

@Composable
fun Display(onDismiss:()->Unit) {
    AlertDialog(onDismissRequest = onDismiss , confirmButton = { /*TODO*/ },
        modifier = Modifier
            .height(450.dp)
            .background(color = darkBluee),
        title ={
            Row() {
                Box(modifier = Modifier
                    .size(width = 280.dp, height = 60.dp) // Set width and height here
                    .background(Color.White, shape = RoundedCornerShape(30.dp)),
                    contentAlignment = Alignment.Center)
                {
                    Text(text="$name",fontSize = 30.sp,fontWeight = FontWeight.Bold)
                }

            }
        },
        text = {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row( verticalAlignment = Alignment.CenterVertically)
                {
                    Box(modifier = Modifier.width(85.dp).background(color = lightBlue).height(4.dp))
                    Image(
                        painter = painterResource(id= R.drawable.first),
                        contentDescription = "Image",
                        modifier = Modifier.size(80.dp)
                    )
                    Box(modifier = Modifier.width(85.dp).background(color = lightBlue).height(4.dp))
                }
                Text(text="WINS!",fontSize = 40.sp,fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    com.example.myapplication.processor()
                },
                    modifier = Modifier.width(270.dp)
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = lightBlue
                    ) ){
                    Text(text = "Play Again",fontSize = 26.sp,fontWeight = FontWeight.Bold)

                }
                Spacer(modifier = Modifier.height(18.dp))
                Button(onClick = {
                    com.example.myapplication.returnHome()
                },
                    modifier = Modifier.width(270.dp)
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = lightRed))
                {
                    Text(text = "Home",fontSize = 26.sp,fontWeight = FontWeight.Bold)

                }

            }
        }

    )
}

