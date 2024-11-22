package com.example.ecodicas.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.ecodicas.R

class DetalhesDicaDialog : DialogFragment() {

    companion object {
        const val ARG_TITULO = "titulo"
        const val ARG_DESCRICAO = "descricao"

        fun newInstance(titulo: String, descricao: String): DetalhesDicaDialog {
            val dialog = DetalhesDicaDialog()
            val args = Bundle()
            args.putString(ARG_TITULO, titulo)
            args.putString(ARG_DESCRICAO, descricao)
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_detalhes_dica, container, false)

        val tituloTextView = view.findViewById<TextView>(R.id.detalhesTitulo)
        val descricaoTextView = view.findViewById<TextView>(R.id.detalhesDescricao)
        val fecharButton = view.findViewById<Button>(R.id.fecharButton)

        val titulo = arguments?.getString(ARG_TITULO)
        val descricao = arguments?.getString(ARG_DESCRICAO)

        tituloTextView.text = titulo
        descricaoTextView.text = descricao

        fecharButton.setOnClickListener {
            dismiss()
        }

        return view
    }
}
