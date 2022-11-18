package com.berkkanb.lib

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.berkkanb.lib.utils.drawSteps

//TODO Add animatable step progress
@Composable
fun StepProgress(
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
