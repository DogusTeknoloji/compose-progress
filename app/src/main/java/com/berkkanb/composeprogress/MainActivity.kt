package com.berkkanb.composeprogress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.berkkanb.composeprogress.ui.theme.ComposeProgressTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProgressTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column() {
                            MyProgress(
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(200.dp),
                                progressColor = Color.Magenta,
                                progressBackgroundColor = Color.LightGray,
                                indicators = listOf(0.1f,0.9f),
                                fillProgressBy = 0.95f,
                                cornerRadius = CornerRadius(100f,100f)
                            )
                        }
                    }
                }
            }
        }
    }
}
