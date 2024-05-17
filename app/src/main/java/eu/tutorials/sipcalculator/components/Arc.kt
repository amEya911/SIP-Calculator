package eu.tutorials.sipcalculator.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.sipcalculator.addCommas
import eu.tutorials.sipcalculator.ui.theme.BarBlue
import eu.tutorials.sipcalculator.ui.theme.BarGray

@Composable
fun DisplayArc(
    displayTotalInvestment: Long,
    displayEstReturn: Long,
    displayTotalValue: Long
) {

    val curPercentage = (displayEstReturn.toFloat() / displayTotalValue.toFloat())
    val sweepAngle = 360f * curPercentage

    val remainingPercentage = 1f - curPercentage
    val remainingSweepAngle = 360f * remainingPercentage

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Canvas(
            modifier = Modifier
                .size(250.dp)
                .padding(16.dp)
        )
        {
            drawArc(
                color = BarBlue,
                startAngle = -90f, // Start angle at top
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(50.dp.toPx()),
            )

            drawArc(
                color = BarGray,
                startAngle = sweepAngle - 90f, // Start angle where the blue arc ends
                sweepAngle = remainingSweepAngle,
                useCenter = false,
                style = Stroke(50.dp.toPx()),
            )
        }

        Row(
            modifier = Modifier.padding(top = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Invested", color = BarGray, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Returns", color = BarBlue, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}