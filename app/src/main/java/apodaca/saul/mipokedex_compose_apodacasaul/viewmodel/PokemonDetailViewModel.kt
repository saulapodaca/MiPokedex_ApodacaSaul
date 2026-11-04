package apodaca.saul.mipokedex_compose_apodacasaul.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import apodaca.saul.mipokedex_compose_apodacasaul.model.data.CompanionPreferences
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.Pokemon
import kotlinx.coroutines.launch

class PokemonDetailViewModel (
    private val companionDataStore: CompanionPreferences
): ViewModel(){
    fun saveCompanion(pokemon: Pokemon){
        viewModelScope.launch {
            companionDataStore.saveCompanion(pokemon)
        }
    }
}