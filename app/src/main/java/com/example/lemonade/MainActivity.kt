package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Lemonade()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLemonadeApp() {
    LemonadeTheme {
        Lemonade()
    }
}

@Composable
fun Lemonade() {
    var step by remember { mutableStateOf(1) }
    var tapsRemaining by remember { mutableStateOf(0) }

    val imageRes = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    val textRes = when (step) {
        1 -> R.string.tap_tree
        2 -> R.string.tap_lemon
        3 -> R.string.tap_glass
        4 -> R.string.tap_empty
        else -> R.string.tap_tree
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center) // Centers the content
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Centers children horizontally
            modifier = Modifier.align(Alignment.Center) // Centers the column inside the Box
        ) {
            // Box to give a peach background color behind the image
            Box(
                modifier = Modifier
                    .size(260.dp) // Slightly larger than image for padding effect
                    .background(Color(0xFFFFDAB9)) // Peach background color
                    .padding(10.dp) // Padding inside the box for spacing
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = stringResource(id = textRes),
                    modifier = Modifier
                        .size(250.dp)
                        .clickable {
                            when (step) {
                                1 -> {
                                    tapsRemaining = Random.nextInt(2, 5)
                                    step = 2
                                }
                                2 -> {
                                    if (tapsRemaining > 1) {
                                        tapsRemaining--
                                    } else {
                                        step = 3
                                    }
                                }
                                3 -> step = 4
                                4 -> step = 1
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = stringResource(id = textRes),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center, // Centers text inside the Text component
                modifier = Modifier.fillMaxWidth() // Ensures text uses full width for centering
            )
        }
    }
}
