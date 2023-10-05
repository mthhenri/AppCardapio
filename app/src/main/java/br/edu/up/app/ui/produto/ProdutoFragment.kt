package br.edu.up.app.ui.produto

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.edu.up.app.R
import br.edu.up.app.data.Produto
import br.edu.up.app.databinding.FragmentProdutoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProdutoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel : ProdutoViewModel by activityViewModels()
        val binding = FragmentProdutoBinding.inflate(layoutInflater)

        binding.btnSalvar.setOnClickListener(){
            val produtoSalvar = Produto(
                0,
                binding.inputNome.text.toString(),
                binding.inputDescricao.text.toString(),
                binding.inputPreco.text.toString().toDouble(),
                binding.inputPeso.text.toString().toInt(),
                binding.inputFoto.text.toString(),
                0
            )
            viewModel.produto = produtoSalvar
            viewModel.salvar()
            findNavController().popBackStack()
        }

        return  binding.root
//        return inflater.inflate(R.layout.fragment_produto, container, false)
    }

}