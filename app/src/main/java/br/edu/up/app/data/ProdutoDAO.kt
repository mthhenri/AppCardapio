package br.edu.up.app.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDAO {

    @Query("SELECT * FROM produtos")
    fun listar(): Flow<List<Produto>>

    @Insert
    suspend fun cadastrar(produto: Produto)

    @Update
    suspend fun atualizar(produto: Produto)

    @Delete
    suspend fun deletar(produto: Produto)

    @Query("DELETE FROM produtos WHERE id = :id")
    suspend fun deletar(id: Int)

    @Query("DELETE FROM produtos")
    suspend fun deletarCardapio()

}