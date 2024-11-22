package com.example.ecodicas.model
//Integrantes:  Luigi de Jesus Felice - Rm: 94546
//              Victor Moura Ventura - Rm: 93509

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DicaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val descricao: String
)
