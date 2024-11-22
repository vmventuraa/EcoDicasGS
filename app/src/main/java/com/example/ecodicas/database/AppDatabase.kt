package com.example.ecodicas.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ecodicas.model.DicaEntity


@Database(entities = [DicaEntity::class], version = 1)
abstract class DicaDatabase : RoomDatabase() {
    abstract fun dicaDao(): DicaDao
}
