package br.edu.up.app.ui.produto

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.edu.up.app.data.Fotos
import br.edu.up.app.data.Produto
import br.edu.up.app.databinding.FragmentItemProdutoBinding

import br.edu.up.app.ui.produto.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ProdutosAdapter(
    private val produtos: List<Produto>
) : RecyclerView.Adapter<ProdutosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemProdutoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
            val idFoto = Fotos.get(produto.foto)
            holder.imgFoto.setImageResource(idFoto)
            holder.txtNomeProd.text = produto.nome
            holder.txtPreco.text = "R$ " + String.format("%.2f", produto.preco).replace(".", ",")
//        holder.idView.text = item.id
//        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = produtos.size

    inner class ViewHolder(binding: FragmentItemProdutoBinding) :
        RecyclerView.ViewHolder(binding.root) {
            val imgFoto: ImageView = binding.imgFoto
            val txtNomeProd: TextView = binding.txtNomeProd
            val txtPreco: TextView = binding.txtPreco
        }
}