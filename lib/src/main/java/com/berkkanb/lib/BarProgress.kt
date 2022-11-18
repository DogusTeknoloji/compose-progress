package com.berkkanb.lib

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.berkkanb.lib.utils.drawIndicators

@Composable
fun BarProgress(
    modifier: Modifier,
    progressColor: Color,
    progressBackgroundColor: Color,
    fillProgressBy: Float,
    indicators: List<Float>? = null,
    indicatorColor: Color = Color.White,
    indicatorWidth: Float = 5f,
    cornerRadius: CornerRadius = CornerRadius.Zero,
    stepImageVector: ImageVector? = null,
    stepImageVectorMultiplier: Float = 0.6f,
    animationDuration: Int,
    animationDelay: Int = 0
) {
    val targetPercentage = remember {
        Animatable(0f)
    }
    val painter = stepImageVector?.let { rememberVectorPainter(image = it) }
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
        val canvasSize = this.size
        val backgroundPath = Path()
        val backgroundRoundRect =
            RoundRect(0f, 0f, canvasSize.width, canvasSize.height, cornerRadius)

        backgroundPath.addRoundRect(roundRect = backgroundRoundRect)

        clipPath(backgroundPath) {
            drawPath(path = backgroundPath, color = progressBackgroundColor)
            drawRect(
                color = progressColor,
                size = Size(
                    width = targetPercentage.value * this.size.width,
                    height = this.size.height
                )
            )
        }

        indicators?.let { indicatorList ->
            drawIndicators(
                fillProgressBy,
                indicatorList,
                indicatorColor,
                indicatorWidth,
                canvasSize,
                stepImageVectorMultiplier,
                painter
            )
        }

    }
}

@Composable
fun BarProgress(
    modifier: Modifier,
    progressColor: Color,
    progressBackgroundColor: Color,
    fillProgressBy: Float,
    indicators: List<Float>? = null,
    indicatorColor: Color = Color.White,
    indicatorWidth: Float = 5f,
    cornerRadius: CornerRadius = CornerRadius.Zero,
    stepImageVector: ImageVector? = null,
    stepImageVectorMultiplier: Float = 0.6f
) {

    val painter = stepImageVector?.let { rememberVectorPainter(image = it) }

    Canvas(modifier = modifier) {
        val canvasSize = this.size
        val backgroundPath = Path()
        val backgroundRoundRect =
            RoundRect(0f, 0f, canvasSize.width, canvasSize.height, cornerRadius)

        backgroundPath.addRoundRect(roundRect = backgroundRoundRect)

        clipPath(backgroundPath) {
            drawPath(path = backgroundPath, color = progressBackgroundColor)
            drawRect(
                color = progressColor,
                size = Size(
                    width = fillProgressBy * this.size.width,
                    height = this.size.height
                )
            )
        }

        indicators?.let { indicatorList ->
            drawIndicators(
                fillProgressBy,
                indicatorList,
                indicatorColor,
                indicatorWidth,
                canvasSize,
                stepImageVectorMultiplier,
                painter
            )
        }

    }
}