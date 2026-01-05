package id.co.mondo.ukhuwah.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.R
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.NotifItem
import id.co.mondo.ukhuwah.ui.components.TitleNotif
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun NotificationScreen(
    navController: NavController,
    contentPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        AppTopBar(
            title = "Notification",
            showBack = true,
            onBackClick = {
                navController.popBackStack()
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                TitleNotif("Today")
            }
            item {
                NotifItem(
                    title = "Berhasil",
                    desc = "membuat akun 'A.Ulfa Tenripada'",
                    time = "14:39",
                    color = Color.Green,
                    tintIcon = Color.White,
                    painter = painterResource(R.drawable.done)
                )
            }
            item {
                NotifItem(
                    title = "Gagal",
                    desc = "membuat akun 'A.Ulfa Tenripada'",
                    time = "14:39",
                    color = Color.Red,
                    tintIcon = Color.White,
                    painter = painterResource(R.drawable.close)
                )
            }
            item {
                TitleNotif("Yesterday")
            }
            item {
                NotifItem(
                    title = "Jadwal",
                    desc = "Waktu Pengukuran akan tiba Besok! Jangan lupa datang ya bu'",
                    time = "14:39",
                    color = Color.Yellow,
                    tintIcon = Color.White,
                    painter = painterResource(R.drawable.calendar)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    Innovillage6thTheme {
        NotificationScreen(
            navController = rememberNavController(),
            contentPadding = PaddingValues()
        )
    }
}