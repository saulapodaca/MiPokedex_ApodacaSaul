package apodaca.saul.mipokedex_compose_apodacasaul.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.DarkGray
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Green
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Psych
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Typography
import apodaca.saul.mipokedex_compose_apodacasaul.view.components.PokemonRow
import apodaca.saul.mipokedex_compose_apodacasaul.viewmodel.PokemonViewModel

@Composable
fun PokemonHuntScreen(innerPadding: PaddingValues, viewModel: PokemonViewModel = viewModel(), onNavigateToDetail: (id:Int) -> Unit){
    Column(Modifier.fillMaxWidth()
        .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button({viewModel.searchPokemon()}) {
            Text("Buscar pokemon en la hierva")
        }

        Spacer(Modifier.size(18.dp))

        viewModel.wildPokemon?.let {
            pokemon ->
            Image(
                painter = painterResource(pokemon.image),
                contentDescription = "${pokemon.name} image"
            )
            Text("Apareció un ${pokemon.name} salvaje", style = Typography.labelLarge, color = DarkGray)
            Spacer(Modifier.size(15.dp))

            Button(onClick = {viewModel.capturePokemon()}) {
                Text("Capturar a ${pokemon.name}")
            }
        }

        if(viewModel.gonePokemon){
            Text("El pokemón se ha escapado🤣🤣", style = Typography.labelLarge, color = Color.Magenta)
        }

        if (viewModel.isPokemonCaptured){
            Text("¡Has capturado al pokemon!👌", style = Typography.labelLarge, color = Green)
        }

        Spacer(Modifier.size(20.dp))

        Text("Pokemones Capturados", style = Typography.titleLarge, color = Psych)

        LazyColumn() {
            items(viewModel.capturedPokemon){
                pokemon ->
                PokemonRow(pokemon,onNavigateToDetail)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonHuntScreenPreview(){
    PokemonHuntScreen(PaddingValues(15.dp), PokemonViewModel, {})
}