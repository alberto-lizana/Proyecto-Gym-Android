package com.kotlin.kotlingymanalytics.ui.theme.componentes

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.kotlin.kotlingymanalytics.R
import com.kotlin.kotlingymanalytics.data.enums.EnumLogoType


@Composable
fun Logo(
    type: EnumLogoType = EnumLogoType.PRINCIPAL,
    modifier: Modifier = Modifier,
    contentDescription: String? = "Logo GymAnalytics"
) {
    val logo = when (type) {
        EnumLogoType.PRINCIPAL -> R.drawable.logo_ajustado_1
        EnumLogoType.LARGO -> R.drawable.logo_ajustado_2
        EnumLogoType.RESUMIDO -> R.drawable.logo_ajustado_3
    }

    Image(
        painter = painterResource(logo),
        contentDescription = contentDescription,
        modifier = modifier
    )
}