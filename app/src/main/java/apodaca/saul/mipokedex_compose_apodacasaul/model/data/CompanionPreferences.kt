package apodaca.saul.mipokedex_compose_apodacasaul.model.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.Pokemon
import apodaca.saul.mipokedex_compose_apodacasaul.model.preferences.PokemonSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.serializer

val Context.dataStore: DataStore<Pokemon> by dataStore(
    fileName = "pokemon.json",
    serializer = PokemonSerializer,
    scope = CoroutineScope(
        Dispatchers.IO
    )
)
class CompanionPreferences(
    private val context: Context
) {

    suspend fun saveCompanion(companion: Pokemon){
        context.dataStore.updateData {
            pokemon -> companion
        }
    }

    fun pokemonCompanion(): Flow<Pokemon> =
        context.dataStore.data.map {
        pokemon -> pokemon
    }
}