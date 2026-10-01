package com.kotlin.kotlingymanalytics.room.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kotlin.kotlingymanalytics.data.enums.GrupoMuscular
import com.kotlin.kotlingymanalytics.room.dao.GymDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        EjercicioEntity::class, 
        EjercicioMusculoSecundarioEntity::class,
        EsquemaRepsEntity::class, 
        EsquemaSeriesEntity::class, 
        RutinaEntity::class,
        EjercicioAsignadoEntity::class, 
        CicloEntity::class, 
        CicloSemanaEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class GymDatabase : RoomDatabase() {

    abstract fun gymDao(): GymDao

    companion object {
        @Volatile
        private var INSTANCE: GymDatabase? = null

        fun getInstance(context: Context): GymDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    GymDatabase::class.java,
                    "gym.db"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                INSTANCE?.gymDao()?.let { precargar(it) }
                            }
                        }
                    })
                    .build()
                    .also { INSTANCE = it }
            }

        private suspend fun precargar(dao: GymDao) {
            // Series: ids 0..19 -> 1..20 series
            dao.insertarEsquemasSeries(
                (1..20).map { EsquemaSeriesEntity((it - 1).toLong(), it) }
            )

            // Reps: ids 0..5 fijas (1..6) e ids 6..11 rangos
            val fijas = (1..6).map { EsquemaRepsEntity((it - 1).toLong(), it) }
            val rangos = listOf(4 to 6, 6 to 8, 8 to 10, 10 to 12, 12 to 15, 15 to 20)
                .mapIndexed { i, (min, max) -> EsquemaRepsEntity((6 + i).toLong(), min, max) }
            dao.insertarEsquemasReps(fijas + rangos)

            // Ejercicios: el id es la posición + 1 (igual que tu ejerciciosBase)
            val base = listOf(
                Triple("Press banca", GrupoMuscular.PECHO, listOf(GrupoMuscular.TRICEPS, GrupoMuscular.HOMBRO)),
                Triple("Press inclinado con mancuernas", GrupoMuscular.PECHO, listOf(GrupoMuscular.TRICEPS, GrupoMuscular.HOMBRO)),
                Triple("Dominadas", GrupoMuscular.ESPALDA, listOf(GrupoMuscular.BICEPS)),
                Triple("Remo con barra", GrupoMuscular.ESPALDA, listOf(GrupoMuscular.BICEPS)),
                Triple("Press militar", GrupoMuscular.HOMBRO, listOf(GrupoMuscular.TRICEPS)),
                Triple("Curl de bíceps con barra", GrupoMuscular.BICEPS, emptyList()),
                Triple("Extensión de tríceps en polea", GrupoMuscular.TRICEPS, emptyList()),
                Triple("Sentadilla con barra", GrupoMuscular.PIERNA, listOf(GrupoMuscular.GLUTEO, GrupoMuscular.CORE)),
                Triple("Peso muerto rumano", GrupoMuscular.PIERNA, listOf(GrupoMuscular.GLUTEO)),
                Triple("Hip thrust", GrupoMuscular.GLUTEO, listOf(GrupoMuscular.PIERNA)),
                Triple("Plancha abdominal", GrupoMuscular.CORE, emptyList())
            )

            dao.insertarEjercicios(
                base.mapIndexed { i, (nombre, principal, _) ->
                    EjercicioEntity(
                        id = (i + 1).toLong(),
                        nombre = nombre,
                        grupoMuscularPrincipal = principal
                    )
                }
            )

            dao.insertarMusculosSecundarios(
                base.flatMapIndexed { i, (_, _, secundarios) ->
                    secundarios.map { EjercicioMusculoSecundarioEntity((i + 1).toLong(), it) }
                }
            )
        }
    }
}