package apodaca.saul.mipokedex_compose_apodacasaul.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import apodaca.saul.mipokedex_compose_apodacasaul.domain.Pokemon

@Composable
fun PokemonDetailScreen(innerPadding: PaddingValues, pokemon: Pokemon){
    Column() {
        Text(pokemon.name)
        Image(painterResource(pokemon.image), contentDescription = "${pokemon.name} image")
    }
}