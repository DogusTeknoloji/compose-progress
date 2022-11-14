package com.berkkanb.composeprogress

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource

@Composable
fun MyProgress(
    modifier: Modifier,
    progressColor: Color,
    progressBackgroundColor: Color,
    fillProgressBy: Float,
    indicators: List<Float>? = null,
    indicatorColor: Color = Color.White,
    indicatorWidth: Float = 5f,
    cornerRadius: CornerRadius = CornerRadius.Zero,
    animationDuration: Int,
    animationDelay:Int = 0
) {
    val targetPercentage = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = fillProgressBy) {
        targetPercentage.animateTo(
            targetValue = fillProgressBy,
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = LinearOutSlowInEasing,
                delayMillis = animationDelay
            )
        )
    }
    Canvas(modifier = modifier) {
        drawRoundRect(
            color = progressBackgroundColor,
            cornerRadius = cornerRadius
        )
        drawRoundRect(
            color = progressColor,
            size = Size(
                width = targetPercentage.value * this.size.width,
                height = this.size.height
            ),
            cornerRadius = cornerRadius
        )
        indicators?.forEach {
            drawLine(
                color = indicatorColor,
                start = Offset(x = it * this.size.width, y = 0f),
                end = Offset(x = it * this.size.width, y = this.size.height),
                strokeWidth = indicatorWidth
            )
        }
        /*
        val path = Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(
                        offset = Offset(0f, 0f),
                        size = Size(
                            width = targetPercentage.value * this@Canvas.size.width,
                            height = this@Canvas.size.height
                        )
                    ),
                    bottomLeft = cornerRadius,
                    topLeft = cornerRadius,
                )
            )
        }
        drawPath(path, color = progressColor)*/
    }
}

@Composable
fun MyProgress(
    modifier: Modifier,
    progressColor: Color,
    progressBackgroundColor: Color,
    fillProgressBy: Float,
    indicators: List<Float>? = null,
    indicatorColor: Color = Color.White,
    indicatorWidth: Float = 5f,
    cornerRadius: CornerRadius = CornerRadius.Zero
) {

    Canvas(modifier = modifier) {
        drawRoundRect(
            color = progressBackgroundColor,
            cornerRadius = cornerRadius
        )
        drawRoundRect(
            color = progressColor,
            size = Size(
                width = fillProgressBy * this.size.width,
                height = this.size.height
            ),
            cornerRadius = cornerRadius
        )
        indicators?.forEach {
            drawLine(
                color = indicatorColor,
                start = Offset(x = it * this.size.width, y = 0f),
                end = Offset(x = it * this.size.width, y = this.size.height),
                strokeWidth = indicatorWidth
            )
        }
        /*
        val path = Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(
                        offset = Offset(0f, 0f),
                        size = Size(
                            width = targetPercentage.value * this@Canvas.size.width,
                            height = this@Canvas.size.height
                        )
                    ),
                    bottomLeft = cornerRadius,
                    topLeft = cornerRadius,
                )
            )
        }
        drawPath(path, color = progressColor)*/
    }
}