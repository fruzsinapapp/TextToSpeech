package com.example.programmingexercises

import android.app.AlertDialog
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.programmingexercises.ui.theme.ProgrammingExercisesTheme

import java.util.*

class TextToSpeech : ComponentActivity(), TextToSpeech.OnInitListener {
    lateinit var tts : TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgrammingExercisesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Screen2(tts = tts)
                }
            }
        }

        tts = TextToSpeech( this, this)

        
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.US)
        } else {
            startActivity(Intent(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //release the background service now that we are done
        tts.shutdown()
    }


}
@Composable
fun Screen2(tts: TextToSpeech){
    val name = rememberSaveable{ mutableStateOf("")}
    Image(
        modifier = Modifier,
        painter = painterResource(R.drawable.background3),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        TopAppBar {
            Text("Welcome page")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Type in your name so I can welcome you!")
            Spacer(modifier = Modifier.size(20.dp))
            OutlinedTextField(
                value = name.value,
                onValueChange = {name.value = it},
            modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.size(20.dp))

            Button(
                onClick = {
                    tts.setPitch(2.0f)
                    tts.speak("Hello, welcome ${name.value}!",TextToSpeech.QUEUE_FLUSH,null,"")
                }
            ){
                Text(text = "Higher pitch")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(
                onClick = {
                    tts.setPitch(0.1f)
                    tts.speak("Hello, welcome ${name.value}!",TextToSpeech.QUEUE_FLUSH,null,"")
                }
            ){
                Text(text = "Lower pitch")
            }
        }
    }

}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
    val context = LocalContext.current
    val alertDialog = AlertDialog.Builder(context).create()
    alertDialog.setTitle("Title")
    alertDialog.setMessage("This is VERY important. Do you agree?")

    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes!") { dialog, which ->
        Toast.makeText(context, "You said yes!", Toast.LENGTH_SHORT).show()
    }

    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"No") { _, _ ->
        Toast.makeText(context, "Nooooo", Toast.LENGTH_SHORT).show()
    }

    alertDialog.show()

}

