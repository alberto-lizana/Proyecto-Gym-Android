package com.kotlin.kotlingymanalytics.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.kotlin.kotlingymanalytics.room.entity.CicloEntity
import com.kotlin.kotlingymanalytics.room.entity.CicloSemanaEntity
import com.kotlin.kotlingymanalytics.room.entity.EjercicioAsignadoEntity
import com.kotlin.kotlingymanalytics.room.entity.EjercicioEntity
import com.kotlin.kotlingymanalytics.room.entity.EjercicioMusculoSecundarioEntity
import com.kotlin.kotlingymanalytics.room.entity.EsquemaRepsEntity
import com.kotlin.kotlingymanalytics.room.entity.EsquemaSeriesEntity
import com.kotlin.kotlingymanalytics.room.entity.RutinaEntity
import com.kotlin.kotlingymanalytics.room.relations.CicloCompleto
import com.kotlin.kotlingymanalytics.room.relations.RutinaCompleta
import kotlinx.coroutines.flow.Flow

@Dao
interface GymDao {
    // Catálogo
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun insertarEjercicios(e: List<EjercicioEntity>): List<Long>
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun insertarMusculosSecundarios(m: List<EjercicioMusculoSecundarioEntity>)
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun insertarEsquemasReps(e: List<EsquemaRepsEntity>)
    @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun insertarEsquemasSeries(e: List<EsquemaSeriesEntity>)

    @Query("SELECT * FROM ejercicio ORDER BY nombre")
    fun ejercicios(): Flow<List<EjercicioEntity>>

    // Esquemas
    @Query("SELECT * FROM esquema_reps ORDER BY id")
    fun esquemasReps(): Flow<List<EsquemaRepsEntity>>

    @Query("SELECT * FROM esquema_series ORDER BY id")
    fun esquemasSeries(): Flow<List<EsquemaSeriesEntity>>

    // Rutinas
    @Insert suspend fun insertarRutina(r: RutinaEntity): Long
    @Insert suspend fun insertarEjerciciosAsignados(e: List<EjercicioAsignadoEntity>)

    @Transaction
    @Query("SELECT * FROM rutina WHERE id = :id")
    fun rutina(id: Long): Flow<RutinaCompleta?>

    @Transaction
    suspend fun crearRutina(rutina: RutinaEntity, asignados: List<EjercicioAsignadoEntity>): Long {
        val id = insertarRutina(rutina)
        insertarEjerciciosAsignados(asignados.map { it.copy(rutinaId = id) })
        return id
    }

    @Query("SELECT * FROM rutina WHERE usuarioId = :usuarioId ORDER BY id DESC")
    fun rutinasDeUsuario(usuarioId: Long): Flow<List<RutinaEntity>>

    @Transaction
    @Query("SELECT * FROM rutina WHERE usuarioId = :usuarioId ORDER BY id DESC")
    fun rutinasCompletasDeUsuario(usuarioId: Long): Flow<List<RutinaCompleta>>


    // Ciclos
    @Insert suspend fun insertarCiclo(c: CicloEntity): Long
    @Insert suspend fun insertarSemanas(s: List<CicloSemanaEntity>)

    @Query("UPDATE ciclo SET activo = (id = :cicloId) WHERE usuarioId = :usuarioId")
    suspend fun activarSoloEste(usuarioId: Long, cicloId: Long)

    @Transaction
    @Query("SELECT * FROM ciclo WHERE usuarioId = :usuarioId AND activo = 1 LIMIT 1")
    fun cicloActivo(usuarioId: Long): Flow<CicloCompleto?>

    @Transaction
    @Query("SELECT * FROM ciclo WHERE usuarioId = :usuarioId ORDER BY fechaInicio DESC")
    fun ciclosDeUsuario(usuarioId: Long): Flow<List<CicloCompleto>>

    @Transaction
    suspend fun crearCiclo(ciclo: CicloEntity, rutinaIdsPorSemana: List<Long>, activar: Boolean): Long {
        val id = insertarCiclo(ciclo)
        insertarSemanas(rutinaIdsPorSemana.mapIndexed { i, rId -> CicloSemanaEntity(id, i, rId) })
        if (activar) activarSoloEste(ciclo.usuarioId, id)
        return id
    }
}