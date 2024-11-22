package com.example.ecodicas.data
//Integrantes:  Luigi de Jesus Felice - Rm: 94546
//              Victor Moura Ventura - Rm: 93509

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ecodicas.model.DicaEntity

@Dao
interface DicaDao {
    @Query("SELECT * FROM DicaEntity")
    fun getAll(): LiveData<List<DicaEntity>>

    @Insert
    fun insert(dica: DicaEntity)

    @Delete
    fun delete(dica: DicaEntity)
}
