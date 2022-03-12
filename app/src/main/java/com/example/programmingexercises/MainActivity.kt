package com.example.programmingexercises

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.programmingexercises.ui.theme.ProgrammingExercisesTheme
import java.util.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgrammingExercisesTheme {
                Screen(this)
            }
        }
    }


}
@Composable
fun Screen(context: Context){
    Surface(color = MaterialTheme.colors.background) {
        Image(
            modifier = Modifier,
            painter = painterResource(R.drawable.background3),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
            TopAppBar {
                Text("Text to speech application")
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text="Hello")
                Spacer(modifier = Modifier.size(20.dp))
                Button(
                    onClick = {context.startActivity(Intent(context,com.example.programmingexercises.TextToSpeech::class.java))},
                    shape = MaterialTheme.shapes.small
                ){
                    Text(text = "Go to welcome screen")
                }

            }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProgrammingExercisesTheme {
        Greeting("Android")
    }
}