package id.co.mondo.ukhuwah.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.R
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.ParentCard
import id.co.mondo.ukhuwah.ui.components.SearchField
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun ParentScreen(
    navController: NavController,
    contentPadding: PaddingValues
) {

    var query by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
    ) {
        AppTopBar(
            title = "Orang Tua",
            showBack = false,

        )
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            SearchField(
                query = query,
                onQueryChange = {
                    query = it
                },
                modifier = Modifier.weight(1F)
            )
            Button(
                onClick = {
                    navController.navigate("register")

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.height(48.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(R.drawable.family),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Buat Akun",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 10.sp
                        )
                    )
                }

            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(10) {
                ParentCard(
                    onClick = {

                    }
                )

            }

        }
    }
}


@Preview(showBackground = true, device = PIXEL_4)
@Composable
fun PreviewParentScreen() {
    Innovillage6thTheme {
        ParentScreen(
            navController = rememberNavController(),
            contentPadding = PaddingValues()
        )
    }
}