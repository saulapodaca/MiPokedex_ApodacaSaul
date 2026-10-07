package apodaca.saul.mipokedex_compose_apodacasaul.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.Pokemon

class PokemonViewModel: ViewModel(){

    var wildPokemon by mutableStateOf<Pokemon?>(null)

    fun capturePokemon(){
    }
}