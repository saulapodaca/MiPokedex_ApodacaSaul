package apodaca.saul.mipokedex_compose_apodacasaul.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import apodaca.saul.mipokedex_compose_apodacasaul.components.FavoritesRow
import apodaca.saul.mipokedex_compose_apodacasaul.components.PokedexGrid
import apodaca.saul.mipokedex_compose_apodacasaul.data.favoriteList
import apodaca.saul.mipokedex_compose_apodacasaul.data.pokemonList

@Composable
fun MenuPokedexScreen(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mis Favoritos",
            style = MaterialTheme.typography.titleLarge
        )
        FavoritesRow(
            favoriteList = favoriteList
        )
        Text(
            text = "Todos mis pokemones",
            style = MaterialTheme.typography.titleLarge
        )
        PokedexGrid(pokemonList)
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPokedexScreenPreview() {
    MenuPokedexScreen(PaddingValues())
}

