package com.afebrii.jogjaventure.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.afebrii.jogjaventure.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithBackButton(
    title: String,
    onBackPressed: () -> Unit
) {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = onBackPressed,
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = title,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}


@Composable
fun ScreenDetail(placeName: String, onBackPressed: () -> Unit) {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                AppBarWithBackButton(title = "Detail", onBackPressed = onBackPressed)
            }
            Image(
                painter = painterResource(id = getImageResourceId(placeName)),
                contentDescription = "Foto $placeName",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = placeName,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Description(placeName = placeName)
        }
    }
}

@Composable
fun Description(placeName: String) {
    val description = when (placeName) {
        "Candi Prambanan" -> "Kompleks candi Hindu terbesar di Indonesia yang didedikasikan untuk Trimurti: Brahma, Wisnu, dan Siwa."
        "Keraton Yogyakarta" -> "Istana resmi Kesultanan Yogyakarta yang juga menjadi pusat budaya Jawa."
        "Taman Sari" -> "Kompleks istana air yang digunakan oleh keluarga kerajaan untuk rekreasi dan meditasi."
        "Malioboro" -> "Jalan utama yang terkenal dengan pusat perbelanjaan dan oleh-oleh khas Yogyakarta."
        "Pantai Parangtritis" -> "Pantai yang terkenal dengan pemandangan matahari terbenam dan legenda Ratu Kidul."
        "Hutan Pinus Mangunan" -> "Hutan pinus yang menawarkan udara sejuk dan pemandangan indah untuk bersantai dan berfoto."
        "Museum Ullen Sentalu" -> "Museum yang menampilkan budaya dan sejarah Kerajaan Mataram."
        else -> ""
    }

    Text(
        text = description,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

fun getImageResourceId(placeName: String): Int {
    return when (placeName) {
        "Candi Prambanan" -> R.drawable.prambanan
        "Keraton Yogyakarta" -> R.drawable.keraton_yogya
        "Taman Sari" -> R.drawable.taman_sari
        "Malioboro" -> R.drawable.malioboro
        "Pantai Parangtritis" -> R.drawable.pantai_parangritis
        "Hutan Pinus Mangunan" -> R.drawable.hutan_pinus_mangunan
        "Museum Ullen Sentalu" -> R.drawable.museum_ullen
        else -> R.drawable.ic_launcher_background
    }
}