package apodaca.saul.mipokedex_compose_apodacasaul.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import apodaca.saul.mipokedex_compose_apodacasaul.model.data.pokemonList
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.Pokemon

class PokemonViewModel: ViewModel(){

    var wildPokemon by mutableStateOf<Pokemon?>(null)

    fun capturePokemon(){
    }

    fun getPrevPokemon(actualPokemon: Number): Pokemon?{
        val currentIndex = pokemonList.indexOfFirst { it.number == actualPokemon }
        return if (currentIndex>0) pokemonList[currentIndex-1] else null
    }

    fun getNextPokemon(actualPokemon: Number): Pokemon?{
        val currentIndex = pokemonList.indexOfFirst { it.number == actualPokemon }
        return if (currentIndex in 0 until pokemonList.lastIndex) pokemonList[currentIndex+1] else null
    }

    fun getFavoritePokemon(): List<Pokemon>{
        return pokemonList.filter { it.favorite }
    }

    fun getPokemonByNumber(pokemonNumber: Number): Pokemon{
        return pokemonList.first { it.number == pokemonNumber }
    }

    fun getPokemonList(): List<Pokemon>{
        return pokemonList
    }

    fun toggleFavorite(pokemonNumber: Number): Boolean{
        val foundPokemon = pokemonList.find { it.number == pokemonNumber }
        foundPokemon?.let {
            it.favorite = !it.favorite
            return it.favorite
        }
        return false
    }
}