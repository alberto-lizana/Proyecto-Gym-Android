package com.kotlin.kotlingymanalytics.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kotlin.kotlingymanalytics.data.models.EsquemaReps
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema

@Composable
fun SelectorEsquemaReps(
    esquemas: List<EsquemaReps>,
    seleccionado: EsquemaReps?,
    onSeleccionar: (EsquemaReps) -> Unit
) {
    var expandido by remember {
        mutableStateOf(false)
    }

    Column {

        TextButton(
            onClick = {
                expandido = !expandido
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = seleccionado?.let {

                    if (it.getRepeticionesMax() == null) {
                        "${it.getRepeticionesMin()} repeticiones"
                    } else {
                        "${it.getRepeticionesMin()} - ${it.getRepeticionesMax()} repeticiones"
                    }

                } ?: "Seleccionar repeticiones",

                color = BlancoCrema
            )
        }

        if (expandido) {

            esquemas.forEach { esquema ->

                TextButton(
                    onClick = {

                        onSeleccionar(esquema)
                        expandido = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = if (esquema.getRepeticionesMax() == null) {
                            "${esquema.getRepeticionesMin()} repeticiones"
                        } else {
                            "${esquema.getRepeticionesMin()} - ${esquema.getRepeticionesMax()} repeticiones"
                        },
                        color = BlancoCrema
                    )
                }
            }
        }
    }
}