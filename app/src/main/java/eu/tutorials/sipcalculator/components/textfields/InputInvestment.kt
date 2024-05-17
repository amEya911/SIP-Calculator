package eu.tutorials.sipcalculator.components.textfields

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.sipcalculator.R
import eu.tutorials.sipcalculator.ui.theme.Blue
import eu.tutorials.sipcalculator.ui.theme.DarkGray
import eu.tutorials.sipcalculator.ui.theme.DarkRed
import eu.tutorials.sipcalculator.ui.theme.InputInvestmentText
import eu.tutorials.sipcalculator.ui.theme.LightGray
import eu.tutorials.sipcalculator.ui.theme.LightRed
import eu.tutorials.sipcalculator.ui.theme.White

@Composable
fun DisplayInputInvestment(
    value: Long,
    onValueChange: (Int) -> Unit,
    maxDigits: Boolean
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 32.dp, end = 32.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box {
            TextField(
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_currency_rupee_24),
                        contentDescription = "Rupee icon",
                        modifier = Modifier.size(15.dp),
                        tint = if (value < 500) DarkRed else Blue
                    )
                },
                value = TextFieldValue(
                    text = value.toString(),
                    selection = TextRange(value.toString().length)
                ),
                onValueChange = { newValue ->
                    val intValue = newValue.text.toIntOrNull() ?: 0
                    onValueChange(intValue)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .align(Alignment.CenterStart)
                    .background(if (value < 500) LightRed else White, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                textStyle = MaterialTheme.typography.bodyLarge
                    .copy(
                        textAlign = TextAlign.Start,
                        color = if (value < 500) DarkRed else Blue,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}




