import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecodicas.R
import com.example.ecodicas.model.DicaEntity

class DicaAdapter(
    private val onDeleteClick: (DicaEntity) -> Unit,
    private val onItemClick: (DicaEntity) -> Unit
) : RecyclerView.Adapter<DicaAdapter.DicaViewHolder>() {

    private var dicas: List<DicaEntity> = listOf()
    private var dicasFiltradas: List<DicaEntity> = listOf()

    init {
        dicasFiltradas = dicas
    }

    inner class DicaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descricaoTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DicaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dica, parent, false)
        return DicaViewHolder(view)
    }

    override fun onBindViewHolder(holder: DicaViewHolder, position: Int) {
        val dica = dicasFiltradas[position]
        holder.tituloTextView.text = dica.titulo
        holder.descricaoTextView.text = dica.descricao

        holder.deleteButton.setOnClickListener {
            onDeleteClick(dica)
        }

        holder.itemView.setOnClickListener {
            onItemClick(dica)
        }
    }

    override fun getItemCount(): Int = dicasFiltradas.size

    fun updateDicas(newDicas: List<DicaEntity>) {
        dicas = newDicas
        dicasFiltradas = newDicas
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        dicasFiltradas = if (query.isEmpty()) {
            dicas
        } else {
            dicas.filter {
                it.titulo.contains(query, ignoreCase = true) ||
                        it.descricao.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}
