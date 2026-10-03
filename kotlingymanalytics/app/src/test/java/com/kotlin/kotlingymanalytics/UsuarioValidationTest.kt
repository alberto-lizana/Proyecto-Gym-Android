package com.kotlin.kotlingymanalytics

import com.kotlin.kotlingymanalytics.core.utils.validarApmat
import com.kotlin.kotlingymanalytics.core.utils.validarAppat
import com.kotlin.kotlingymanalytics.core.utils.validarEmail
import com.kotlin.kotlingymanalytics.core.utils.validarFechaNacimiento
import com.kotlin.kotlingymanalytics.core.utils.validarNombre
import com.kotlin.kotlingymanalytics.core.utils.validarPassword
import com.kotlin.kotlingymanalytics.core.utils.validarPasswordMatcher
import com.kotlin.kotlingymanalytics.data.enums.SexoTipo
import com.kotlin.kotlingymanalytics.data.models.Usuario
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.time.LocalDate

class UsuarioValidationTest {

    // TEST PARA NOMBRE
    @Test
    fun nombreValido_noDebeTenerErrores() {
        val errores = mutableMapOf<String, String>()

        validarNombre("Alberto", errores)

        assertTrue(errores.isEmpty())
    }

    @Test
    fun nombreVacio_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarNombre("", errores)

        assertTrue(errores.containsKey("vacio"))
    }

    @Test
    fun nombreConNumeros_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarNombre("Alberto123", errores)

        assertTrue(errores.containsKey("caracteres invalidos"))
    }

    @Test
    fun nombreConMenosDeTresLetras_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarNombre("Al", errores)

        assertTrue(errores.containsKey("corto"))
    }

    // TEST PARA APPAT
    @Test
    fun apellidoPaternoValido_noDebeTenerErrores() {
        val errores = mutableMapOf<String, String>()

        validarAppat("Lizana", errores)

        assertTrue(errores.isEmpty())
    }

    @Test
    fun apellidoPaternoCompuesto_debeSerValido() {
        val errores = mutableMapOf<String, String>()

        validarAppat("De La Cruz", errores)

        assertTrue(errores.isEmpty())
    }

    // TEST PARA APMAT
    @Test
    fun apellidoMaternoVacio_noDebeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarApmat("", errores)

        assertTrue(errores.isEmpty())
    }

    @Test
    fun apellidoMaternoConNumeros_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarApmat("Lizana123", errores)

        assertTrue(errores.containsKey("caracteres_invalidos"))
    }

    // TEST PARA FECHA NACIMIENTO
    @Test
    fun fechaNacimientoValida_noDebeGenerarErrores() {
        val errores = mutableMapOf<String, String>()

        validarFechaNacimiento(
            LocalDate.of(2000, 5, 10),
            errores
        )

        assertTrue(errores.isEmpty())
    }

    @Test
    fun fechaNacimientoNula_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarFechaNacimiento(null, errores)

        assertTrue(errores.containsKey("vacio"))
    }

    @Test
    fun fechaNacimientoMayorA100Anios_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarFechaNacimiento(
            LocalDate.now().minusYears(101),
            errores
        )

        assertTrue(errores.containsKey("muy_antigua"))
    }


    @Test
    fun fechaNacimientoMenorA5Anios_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarFechaNacimiento(
            LocalDate.now().minusYears(4),
            errores
        )

        assertTrue(errores.containsKey("muy_reciente"))
    }

    // TEST PARA EMAIL
    @Test
    fun emailValido_noDebeGenerarErrores() {
        val errores = mutableMapOf<String, String>()

        validarEmail(
            "alberto@gmail.com",
            errores,
            emptyList()
        )

        assertTrue(errores.isEmpty())
    }


    @Test
    fun emailInvalido_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarEmail(
            "alberto@gmail",
            errores,
            emptyList()
        )

        assertTrue(errores.containsKey("formato_invalido"))
    }

    @Test
    fun emailExistente_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        val usuarios = listOf(
            Usuario(
                id = 1,
                nombre = "Alberto",
                appat = "Lizana",
                apmat = "Rojas",
                fechaNacimiento = LocalDate.of(2000, 1, 1),
                email = "alberto@gmail.com",
                password = "Password123!",
                sexo = SexoTipo.MASCULINO
            )
        )

        validarEmail(
            "alberto@gmail.com",
            errores,
            usuarios
        )

        assertTrue(errores.containsKey("email_existe"))
    }

    // TEST PARA CONTRASEÑA
    @Test
    fun passwordValida_noDebeGenerarErrores() {
        val errores = mutableMapOf<String, String>()

        validarPassword("Password123!", errores)

        assertTrue(errores.isEmpty())
    }

    @Test
    fun passwordMuyCorta_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarPassword("Pass1!", errores)

        assertTrue(errores.containsKey("longitud"))
    }

    @Test
    fun passwordSinMayuscula_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarPassword("password123!", errores)

        assertTrue(errores.containsKey("formato_invalido"))
    }

    @Test
    fun passwordSinMinuscula_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarPassword("PASSWORD123!", errores)

        assertTrue(errores.containsKey("formato_invalido"))
    }

    @Test
    fun passwordSinCaracterEspecial_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarPassword("Password1234", errores)

        assertTrue(errores.containsKey("formato_invalido"))
    }

    @Test
    fun passwordConEspacioInicial_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarPassword(" Password123!", errores)

        assertTrue(errores.containsKey("formato_invalido"))
    }

    // TEST CONFIRMACION DE CONTRASEÑA
    @Test
    fun passwordsIguales_noDebeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarPasswordMatcher(
            "Password123!",
            "Password123!",
            errores
        )

        assertTrue(errores.isEmpty())
    }

    @Test
    fun passwordsDiferentes_debeGenerarError() {
        val errores = mutableMapOf<String, String>()

        validarPasswordMatcher(
            "Password123!",
            "Password456!",
            errores
        )

        assertTrue(errores.containsKey("no_coincide"))
    }
}