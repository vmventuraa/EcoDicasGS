package com.example.ecodicas
//Integrantes:  Luigi de Jesus Felice - Rm: 94546
//              Victor Moura Ventura - Rm: 93509

import DicaAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecodicas.details.DetalhesDicaDialog
import com.example.ecodicas.viewmodel.DicaViewModel
import com.example.ecodicas.viewmodel.DicaViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: DicaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "EcoDicas"

        val searchView = findViewById<SearchView>(R.id.searchView)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dicaAdapter = DicaAdapter(
            onDeleteClick = { dica ->
                viewModel.removeDica(dica)
                Toast.makeText(this, "Dica removida: ${dica.titulo}", Toast.LENGTH_SHORT).show()
            },
            onItemClick = { dica ->
                val dialog = DetalhesDicaDialog.newInstance(dica.titulo, dica.descricao)
                dialog.show(supportFragmentManager, "DetalhesDicaDialog")
            }
        )
        recyclerView.adapter = dicaAdapter

        val editTextTitulo = findViewById<EditText>(R.id.editTextTitulo)
        val editTextDescricao = findViewById<EditText>(R.id.editTextDescricao)
        val buttonAddDica = findViewById<Button>(R.id.buttonAddDica)



        buttonAddDica.setOnClickListener {
            val titulo = editTextTitulo.text.toString()
            val descricao = editTextDescricao.text.toString()

            if (titulo.isEmpty()) {
                editTextTitulo.error = "Digite o título da dica"
                return@setOnClickListener
            }
            if (descricao.isEmpty()) {
                editTextDescricao.error = "Digite a descrição da dica"
                return@setOnClickListener
            }
            viewModel.addDica(titulo, descricao)
            editTextTitulo.text.clear()
            editTextDescricao.text.clear()
        }
        val viewModelFactory = DicaViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DicaViewModel::class.java)

        viewModel.dicasLiveData.observe(this) { dicas ->
            dicaAdapter.updateDicas(dicas)
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    dicaAdapter.filter(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    dicaAdapter.filter(newText)
                }
                return true
            }
        })
    }
}
