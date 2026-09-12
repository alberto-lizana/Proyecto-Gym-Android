package com.kotlin.kotlingymanalytics.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.kotlin.kotlingymanalytics.core.utils.brzycki
import com.kotlin.kotlingymanalytics.core.utils.calcularRmFuncionSuperior
import com.kotlin.kotlingymanalytics.core.utils.epley
import com.kotlin.kotlingymanalytics.core.utils.lander
import com.kotlin.kotlingymanalytics.core.utils.oConner
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsButton
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsCard
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsOutlinedInput
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsTitulo
import com.kotlin.kotlingymanalytics.ui.theme.AzulOscuro
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema
import com.kotlin.kotlingymanalytics.ui.theme.FondoOscuro
import com.kotlin.kotlingymanalytics.ui.theme.RojoOscuro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GymAnalyticsCalcularRm() {

    var peso by rememberSaveable { mutableStateOf("") }

    var repeticiones by rememberSaveable { mutableStateOf("") }

    var formulaSeleccionada by rememberSaveable { mutableStateOf("Epley") }

    var menuAbierto by rememberSaveable { mutableStateOf(false) }

    var resultadoRm by rememberSaveable { mutableStateOf("") }

    val formulas = listOf(
        "Epley",
        "Brzycki",
        "Lander",
        "O'Conner"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        FondoOscuro,
                        AzulOscuro
                    )
                )
            )
            .padding(
                horizontal = 24.dp,
                vertical = 20.dp
            ),
        verticalArrangement = Arrangement.Top
    ) {

        GymAnalyticsTitulo(
            titulo = "Calcular 1 RM"
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        GymAnalyticsOutlinedInput(
            value = peso,
            onValueChange = {
                peso = it
                resultadoRm = ""
            },
            label = "Peso (kg)",
            keyboardType = KeyboardType.Decimal
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        GymAnalyticsOutlinedInput(
            value = repeticiones,
            onValueChange = {
                repeticiones = it
                resultadoRm = ""
            },
            label = "Repeticiones",
            keyboardType = KeyboardType.Number
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        ExposedDropdownMenuBox(
            expanded = menuAbierto,
            onExpandedChange = {
                menuAbierto = !menuAbierto
            }
        ) {

            OutlinedTextField(
                value = formulaSeleccionada,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                label = {
                    Text(
                        text = "Fórmula",
                        color = BlancoCrema
                    )
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = menuAbierto
                    )
                },
                shape = RoundedCornerShape(14.dp),
                colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = FondoOscuro.copy(alpha = 0.15f),
                    unfocusedContainerColor = FondoOscuro.copy(alpha = 0.25f),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            ExposedDropdownMenu(
                expanded = menuAbierto,
                onDismissRequest = {
                    menuAbierto = false
                }
            ) {

                formulas.forEach { formula ->

                    DropdownMenuItem(
                        text = {
                            Text(
                                text = formula
                            )
                        },
                        onClick = {
                            formulaSeleccionada = formula
                            menuAbierto = false
                            resultadoRm = ""
                        }
                    )
                }
            }
        }

        if (resultadoRm.isNotBlank()) {
            Spacer(modifier = Modifier.height(24.dp))

            GymAnalyticsCard(
                titulo = "1 RM estimado"
            ) {
                Text(
                    text = resultadoRm,
                    color = BlancoCrema,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Fórmula: $formulaSeleccionada",
                    color = BlancoCrema.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        GymAnalyticsButton(
            text = "CALCULAR RM",
            containerColor = RojoOscuro,
            onClick = {

                val pesoDouble = peso.toDoubleOrNull()
                val repeticionesInt = repeticiones.toIntOrNull()

                if (
                    pesoDouble != null &&
                    repeticionesInt != null &&
                    pesoDouble > 0 &&
                    repeticionesInt > 0
                ) {

                    val formula = when (formulaSeleccionada) {

                        "Epley" -> epley
                        "Brzycki" -> brzycki
                        "Lander" -> lander
                        "O'Conner" -> oConner

                        else -> epley
                    }

                    val resultado = calcularRmFuncionSuperior(
                        peso = pesoDouble,
                        repeticiones = repeticionesInt,
                        function = formula
                    )

                    resultadoRm = "%.2f kg".format(resultado)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}