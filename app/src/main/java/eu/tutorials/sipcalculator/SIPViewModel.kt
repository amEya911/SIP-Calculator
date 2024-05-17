package eu.tutorials.sipcalculator

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import kotlin.math.pow

class SIPViewModel : ViewModel() {

    var displayTotalInvestment by mutableLongStateOf(0)
    var displayEstReturn by mutableLongStateOf(0)
    var displayTotalValue by mutableLongStateOf(0)
    var displayTotalValueAfterInflation by mutableLongStateOf(0)

    var inputTotalInvestmentLumpsum by mutableLongStateOf(25000)
    var inputTotalInvestmentSIP by mutableLongStateOf(25000)
    var inputEstReturn by mutableIntStateOf(12)
    var inputTimePeriod by mutableIntStateOf(10)
    var inputInflation by mutableIntStateOf(0)
    var showDialog by mutableStateOf(false)

    var isSIP by mutableStateOf(true)
    var isLumpsum by mutableStateOf(false)


    fun calculateLumpsum() {
        val totalInvestmentLumpsum = if (inputTotalInvestmentLumpsum < 500) 500 else inputTotalInvestmentLumpsum
        val estReturn = if (inputEstReturn < 1) 0.01 else inputEstReturn.toDouble() / 100
        val timePeriod = if (inputTimePeriod < 1) 1 else inputTimePeriod
        val inflation = (1 + (inputInflation.toDouble() / 100)).pow(timePeriod)

        displayTotalInvestment = totalInvestmentLumpsum
        displayTotalValue = ((totalInvestmentLumpsum * (1 + estReturn).pow(timePeriod))).toLong()
        displayEstReturn = displayTotalValue - displayTotalInvestment
        displayTotalValueAfterInflation = (displayTotalValue / inflation).toLong()
    }

    fun calculateSIP() {
        val totalInvestmentSIP = if (inputTotalInvestmentSIP < 500) 500 else inputTotalInvestmentSIP
        val estReturn = if (inputEstReturn < 1) 0.01 else inputEstReturn.toDouble() / 100
        val timePeriod = if (inputTimePeriod < 1) 1 else inputTimePeriod
        val inflation = (1 + (inputInflation.toDouble() / 100)).pow(timePeriod)

        val monthlyRate = estReturn / 12
        val totalMonths = timePeriod.toLong() * 12

        displayTotalInvestment = (totalMonths * totalInvestmentSIP)
        displayTotalValue = (totalInvestmentSIP.toDouble() * ((1 + monthlyRate).pow(totalMonths.toInt()) - 1) / monthlyRate * (1 + monthlyRate)).toLong()
        displayEstReturn = displayTotalValue - displayTotalInvestment
        displayTotalValueAfterInflation = (displayTotalValue / inflation).toLong()
    }



    fun toggleSIP() {
        isSIP = true
        isLumpsum = false
        inputTotalInvestmentSIP = inputTotalInvestmentLumpsum
    }

    fun toggleLumpsum() {
        isSIP = false
        isLumpsum = true
        inputTotalInvestmentLumpsum = inputTotalInvestmentSIP
    }
}
