package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.co.mondo.ukhuwah.R

@Composable
fun CardSupport(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Support", style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 16.sp,
            )
        )
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SettingItem(
                    icon = painterResource(R.drawable.helpcircle),
                    title = "bantuan",
                    onClick = {

                    }
                )
                SettingItem(
                    icon = painterResource(R.drawable.users),
                    title = "hubungi kami",
                    onClick = {

                    }
                )
                SettingItem(
                    icon = painterResource(R.drawable.info),
                    title = "kebijakan privasi",
                    onClick = {

                    }
                )

            }
        }
    }
}