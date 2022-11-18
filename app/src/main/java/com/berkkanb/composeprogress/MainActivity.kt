package com.berkkanb.composeprogress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.berkkanb.composeprogress.ui.theme.ComposeProgressTheme
import com.berkkanb.lib.BarProgress
import com.berkkanb.lib.StepProgress
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProgressTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var state by remember {
                        mutableStateOf(1f)
                    }
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            BarProgress(
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(200.dp)
                                    .clickable { state = Random.nextFloat() },
                                progressColor = Color(0xFF6633CC),
                                progressBackgroundColor = Color(0xFFE0E0E0),
                                indicators = listOf(0.1f, 0.4f, 0.6f, 0.8f),
                                fillProgressBy = state,
                                cornerRadius = CornerRadius(30f, 30f),
                                stepImageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_check_24),
                                animationDuration = 700,
                                animationDelay = 0
                            )
                            BarProgress(
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(200.dp),
                                progressColor = Color(0xFF6633CC),
                                progressBackgroundColor = Color(0xFFE0E0E0),
                                indicators = listOf(0.3f, 0.5f, 0.7f, 0.85f),
                                fillProgressBy = 0.85f,
                                cornerRadius = CornerRadius(30f, 30f),
                                animationDuration = 700,
                                animationDelay = 200
                            )
                            BarProgress(
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(200.dp),
                                progressColor = Color(0xFF6633CC),
                                progressBackgroundColor = Color(0xFFE0E0E0),
                                //indicators = listOf(0.3f,0.5f,0.7f,0.85f),
                                fillProgressBy = 0.95f,
                                cornerRadius = CornerRadius(30f, 30f)
                            )
                            StepProgress(
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(200.dp),
                                cornerRadius = CornerRadius(30f, 30f),
                                progressColor = Color(0xFF6633CC),
                                progressBackgroundColor = Color(0xFFE0E0E0),
                                currentStep = 6,
                                totalStep = 6,
                                stepImageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_check_24)
                            )
                        }
                    }
                }
            }
        }
    }
}
