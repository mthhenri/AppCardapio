package br.edu.up.app.ui.produto

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.edu.up.app.data.BancoSQLite
import br.edu.up.app.data.ProdutoRepository
import br.edu.up.app.databinding.FragmentListaProdutosBinding
import kotlinx.coroutines.launch

class ProdutosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {

        val banco = BancoSQLite.get(requireContext())
        val repository = ProdutoRepository(banco.produtoDAO())
        val viewModel = ProdutoViewModel(repository)
        val binding = FragmentListaProdutosBinding.inflate(layoutInflater)
        val recyclerView = binding.root

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.produtos.collect{
                    produtos -> recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = ProdutosAdapter(produtos)
                }
            }
        }
        return binding.root
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ProdutosFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}