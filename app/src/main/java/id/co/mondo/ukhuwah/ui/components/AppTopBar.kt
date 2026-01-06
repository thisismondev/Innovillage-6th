package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.co.mondo.ukhuwah.R

@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    showBack: Boolean = true,
    showSearch: Boolean = false,
    showEdit: Boolean = false,
    isEditMode: Boolean = false,
    showBookmark: Boolean = false,
    onBackClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onBookmarkClick: () -> Unit = {},

) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(horizontal = 20.dp)
    ) {
        if (showBack) {
            IconButton(
                onClick = {
                    onBackClick()
                },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 16.sp
            ),
            modifier = Modifier.align(Alignment.Center)
        )

        if (showSearch){
            IconButton(
                onClick = {
                    onSearchClick()
                },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        }
        if (showEdit){
            Text(
                text = if (isEditMode) "Save" else "Edit",
                style = MaterialTheme.typography.titleMedium.copy(
                    textDecoration = TextDecoration.Underline
                ),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(
                    onClick = {
                        onSaveClick()
                    }
                )
            )
        }
        if (showBookmark){
            IconButton(
                onClick = {
                    onSearchClick()
                },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.bookmark),
                    contentDescription = "BookMark",
                    modifier = Modifier.size(24.dp)
                )
            }
        }


    }
    Divider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 0.5.dp,
        color = MaterialTheme.colorScheme.outlineVariant
    )
}