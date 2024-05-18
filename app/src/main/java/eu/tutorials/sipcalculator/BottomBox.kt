package eu.tutorials.sipcalculator

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.sipcalculator.components.DisplayArc
import eu.tutorials.sipcalculator.ui.theme.BoxBackGround
import eu.tutorials.sipcalculator.ui.theme.LightGreen
import java.text.DecimalFormat

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomBox(
    displayTotalInvestment: Long,
    displayEstReturn: Long,
    displayTotalValue: Long,
    displayTotalValueAfterInflation: Long,
    inputInflation: Int
) {

    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxHeight()
            .fillMaxWidth()
            .background(BoxBackGround, RoundedCornerShape(32.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 32.dp)
        ) {
            Column(
                modifier = Modifier.padding(end = 16.dp, bottom = 16.dp, start = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val rows = if (inputInflation != 0) {
                    listOf(
                        "Invested" to displayTotalInvestment,
                        "Returns (Without Inflation)" to displayEstReturn,
                        "Total Value Before Inflation" to displayTotalValue,
                        "Total Value After Inflation" to displayTotalValueAfterInflation
                    )
                } else {
                    listOf(
                        "Invested" to displayTotalInvestment,
                        "Returns" to displayEstReturn,
                        "Total Value" to displayTotalValue
                    )
                }

                rows.forEachIndexed { index, (title, value) ->
                    val formattedValue = if (value > 1000) {
                        " (${formatNumber(value)})"
                    } else {
                        ""
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = title, fontSize = 15.sp)
                        Text(
                            modifier = Modifier.padding(start = 8.dp).basicMarquee(),
                            text = if (index == 1) "+₹${value.addCommas()}$formattedValue" else "₹${value.addCommas()}$formattedValue",
                            fontSize = 15.sp,
                            color = if (index == 1) LightGreen else Color.Black,
                            maxLines = 1
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
                    Spacer(modifier = Modifier.height(16.dp))
            }
            DisplayArc(
                displayTotalInvestment,
                displayEstReturn,
                displayTotalValue
            )
        }

    }

}

fun Long.addCommas(): String {
    return String.format("%,d", this)
}

@Composable
fun formatNumber(number: Long): String {
    return when {
        number >= 1_000_000_000_000L -> "${String.format("%.2f", number.toDouble() / 1_000_000_000_000)} Trillion"
        number >= 1_000_000_000L -> "${String.format("%.2f", number.toDouble() / 1_000_000_000)} Billion"
        number >= 1_000_000L -> "${String.format("%.2f", number.toDouble() / 1_000_000)} Million"
        number >= 1_000L -> "${String.format("%.2f", number.toDouble() / 1_000)}K"
        else -> DecimalFormat("#,###").format(number)
    }
}


