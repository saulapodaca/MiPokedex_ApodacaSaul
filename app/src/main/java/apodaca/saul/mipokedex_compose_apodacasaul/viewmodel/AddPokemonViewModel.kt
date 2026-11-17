package apodaca.saul.mipokedex_compose_apodacasaul.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import apodaca.saul.mipokedex_compose_apodacasaul.model.data.interfaces.IPokemonRepository
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddPokemonViewModel(private val repository: IPokemonRepository) : ViewModel() {

    private val pokemonsMutable = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons: StateFlow<List<Pokemon>> = pokemonsMutable.asStateFlow()

    var pokemonSaved by mutableStateOf<Boolean>(false)
        private set

    fun loadPokemons(){
        viewModelScope.launch {
            pokemonsMutable.value = repository.getPokemons()
        }
    }

    fun savePokemon(
        name:String, type:String, number:Int, description:String,
        height:Float, weight:Float, ability:String
    ){
        val savingPokemon = Pokemon(
            name = name,
            type= type,
            number = number,
            description = description,
            height = height,
            weight = weight,
            ability = ability,
            favorite = false
        )

        viewModelScope.launch {
            repository.addPokemon(savingPokemon)
            pokemonSaved = true
            loadPokemons()
        }
    }

}