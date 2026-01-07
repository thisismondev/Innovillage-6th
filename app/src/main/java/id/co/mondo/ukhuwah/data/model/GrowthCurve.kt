package id.co.mondo.ukhuwah.data.model

import androidx.compose.ui.graphics.Color

data class GrowthCurve(
    val label: String,
    val values: List<Float>,
    val color: Color
)


val months = listOf(
    0f, 1f, 2f, 3f, 4f, 5f, 6f,
    7f, 8f, 9f, 10f, 11f, 12f
)

val curves = listOf(
    GrowthCurve("-3 SD", listOf(2.5f, 2.9f, 3.2f, 3.6f, 3.9f, 4.2f, 4.5f, 4.8f, 5.1f, 5.3f, 5.6f, 5.8f, 6.0f), Color.Black),
    GrowthCurve("-2 SD", listOf(2.8f, 3.2f, 3.6f, 4.0f, 4.4f, 4.8f, 5.1f, 5.4f, 5.7f, 6.0f, 6.3f, 6.5f, 6.8f), Color.Blue),
    GrowthCurve("-1 SD", listOf(3.2f, 3.6f, 4.0f, 4.5f, 4.9f, 5.4f, 5.8f, 6.2f, 6.6f, 6.9f, 7.3f, 7.6f, 7.9f), Color.Green),
    GrowthCurve("Median", listOf(3.5f, 4.0f, 4.5f, 5.0f, 5.5f, 6.0f, 6.4f, 6.9f, 7.3f, 7.7f, 8.1f, 8.4f, 8.7f), Color.Red),
    GrowthCurve("+1 SD", listOf(3.9f, 4.4f, 5.0f, 5.6f, 6.1f, 6.7f, 7.2f, 7.7f, 8.2f, 8.6f, 9.0f, 9.4f, 9.7f), Color.Green),
    GrowthCurve("+2 SD", listOf(4.2f, 4.8f, 5.4f, 6.0f, 6.6f, 7.2f, 7.7f, 8.3f, 8.8f, 9.3f, 9.8f, 10.2f, 10.6f), Color.Blue),
    GrowthCurve("+3 SD", listOf(4.6f, 5.2f, 5.9f, 6.6f, 7.2f, 7.9f, 8.5f, 9.1f, 9.7f, 10.2f, 10.7f, 11.2f, 11.6f), Color.Black)
)

val childWeight = listOf(
    3.4f, 3.9f, 4.5f, 5.1f, 5.7f, 6.3f,
    6.8f, 7.3f, 7.7f, 8.1f, 8.5f, 8.8f, 9.1f
)
