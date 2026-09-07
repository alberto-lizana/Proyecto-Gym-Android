package com.kotlin.kotlingymanalytics.ui.componentes

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Today

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.offline.Download
import com.kotlin.kotlingymanalytics.ui.theme.AzulClaro

@Composable
fun LockIcon (color: Color, modifier: Modifier, size: Dp = 24.dp){
    Icon(
        imageVector = Icons.Default.Lock,
        contentDescription = null,
        tint = color,
        modifier = modifier.size(size)
    )
}

@Composable
fun EmailIcon (color: Color, modifier: Modifier, size: Dp = 24.dp){
    Icon(
        imageVector = Icons.Default.Email,
        contentDescription = null,
        tint = color,
        modifier = modifier.size(size)
    )
}

@Composable
fun AddCircleOutlineIcon (color: Color, modifier: Modifier, size: Dp = 24.dp){
    Icon(
        imageVector = Icons.Default.AddCircleOutline,
        contentDescription = null,
        tint = color,
        modifier = modifier.size(size)
    )
}

@Composable
fun ArrowIcon (color: Color, modifier: Modifier, size: Dp = 24.dp){
    Icon(
    imageVector = Icons.Default.KeyboardDoubleArrowRight,
    contentDescription = null,
    tint = color,
    modifier = modifier.size(size)
    )
}

@Composable
fun HomeIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    contentDescription: String? = null
) {
    Icon(
        imageVector = Icons.Default.Home,
        contentDescription = contentDescription,
        modifier = modifier.size(size)
    )
}

@Composable
fun HoyIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    contentDescription: String? = null
) {
    Icon(
        imageVector = Icons.Default.Today,
        contentDescription = contentDescription,
        modifier = modifier.size(size)
    )
}

@Composable
fun StatsIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    contentDescription: String? = null
) {
    Icon(
        imageVector = Icons.Default.BarChart,
        contentDescription = contentDescription,
        modifier = modifier.size(size)
    )
}


@Composable
fun AddIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    contentDescription: String? = null,
    tint: Color = AzulClaro
) {
    Icon(
        imageVector = Icons.Default.Add,
        contentDescription = contentDescription,
        modifier = modifier.size(size),
        tint = tint
    )
}

@Composable
fun PersonIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    contentDescription: String? = null
) {
    Icon(
        imageVector = Icons.Default.Person,
        contentDescription = contentDescription,
        modifier = modifier.size(size)
    )
}

@Composable
fun MancuernaIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    contentDescription: String? = null,
    tint: Color = AzulClaro
) {
    Icon(
        imageVector = Icons.Default.FitnessCenter,
        contentDescription = contentDescription,
        modifier = modifier.size(size),
        tint = tint
    )
}

@Composable
fun DownloadIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    contentDescription: String? = null,
    tint: Color = AzulClaro
) {
    Icon(
    imageVector = Icons.Default.Download,
    contentDescription = null,
        tint = tint
    )
}

@Composable
fun CalendarIcon(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    contentDescription: String? = null,
    tint: Color = AzulClaro
){
    Icon(
        imageVector = Icons.Default.CalendarMonth,
        contentDescription = null,
        tint = tint
    )
}