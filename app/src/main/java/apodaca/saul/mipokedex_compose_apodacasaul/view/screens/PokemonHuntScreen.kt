package apodaca.saul.mipokedex_compose_apodacasaul.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.Pokemon
import apodaca.saul.mipokedex_compose_apodacasaul.viewmodel.PokemonViewModel

@Composable
fun PokemonHuntScreen(innerPadding: PaddingValues, viewModel: PokemonViewModel = viewModel()){
    Column(Modifier.fillMaxWidth()
        .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button({viewModel.searchPokemon()}) {
            Text("Buscar pokemon en la hierva")
        }

        Spacer(Modifier.size(18.dp))

        viewModel.wildPokemon?.let {
            pokemon ->
            Text("Apareció un ${pokemon.name} salvaje")
            Spacer(Modifier.size(15.dp))
            Image(
                painter = painterResource(pokemon.image),
                contentDescription = "${pokemon.name} imagen"
            )
            Button(onClick = {}) {
                Text("Capturar a ${pokemon.name}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonHuntScreenPreview(){
    PokemonHuntScreen(PaddingValues(15.dp))
}