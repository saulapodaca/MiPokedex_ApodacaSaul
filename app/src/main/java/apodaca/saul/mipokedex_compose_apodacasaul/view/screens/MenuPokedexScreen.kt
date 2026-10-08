package apodaca.saul.mipokedex_compose_apodacasaul.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import apodaca.saul.mipokedex_compose_apodacasaul.R
import apodaca.saul.mipokedex_compose_apodacasaul.view.components.FavoritesRow
import apodaca.saul.mipokedex_compose_apodacasaul.view.components.PokedexGrid
import apodaca.saul.mipokedex_compose_apodacasaul.viewmodel.PokemonViewModel
import apodaca.saul.mipokedex_compose_apodacasaul.model.data.pokemonList
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Blue
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Green
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.LightBlue
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.LightGreen
import apodaca.saul.mipokedex_compose_apodacasaul.view.components.MenuPokedex

val PokemonViewModel = PokemonViewModel()
@Composable
fun MenuPokedexScreen(innerPadding: PaddingValues, onNavigateToDetail: (id:Int)-> Unit) {
    var grid by remember{ mutableStateOf(false)}

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
            favoriteList = PokemonViewModel.getFavoritePokemon(),
            onNavigateToDetail
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Todos mis pokemones",
                style = MaterialTheme.typography.titleLarge
            )
            Switch(
                checked = grid,
                onCheckedChange = { grid = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Green,
                    checkedTrackColor = LightGreen,
                    uncheckedThumbColor = Blue,
                    uncheckedTrackColor = LightBlue,
                    uncheckedBorderColor = Color.Transparent
                ),
                thumbContent = if (grid) {
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_view_grid),
                            contentDescription = "grid icon",
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                } else {
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_view_list),
                            contentDescription = "list icon",
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                })
        }
        Spacer(modifier = Modifier.height(8.dp))

        if (grid) {
            PokedexGrid(
                pokemonList = pokemonList,
                onNavigateToDetail = onNavigateToDetail
            )
        } else {
            MenuPokedex(
                pokemonList = pokemonList,
                onNavigateToDetail
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuPokedexScreenPreview() {
    MenuPokedexScreen(PaddingValues(10.dp, 15.dp), {})
}

