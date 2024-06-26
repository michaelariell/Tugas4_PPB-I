package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.diceroller.ui.theme.DiceRollerTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.animation.core.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    DiceRollerWithImageAndButton(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(align = Alignment.Center)
    )
}

@Composable
fun DiceRollerWithImageAndButton(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    var rotateDegrees by remember { mutableStateOf(0f) }

    // This will animate the rotation when the 'rotateDegrees' value changes
    val rotationAnimation: Float by animateFloatAsState(
        targetValue = rotateDegrees,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )

    val imageOfDice = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = imageOfDice),
            contentDescription = "Dice image",
            modifier = Modifier.graphicsLayer(
                rotationZ = rotationAnimation
            )
        )
        Button(onClick = {
            // Update the result and start a new rotation animation
            result = (1..6).random()
            rotateDegrees += 360f // This adds a full rotation; adjust as needed
        }) {
            Text(text = "Roll")
        }
    }
}