package apodaca.saul.mipokedex_compose_apodacasaul.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import apodaca.saul.mipokedex_compose_apodacasaul.data.pokemonList
import apodaca.saul.mipokedex_compose_apodacasaul.domain.Pokemon

@Composable
fun MenuPokedex (pokemonList: List<Pokemon>, innerPadding: PaddingValues){
    LazyColumn() {
        items(pokemonList){ pokemon ->
            PokemonRow(pokemon)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewMenuPokedex(){
    MenuPokedex(pokemonList, innerPadding = )
}