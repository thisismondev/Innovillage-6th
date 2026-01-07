package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisGuidelineComponent
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisLabelComponent
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisLineComponent
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberEnd
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStart
import com.patrykandpatrick.vico.compose.cartesian.layer.continuous
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoScrollState
import com.patrykandpatrick.vico.compose.common.fill
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianLayerRangeProvider
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.cartesian.layer.LineCartesianLayer
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.theme.abu

@Composable
fun BasicLineChart(
    modifier: Modifier = Modifier
) {
    // 1️⃣ Menyimpan & mengelola data chart
    val modelProducer = remember { CartesianChartModelProducer() }

    val x = listOf(
        45f, 50f, 55f, 60f, 65f, 70f, 75f, 80f, 85f, 90f, 95f, 100f, 105f, 110f
    )

    val line1 = listOf(
        1.9f, 2.6f, 3.5f, 4.5f, 5.5f, 6.3f, 7.1f, 7.8f, 8.7f, 9.7f, 10.6f, 11.6f, 12.8f, 14f
    )

    val line2 = listOf(
        2.1f, 2.8f, 3.8f, 4.9f, 5.9f, 6.9f, 7.7f, 8.5f, 9.4f, 10.5f, 11.5f, 12.6f, 14.0f, 15.3f
    )
    val line3 = listOf(
        2.3f, 3.1f, 4.2f, 5.4f, 6.5f, 7.5f, 8.4f, 9.2f, 10.3f, 11.4f, 12.6f, 13.7f, 15.3f, 16.7f
    )
    val line4 = listOf(
        2.5f, 3.4f, 4.5f, 5.9f, 7.1f, 8.2f, 9.1f, 10.1f, 11.2f, 12.5f, 13.7f, 15.0f, 16.7f, 18.3f
    )
    val line5 = listOf(
        2.7f, 3.7f, 5.0f, 6.4f, 7.8f, 9.0f, 10.0f, 11.0f, 12.3f, 13.7f, 15.0f, 16.5f, 18.4f, 20.2f
    )
    val line6 = listOf(
        3.0f, 4.0f, 5.5f, 7.1f, 8.6f, 9.9f, 11.0f, 12.1f, 13.3f, 15.0f, 16.5f, 18.1f, 20.2f, 22.3f
    )
    val line7 = listOf(
        3.3f, 4.5f, 6.1f, 7.8f, 9.5f, 10.9f, 12.2f, 13.4f, 14.7f, 16.5f, 18.2f, 20.0f, 22.4f, 24.7f
    )


    // 2️⃣ Mengisi data chart (sekali saat pertama render)
    LaunchedEffect(Unit) {
        modelProducer.runTransaction {
            lineSeries {
                series(x, line1)
                series(x, line2)
                series(x, line3)
                series(x, line4)
                series(x, line5)
                series(x, line6)
                series(x, line7)
            }
        }
    }

    val lineLayer = rememberLineCartesianLayer(
        rangeProvider = CartesianLayerRangeProvider.fixed(
            minX = 45.0,
            maxX = 110.0,
            minY = 0.0,
            maxY = 25.0
        ),
        lineProvider = LineCartesianLayer.LineProvider.series(
            // 🔴 Line 1
            LineCartesianLayer.rememberLine(
                stroke = LineCartesianLayer.LineStroke.continuous(
                    thickness = 2.dp,
                    cap = StrokeCap.Round
                ),
                fill = LineCartesianLayer.LineFill.single(fill(Color.Black))
            ),
            LineCartesianLayer.rememberLine(
                stroke = LineCartesianLayer.LineStroke.continuous(
                    thickness = 2.dp,
                    cap = StrokeCap.Round
                ),
                fill = LineCartesianLayer.LineFill.single(fill(Color.Red))
            ),
            LineCartesianLayer.rememberLine(
                stroke = LineCartesianLayer.LineStroke.continuous(
                    thickness = 2.dp,
                    cap = StrokeCap.Round
                ),
                fill = LineCartesianLayer.LineFill.single(fill(Color.Yellow))
            ),
            LineCartesianLayer.rememberLine(
                stroke = LineCartesianLayer.LineStroke.continuous(
                    thickness = 2.dp,
                    cap = StrokeCap.Round
                ),
                fill = LineCartesianLayer.LineFill.single(fill(Color.Green))
            ),
            LineCartesianLayer.rememberLine(
                stroke = LineCartesianLayer.LineStroke.continuous(
                    thickness = 2.dp,
                    cap = StrokeCap.Round
                ),
                fill = LineCartesianLayer.LineFill.single(fill(Color.Yellow))
            ),
            LineCartesianLayer.rememberLine(
                stroke = LineCartesianLayer.LineStroke.continuous(
                    thickness = 2.dp,
                    cap = StrokeCap.Round
                ),
                fill = LineCartesianLayer.LineFill.single(fill(Color.Red))
            ),
            LineCartesianLayer.rememberLine(
                stroke = LineCartesianLayer.LineStroke.continuous(
                    thickness = 2.dp,
                    cap = StrokeCap.Round
                ),
                fill = LineCartesianLayer.LineFill.single(fill(Color.Black))
            ),

            ),
    )


    // 3️⃣ Menampilkan chart
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
    ) {
        CartesianChartHost(
            chart = rememberCartesianChart(
                lineLayer,
                startAxis = VerticalAxis.rememberStart(
                    title = "Berat (kg)",
                    titleComponent = rememberAxisLabelComponent(
                        textSize = 12.sp,
                        color = Color.Black
                    ),
                    label = rememberAxisLabelComponent(
                        textSize = 8.sp,
                        color = Color.Black
                    ),
                    itemPlacer = remember {
                        VerticalAxis.ItemPlacer.step({ 2.0 })
                    },
                    line = rememberAxisLineComponent(),
                    tick = null,
                    guideline = rememberAxisGuidelineComponent(),
                ),
                endAxis = VerticalAxis.rememberEnd(
                    label = rememberAxisLabelComponent(
                        textSize = 8.sp,
                        color = Color.Black
                    ),
                    itemPlacer = remember {
                        VerticalAxis.ItemPlacer.step({ 2.0 })
                    },
                    line = rememberAxisLineComponent(),
                    tick = null,
                    guideline = rememberAxisGuidelineComponent()
                ),
                bottomAxis = HorizontalAxis.rememberBottom(
                    title = "Panjang (cm)",
                    titleComponent = rememberAxisLabelComponent(
                        textSize = 12.sp,
                        color = Color.Black
                    ),
                    label = rememberAxisLabelComponent(
                        textSize = 8.sp,
                        color = Color.Black
                    ),
                    line = rememberAxisLineComponent(),
                    tick = null,
                    guideline = rememberAxisGuidelineComponent(),
                )
            ),
            modelProducer = modelProducer,
            scrollState = rememberVicoScrollState(),
            modifier = Modifier
                .fillMaxWidth()
                .height(275.dp)
                .padding(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLinechart() {
    Innovillage6thTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(abu)
        ) {
            Text("Diagram")
            BasicLineChart(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            )
        }
    }
}