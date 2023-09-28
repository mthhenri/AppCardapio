package br.edu.up.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Produto::class], version = 1, exportSchema = false)
abstract class BancoSQLite : RoomDatabase() {

    abstract fun produtoDAO() : ProdutoDAO

    companion object{

        @Volatile
        private var instance : BancoSQLite? = null

        fun get(context: Context) : BancoSQLite{
            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    BancoSQLite::class.java,
                    "database.db"
                ).build()
            }
            return instance as BancoSQLite
        }
    }
}