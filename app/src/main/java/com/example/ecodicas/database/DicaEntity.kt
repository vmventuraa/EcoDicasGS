package com.example.ecodicas.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DicaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val descricao: String
)
