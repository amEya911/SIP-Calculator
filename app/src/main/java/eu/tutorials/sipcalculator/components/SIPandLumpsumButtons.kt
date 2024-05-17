package eu.tutorials.sipcalculator.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.sipcalculator.ui.theme.Blue
import eu.tutorials.sipcalculator.ui.theme.DarkGray
import eu.tutorials.sipcalculator.ui.theme.LightGray
import eu.tutorials.sipcalculator.ui.theme.Orange



@Composable
fun SIPAndLumpsumButtons(
    inputText: String,
    isSIP: Boolean,
    isLumpsum: Boolean,
    onClick: () -> Unit,
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            //.wrapContentWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            if (inputText == "SIP") {
                if (isSIP) Orange else DarkGray
            } else {
                if (isLumpsum) Orange else DarkGray
            }
        )
    ) {
        Text(
            text = inputText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = (
                    if (inputText == "SIP") {
                        if (isSIP) LightGray else Blue
                    } else {
                        if (isLumpsum) LightGray else Blue
                    }
                    )
        )
    }
}