package com.example.ecodicas.viewmodel
//Integrantes:  Luigi de Jesus Felice - Rm: 94546
//              Victor Moura Ventura - Rm: 93509

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.ecodicas.data.DicaDao
import com.example.ecodicas.data.DicaDatabase
import com.example.ecodicas.model.DicaEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DicaViewModel(application: Application) : AndroidViewModel(application) {
    private val dicaDao: DicaDao
    val dicasLiveData: LiveData<List<DicaEntity>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            DicaDatabase::class.java,
            "dicas_database"
        ).build()
        dicaDao = database.dicaDao()
        dicasLiveData = dicaDao.getAll()
        this.addDica("Luigi de Jesus Felice","RM: 94546")
        this.addDica("Victor Moura Ventura","RM: 93509")

    }

    fun addDica(titulo: String, descricao: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newDica = DicaEntity(titulo = titulo, descricao = descricao)
            dicaDao.insert(newDica)
        }
    }
    fun removeDica(dica: DicaEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dicaDao.delete(dica)
        }
    }
}
