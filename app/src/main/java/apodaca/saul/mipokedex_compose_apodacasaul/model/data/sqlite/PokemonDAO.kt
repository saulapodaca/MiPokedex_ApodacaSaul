package apodaca.saul.mipokedex_compose_apodacasaul.model.data.sqlite

import apodaca.saul.mipokedex_compose_apodacasaul.model.data.interfaces.IPokemonDAO
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonDAO(private val helper: DatabaseHelper) : IPokemonDAO {
    val mapper = PokemonMapper()
    override suspend fun insertPokemon(pokemon: Pokemon) {
        withContext(Dispatchers.IO){
            val db = helper.writableDatabase
            db.insert(Contract.Pokemons.TABLE_NAME, null,
                mapper.getContentValues(pokemon))
            db.close()
        }
    }

    override suspend fun getAllPokemons(): List<Pokemon> {
        return withContext(Dispatchers.IO){
            val db = helper.readableDatabase
            val query =  "SELECT * FROM ${Contract.Pokemons.TABLE_NAME}"
            val cursor = db.rawQuery(query, null)
            cursor.use {
                c ->
                buildList {
                    while(c.moveToNext()){
                        add(mapper.getPokemon(cursor))
                    }
                }
            }
        }
    }

    override suspend fun getPokemonById(id: Integer): Pokemon? {
        return withContext(Dispatchers.IO){
            val db = helper.readableDatabase
            val query = "SELECT * FROM ${Contract.Pokemons.TABLE_NAME} WHERE ${Contract.Pokemons.COLUMN_ID} = ?"
            val cursor = db.rawQuery(query, arrayOf(id.toString()))
            cursor.use{
                c->
                if(c.moveToFirst()){
                    mapper.getPokemon(c)
                } else {
                    null
                }
            }
        }
    }

    override suspend fun getFavoritePokemons(): List<Pokemon> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonsByType(type: String): List<Pokemon> {
        return withContext(Dispatchers.IO){
            val db = helper.readableDatabase
            val query = "SELECT * FROM ${Contract.Pokemons.TABLE_NAME} WHERE ${Contract.Pokemons.COLUMN_NAME_TYPE} LIKE ?"
            val cursor = db.rawQuery(query, arrayOf("%$type%"))
            cursor.use{
                c->
                buildList {
                    while(c.moveToNext()){
                        add(mapper.getPokemon(cursor))
                    }
                }
            }
        }
    }

    override suspend fun updatePokemon(pokemon: Pokemon) {
        withContext(Dispatchers.IO){
            val db = helper.writableDatabase
            val values = mapper.getContentValues(pokemon)

            val selection = "${Contract.Pokemons.COLUMN_ID} = ?"
            val selectionArgs = arrayOf(pokemon.id.toString())

            db.update(
                Contract.Pokemons.TABLE_NAME,
                values,
                selection,
                selectionArgs
                )
            db.close()
        }
    }

    override suspend fun deletePokemon(id: Integer) {
        withContext(Dispatchers.IO){
            val db = helper.writableDatabase

            val selection = "${Contract.Pokemons.COLUMN_ID} = ?"
            val selectionArgs = arrayOf(id.toString())

            db.delete(
                Contract.Pokemons.TABLE_NAME,
                selection,
                selectionArgs
            )
            db.close()
        }
    }
}