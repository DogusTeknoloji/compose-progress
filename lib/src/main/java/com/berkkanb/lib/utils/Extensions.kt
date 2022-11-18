package com.berkkanb.lib.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.VectorPainter

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