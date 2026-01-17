package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import id.co.mondo.ukhuwah.data.model.Children
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.theme.Pink4


@Composable
fun SelectChildren(
    selectedChild: String,
    onChildSelected: (String) -> Unit,
    children: List<Children>
) {

    var showDropdown by remember { mutableStateOf(false) }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Pilih Profile Anak",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Pink4)
                    .padding(horizontal = 20.dp)
                    .clickable(
                        onClick = {
                            showDropdown = true
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = selectedChild,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.Black
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            DropdownMenu(
                expanded = showDropdown,
                onDismissRequest = { showDropdown = false },
                modifier = Modifier
                    .background(Color.White)
                    .width(200.dp)
                    .align(Alignment.TopEnd),
                offset = DpOffset(0.dp, 4.dp)
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Semua",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.Black
                        )
                    },
                    onClick = {
                        onChildSelected("Semua")
                        showDropdown = false
                    }
                )
                children.forEach { child ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = child.name ?: "",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = Color.Black
                            )
                               },
                        onClick = {
                            onChildSelected(child.name ?: "")
                            showDropdown = false
                        }
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelectChildren() {
    Innovillage6thTheme {
        var selectedChild by remember { mutableStateOf("Semua") }
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SelectChildren(
                selectedChild = selectedChild,
                onChildSelected = {
                    selectedChild = it
                },
                children = listOf(
                    Children(name = "Anak 1"),
                    Children(name = "Anak 2"),
                    Children(name = "Anak 3")
                )

            )
        }
    }
}
