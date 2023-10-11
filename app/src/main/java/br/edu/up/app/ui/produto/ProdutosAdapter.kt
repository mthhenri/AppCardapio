package br.edu.up.app.ui.produto

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import br.edu.up.app.data.Fotos
import br.edu.up.app.data.Produto
import br.edu.up.app.databinding.FragmentItemProdutoBinding

import br.edu.up.app.ui.produto.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ProdutosAdapter(
    private val produtos: List<Produto>,
    val viewModel: ProdutoViewModel
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

        val itemProduto = produtos[position]
        val idFoto = Fotos.get(itemProduto.foto)

        holder.imgFoto.setImageResource(idFoto)
        holder.txtNomeProd.text = itemProduto.nome
        holder.txtPreco.text = "R$ " + String.format("%.2f", itemProduto.preco).replace(".", ",")
        holder.txtDescricao.text = itemProduto.descricao

        holder.itemView.setOnClickListener(){view ->
            viewModel.editar(itemProduto)
            val action = ProdutosFragmentDirections.actionNavHomeToProdutoFragment()
            view.findNavController().navigate(action)
        }

        holder.itemView.setOnLongClickListener{view ->
            AlertDialog.Builder(view.context)
                .setMessage("Remover item do cardápio?")
                .setPositiveButton("Sim") {dialog, id ->
                    viewModel.excluir(itemProduto)
                }
                .setNegativeButton("Não") {dialog, id ->

                }
                .create()
                .show()
            true
        }
//        holder.idView.text = item.id
//        holder.contentView.text = item.contentpainel
    }

    override fun getItemCount(): Int = produtos.size

    inner class ViewHolder(binding: FragmentItemProdutoBinding) :
        RecyclerView.ViewHolder(binding.root) {
            val imgFoto: ImageView = binding.imgFoto
            val txtNomeProd: TextView = binding.txtNomeProd
            val txtPreco: TextView = binding.txtPreco
            val txtDescricao: TextView = binding.txtDescricao
        }
}