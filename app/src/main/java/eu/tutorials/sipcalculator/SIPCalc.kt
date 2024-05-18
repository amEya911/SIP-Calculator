package eu.tutorials.sipcalculator

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.sipcalculator.components.CustomSlider
import eu.tutorials.sipcalculator.components.SIPAndLumpsumButtons
import eu.tutorials.sipcalculator.components.ShowAlert
import eu.tutorials.sipcalculator.components.textfields.DisplayInputInvestment
import eu.tutorials.sipcalculator.components.textfields.DisplayInputReturnAndPeriod
import eu.tutorials.sipcalculator.ui.theme.Blue
import eu.tutorials.sipcalculator.ui.theme.DarkRed
import eu.tutorials.sipcalculator.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun SIPCalc(viewModel: SIPViewModel) {

    val focusManager = LocalFocusManager.current
    val keyBoardController = LocalSoftwareKeyboardController.current

    var maxDigits by mutableStateOf(false)

    if (viewModel.inputTotalInvestmentSIP > 99999999 || viewModel.inputTotalInvestmentLumpsum > 99999999) {
        maxDigits = true
    }

if (viewModel.showDialog) {
    ModalBottomSheet(onDismissRequest = { viewModel.showDialog = false }) {
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Text(
                text = "Understanding Inflation and Purchasing Power\n" +
                        "\n" +
                        "Inflation: Inflation refers to the gradual increase in prices over time. As prices rise, the money you currently possess will not purchase as much in the future as it does today.\n" +
                        "\n" +
                        "Purchasing Power: Purchasing power is the amount of goods and services that can be bought with your money. When inflation occurs, the purchasing power of your money decreases.\n" +
                        "\n" +
                        "For instance, if an item costs ₹100 today and the inflation rate is 3%, the same item will cost ₹103 next year. Over many years, this effect accumulates, reducing the value of your money.\n" +
                        "\n" +
                        "Our calculator helps you understand the future value of your investments by accounting for inflation, allowing you to see their real value in today's terms.\n" +
                        "\n" +
                        "If you prefer not to include inflation in your calculations, you may set the inflation rate to 0."
            )
        }
    }
}

    LazyColumn(
        modifier = Modifier
            .background(LightGray)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                        keyBoardController?.hide()
                    }
                )
            }
    ) {
        item {

            //Display SIP and Lumpsum buttons
            Box(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxHeight()
                    .background(Blue, RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    SIPAndLumpsumButtons(
                        inputText = "SIP",
                        isSIP = viewModel.isSIP,
                        isLumpsum = viewModel.isLumpsum,
                        onClick = {
                            viewModel.toggleSIP()
                        },
                    )

                    SIPAndLumpsumButtons(
                        inputText = "Lumpsum",
                        isSIP = viewModel.isSIP,
                        isLumpsum = viewModel.isLumpsum,
                        onClick = { viewModel.toggleLumpsum() }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))

            Text(text = if (viewModel.isSIP) "Monthly Investment" else "Total Investment", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 32.dp))

            //Display Input investment text field
            if (viewModel.isSIP) {
                DisplayInputInvestment(
                    value = if (!maxDigits) viewModel.inputTotalInvestmentSIP else 99999999L,
                    onValueChange = { newValue ->
                        viewModel.inputTotalInvestmentSIP = newValue.toLong()
                        if (newValue < 99999999) maxDigits = false
                    },
                    maxDigits = maxDigits // Pass maxDigits here
                )
            } else {
                DisplayInputInvestment(
                    value = if (!maxDigits) viewModel.inputTotalInvestmentLumpsum else 99999999L,
                    onValueChange = { newValue ->
                        viewModel.inputTotalInvestmentLumpsum = newValue.toLong()
                        if (newValue < 99999999) maxDigits = false
                    },
                    maxDigits = maxDigits // Pass maxDigits here
                )
            }

            if (viewModel.isSIP) ShowAlert(input = viewModel.inputTotalInvestmentSIP, limit = 500) else ShowAlert(
                input = viewModel.inputTotalInvestmentLumpsum, limit = 500)

            Spacer(modifier = Modifier.height(32.dp))

            //Display Return rate input
            DisplayInputReturnAndPeriod(
                text = "Expected return rate (p.a)",
                value = viewModel.inputEstReturn,
                onValueChange = {
                    viewModel.inputEstReturn = it.toInt()
                },
                viewModel = viewModel,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_percent_24),
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        tint = if (viewModel.inputEstReturn < 1) DarkRed else Blue
                    )
                }
            )

            if (viewModel.inputEstReturn > 30) viewModel.inputEstReturn = 30

            ShowAlert(input = viewModel.inputEstReturn, limit = 1)

            CustomSlider(
                value = mutableStateOf(viewModel.inputEstReturn.toFloat()),
                onValueChange = { viewModel.inputEstReturn = it.toInt() },
                valueRange = 1f..30f,
                steps = 29,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            //Display Time period input
            DisplayInputReturnAndPeriod(
                text = "Time period",
                value = viewModel.inputTimePeriod,
                onValueChange = {
                    viewModel.inputTimePeriod = it.toInt()
                },
                viewModel = viewModel,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = if (viewModel.inputTimePeriod < 1) DarkRed else Blue
                        )
                }
            )

            if (viewModel.inputTimePeriod > 40) viewModel.inputTimePeriod = 40

            ShowAlert(input = viewModel.inputTimePeriod, limit = 1)

            CustomSlider(
                value = mutableStateOf(viewModel.inputTimePeriod.toFloat()),
                onValueChange = { viewModel.inputTimePeriod = it.toInt() },
                valueRange = 1f..40f,
                steps = 39,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            DisplayInputReturnAndPeriod(
                text = "Inflation",
                value = viewModel.inputInflation,
                onValueChange = {
                    viewModel.inputInflation = it.toInt()
                },
                viewModel = viewModel,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_percent_24),
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        tint = Blue
                    )
                }
            )

            CustomSlider(
                value = mutableStateOf(viewModel.inputInflation.toFloat()),
                onValueChange = { viewModel.inputInflation = it.toInt()},
                valueRange = 0f..20f,
                steps = 20,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            if (viewModel.isSIP) viewModel.calculateSIP() else viewModel.calculateLumpsum()

            //Display Box
            BottomBox(
                displayTotalInvestment = viewModel.displayTotalInvestment,
                displayEstReturn = viewModel.displayEstReturn,
                displayTotalValue = viewModel.displayTotalValue,
                displayTotalValueAfterInflation = viewModel.displayTotalValueAfterInflation,
                inputInflation = viewModel.inputInflation
            )
        }
    }
}


















