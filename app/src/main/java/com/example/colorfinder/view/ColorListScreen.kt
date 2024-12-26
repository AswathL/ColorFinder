package com.example.colorfinder.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.colorfinder.viewmodel.MainViewModel
import com.example.colorfinder.R
import com.example.colorfinder.data.local.ColorEntry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorListScreen(viewModel: MainViewModel = viewModel()) {
    val colors = viewModel.colors.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Color App", fontWeight = FontWeight.Light, fontSize = 33.sp, color = Color.White) },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 8.dp).background(color = Color(0xFFB6B9FF), shape = CircleShape)
                    ) {
                        Text(
                            text = viewModel.colors.collectAsState().value.size.toString(),
                            color = Color.White,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        IconButton(onClick = { viewModel.syncColors() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.sync),
                                contentDescription = "Sync Colors",
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF6750A4))
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val randomColor = generateRandomColor()
                    val currentTime = System.currentTimeMillis()
                    viewModel.addColor(randomColor, currentTime)
                },
                containerColor = Color(0xFFB6B9FF),
                shape = CircleShape
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 8.dp,top = 2.dp,bottom = 2.dp,)
                ) {
                    Text("Add Color", fontWeight = FontWeight.Light, fontSize = 20.sp, modifier = Modifier.padding(start = 16.dp,end = 8.dp),color = Color(0xFF6750A4))
                    Icon(
                        modifier = Modifier.background(color = Color(0xFF6750A4), shape = CircleShape),
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Color",
                        tint = Color.White
                    )
                }
            }
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            items(colors) { colorEntry ->
                ColorCard(colorEntry)
            }
        }
    }
}

@Composable
fun ColorCard(colorEntry: ColorEntry) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.5f) // Ensure consistent aspect ratio for grid items
            .padding(8.dp)
            .background(
                color = Color(android.graphics.Color.parseColor(colorEntry.color)),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = colorEntry.color,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            HorizontalDivider(thickness = 2.dp,modifier = Modifier.padding(end = 30.dp),color = Color.White)
            Text(
                modifier = Modifier.align(Alignment.End).padding(top = 10.dp),
                text = "  Created at\n${formatDate(colorEntry.time)}",
                fontWeight = FontWeight.Light,
                color = Color.White,
                fontSize = 14.sp,
                lineHeight = 18.sp
            )
        }
    }
}

fun formatDate(timestamp: Long): String {
    val date = java.util.Date(timestamp)
    val format = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
    return format.format(date)
}

fun generateRandomColor(): String {
    val chars = "0123456789ABCDEF"
    return (1..6).map { chars.random() }.joinToString(prefix = "#", separator = "")
}
