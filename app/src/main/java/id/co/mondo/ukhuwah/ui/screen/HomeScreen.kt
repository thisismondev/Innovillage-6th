package id.co.mondo.ukhuwah.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.data.model.menuItems
import id.co.mondo.ukhuwah.ui.components.CardKader
import id.co.mondo.ukhuwah.ui.components.HeaderKader
import id.co.mondo.ukhuwah.ui.components.NavItemCard
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun HomeScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                )
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            HeaderKader(
                onClickNotification = {

                },
                onClickLogout = {
                    navController.navigate("login"){
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .offset(y = -20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)

        ) {
            CardKader()
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(20.dp)
            ) {
                items(menuItems){ item ->
                    NavItemCard(
                      title = item.title,
                        color = item.color,
                        icon = item.icon,
                        onClick = {
                            navController.navigate(item.routes)
                        }
                    )

                }
            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    Innovillage6thTheme {
        HomeScreen(navController = rememberNavController())
    }
}