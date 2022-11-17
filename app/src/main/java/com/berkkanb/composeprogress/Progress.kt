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
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter

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
        indicators?.let { indicatorList ->
            drawIndicators(fillProgressBy,indicatorList,indicatorColor,indicatorWidth,canvasSize,stepImageVectorMultiplier,painter)
        }

    }
}

fun DrawScope.drawIndicators(
    filledProgressValue:Float,
    indicatorList: List<Float>,
    indicatorColor: Color,
    indicatorWidth: Float,
    canvasSize:Size,
    stepImageVectorMultiplier: Float = 0f,
    painter: VectorPainter? = null
) {
    indicatorList.forEachIndexed { index, indicator ->
        drawLine(
            color = indicatorColor,
            start = Offset(x = indicator * this.size.width, y = 0f),
            end = Offset(x = indicator * this.size.width, y = this.size.height),
            strokeWidth = indicatorWidth
        )
        painter?.let { vectorPainter ->
            with(vectorPainter) {
                val size = (canvasSize.height * stepImageVectorMultiplier)
                val previousLeft = if (index == 0) 0f else indicatorList[index - 1]
                val left = ((indicator + previousLeft) * canvasSize.width - size) / 2
                val top = (canvasSize.height - size) / 2
                if (indicator*canvasSize.width <= filledProgressValue*canvasSize.width){ //TODO LAST PIECE CHECKMARK
                    translate(left = left, top = top) {
                        draw(
                            Size(size, size)
                        )
                    }
                }
            }
        }
    }
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