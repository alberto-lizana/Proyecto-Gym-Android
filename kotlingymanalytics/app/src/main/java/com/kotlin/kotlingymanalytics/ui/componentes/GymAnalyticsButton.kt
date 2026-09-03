package com.kotlin.kotlingymanalytics.ui.componentes

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema


@Composable
fun GymAnalyticsButton (
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color,
    showArrowIcon: Boolean = true
) {
    Button (
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(size = 14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = BlancoCrema
        )
    ) {
        Text (
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        if(showArrowIcon){
            Spacer(modifier = Modifier.width(8.dp))
            ArrowIcon(
                color = BlancoCrema,
                modifier = Modifier
            )
        }
    }
}