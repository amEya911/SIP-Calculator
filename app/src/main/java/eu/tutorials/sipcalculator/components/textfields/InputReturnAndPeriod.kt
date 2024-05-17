package eu.tutorials.sipcalculator.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import eu.tutorials.sipcalculator.SIPViewModel
import eu.tutorials.sipcalculator.ui.theme.Blue
import eu.tutorials.sipcalculator.ui.theme.DarkGray
import eu.tutorials.sipcalculator.ui.theme.DarkRed
import eu.tutorials.sipcalculator.ui.theme.LightGray
import eu.tutorials.sipcalculator.ui.theme.LightRed
import eu.tutorials.sipcalculator.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayInputReturnAndPeriod(
    text: String,
    value: Number,
    onValueChange: (Number) -> Unit,
    icon: @Composable (() -> Unit),
    viewModel: SIPViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, start = 32.dp, end = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val background = if (text != "Inflation") {
            if (value.toInt() < 1) LightRed else White
        } else {
            White
        }
        val textColor = if (text != "Inflation") {
            if (value.toInt() < 1) DarkRed else Blue
        } else {
            Blue
        }

        if (text == "Inflation") {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = text)
                Spacer(modifier = Modifier.width(4.dp))
                IconButton(onClick = {
                    viewModel.showDialog = true
                }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = null, modifier = Modifier.size(15.dp))
                }
            }
        } else {
            Text(text = text)
        }

        Spacer(modifier = Modifier.weight(1f))
        TextField(
            value = TextFieldValue(
                text = value.toString(),
                selection = TextRange(value.toString().length, value.toString().length)
            ),
            onValueChange = if (value is Long) {
                { newValue ->
                    val intValue = newValue.text.toLongOrNull() ?: 0
                    onValueChange(intValue)
                }
            } else {
                { newValue ->
                    val doubleValue = newValue.text.toDoubleOrNull() ?: 0
                    onValueChange(doubleValue)
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),

            modifier = Modifier
                .width(100.dp)
                .height(50.dp)
                .align(Alignment.CenterVertically)
                .background(
                    background, RoundedCornerShape(43)
                ),
            shape = RoundedCornerShape(43),
            textStyle = MaterialTheme.typography.bodyLarge
                .copy(
                    color = textColor,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),
            singleLine = true,
            trailingIcon = icon,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}