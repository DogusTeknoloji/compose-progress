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
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
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


//TODO Add animatable step progress
@Composable
fun MyStepProgress(
    modifier: Modifier,
    progressColor: Color,
    progressBackgroundColor: Color,
    currentStep: Int,
    totalStep: Int,
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

        val stepWidth = (canvasSize.width - (totalStep.minus(1) * indicatorWidth)) / totalStep

        clipPath(backgroundPath) {
            drawPath(path = backgroundPath, color = progressBackgroundColor)
            drawSteps(
                currentStep,
                totalStep,
                indicatorWidth,
                progressBackgroundColor,
                progressColor,
                stepWidth,
                canvasSize,
                indicatorColor,
                stepImageVectorMultiplier,
                painter
            )
        }

    }
}

fun DrawScope.drawSteps(
    currentStep: Int,
    totalStep: Int,
    indicatorWidth: Float,
    progressBackgroundColor: Color,
    progressColor: Color,
    stepWidth: Float,
    canvasSize: Size,
    indicatorColor: Color,
    stepImageVectorMultiplier: Float = 0.6f,
    painter: VectorPainter? = null
) {
    for (i in 0..totalStep.minus(1)) {
        val margin = if (i == 0) 0f else indicatorWidth
        drawRect(
            color = if (i < currentStep) progressColor else progressBackgroundColor,
            topLeft = Offset((i * stepWidth) + (margin * i), 0f),
            size = Size(stepWidth, canvasSize.height)
        )
        if (i > 0) {
            drawLine(
                color = indicatorColor,
                start = Offset(x = i * stepWidth + i.minus(1) * margin, y = 0f),
                end = Offset(x = i * stepWidth + i.minus(1) * margin, y = canvasSize.height),
                strokeWidth = indicatorWidth
            )
        }
        painter?.let { vectorPainter ->
            with(vectorPainter) {
                val size = (canvasSize.height * stepImageVectorMultiplier)
                val previousLeft = if (i == 0) 0f else i * (stepWidth + margin)
                val left = previousLeft + (stepWidth - size) / 2
                val top = (canvasSize.height - size) / 2
                if (i < currentStep) {
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

fun DrawScope.drawIndicators(
    filledProgressValue: Float,
    indicatorList: List<Float>,
    indicatorColor: Color,
    indicatorWidth: Float,
    canvasSize: Size,
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
                if (indicator * canvasSize.width <= filledProgressValue * canvasSize.width) {
                    translate(left = left, top = top) {
                        draw(
                            Size(size, size)
                        )
                    }
                    if (filledProgressValue == 1f) {
                        val lastLeft = ((indicatorList.last() + 1f) * canvasSize.width - size) / 2
                        translate(left = lastLeft, top = top) {
                            draw(
                                Size(size, size)
                            )
                        }
                    }
                }
            }
        }
    }
}