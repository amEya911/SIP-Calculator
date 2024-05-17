package eu.tutorials.sipcalculator.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eu.tutorials.sipcalculator.ui.theme.Orange

@Composable
fun CustomSlider(
    value: MutableState<Float>,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    modifier: Modifier
) {
    Slider(
        value = value.value,
        onValueChange = onValueChange,
        valueRange = valueRange,
        steps = steps,
        modifier = modifier.padding(horizontal = 16.dp),
        colors = SliderDefaults.colors(
            activeTickColor = Orange, // Color of the active tick
            inactiveTickColor = Color.LightGray, // Color of the inactive tick
            thumbColor = Color.White, // Color of the thumb (slider handle)
            activeTrackColor = Orange, // Color of the active track (left of thumb)
            inactiveTrackColor = Color.LightGray, // Color of the inactive track (right of thumb)
        ),
    )
}