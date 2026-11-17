package apodaca.saul.mipokedex_compose_apodacasaul.model.data.sqlite

import apodaca.saul.mipokedex_compose_apodacasaul.model.data.interfaces.IPokemonDAO
import apodaca.saul.mipokedex_compose_apodacasaul.model.data.interfaces.IPokemonRepository
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.Pokemon

class PokemonSQLRepository(private val dao: IPokemonDAO) : IPokemonRepository {
    override suspend fun addPokemon(pokemon: Pokemon) {
        dao.insertPokemon(pokemon)
    }

    override suspend fun getPokemons(): List<Pokemon> {
        return dao.getAllPokemons()
    }

    override suspend fun getFavoritePokemons(): List<Pokemon> {
        return dao.getFavoritePokemons()
    }

    override suspend fun getPokemonsByType(type: String): List<Pokemon> {
        return dao.getPokemonsByType(String())
    }

    override suspend fun updatePokemon(pokemon: Pokemon) {
        dao.updatePokemon(pokemon)
    }

    override suspend fun deletePokemon(id: Integer) {
        dao.deletePokemon(id)
    }
}