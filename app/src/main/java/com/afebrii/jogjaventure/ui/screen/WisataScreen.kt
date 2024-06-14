package com.afebrii.jogjaventure.ui.screen


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


val tempat_wisata = listOf(
    "Candi Prambanan", "Keraton Yogyakarta", "Taman Sari", "Malioboro", "Pantai Parangtritis",
    "Hutan Pinus Mangunan", "Museum Ullen Sentalu"
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WisataScreen(navController: NavController, param: (Any) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Screen 1 Lazy Row dan Lazy Column")
                }
            )
        },
        content = {
            Column {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 15.dp, vertical = 15.dp)
                ) {
                    items(tempat_wisata.size) { index ->
                        val placeName = tempat_wisata[index]
                        PhotoItemColumn(placeName, onClick = {
                            navController.navigate("detail_screen/$placeName")
                        }, navController = navController)
                    }
                }
            }
        }
    )
}

@Composable
fun PhotoItemColumn(photoName: String, onClick: () -> Unit, navController: NavController) {
    val imageResId = getImageResourceId(photoName)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Foto $photoName",
            modifier = Modifier
                .size(150.dp)
                .clickable(onClick = onClick)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = photoName,
            fontSize = 17.sp,
            style = TextStyle.Default,
            textAlign = TextAlign.Center
        )
    }
}
