package apodaca.saul.mipokedex_compose_apodacasaul.navigation

import apodaca.saul.mipokedex_compose_apodacasaul.domain.Pokemon
import kotlinx.serialization.Serializable

@Serializable
object PokemonList;

@Serializable
data class PokemonDetail(val pokemon: Int)