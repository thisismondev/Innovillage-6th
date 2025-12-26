package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun FilterTabs(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
) {
    val tabs = listOf("Semua", "Bulan ini", "Bulan lalu", "Pilih rentang")
    val dateRange = listOf(
        "Maret 2025",
        "Januari 2025",
        "November 2024"
    )

    var showDropdown by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(22.dp)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        tabs.forEach { title ->
            val isSelected = selectedTab == title ||
                    (title == "Pilih rentang" && selectedTab in dateRange)
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(4.dp))
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .background(
                        if (isSelected)
                            MaterialTheme.colorScheme.primary
                        else
                            Color.Transparent
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
                    .clickable {
                        if (title == "Pilih rentang") {
                            showDropdown = true
                        } else {
                            onTabSelected(title)
                            showDropdown = false
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if (title == "Pilih rentang" && selectedTab in dateRange)
                            selectedTab
                        else
                            title,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 10.sp,
                            color = if (isSelected) Color.White else Color.Gray
                        ),
                        maxLines = 1
                    )

                    if (title == "Pilih rentang") {
                        Spacer(Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = if (isSelected) Color.White else Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    if (title == "Pilih rentang") {
                        DropdownMenu(
                            expanded = showDropdown,
                            onDismissRequest = { showDropdown = false },
                            modifier = Modifier
                                .background(Color.White)
                                .width(180.dp)
                        ) {
                            dateRange.forEach { range ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = range,
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                fontSize = 10.sp,
                                            ),
                                        )
                                    },
                                    onClick = {
                                        onTabSelected(range)
                                        showDropdown = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFilterTabs() {
    Innovillage6thTheme {
        var selectedTab by remember { mutableStateOf("Semua") }
        Column(
            Modifier.fillMaxSize()
        ) {
            FilterTabs(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                },
            )
        }
    }
}
