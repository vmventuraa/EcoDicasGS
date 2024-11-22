package com.example.ecodicas.data

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
