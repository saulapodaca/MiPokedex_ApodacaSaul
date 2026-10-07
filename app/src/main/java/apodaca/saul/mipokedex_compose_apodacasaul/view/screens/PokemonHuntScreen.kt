package apodaca.saul.mipokedex_compose_apodacasaul.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import apodaca.saul.mipokedex_compose_apodacasaul.viewmodel.PokemonViewModel

@Composable
fun PokemonHuntScreen(innerPaddingValues: PaddingValues, viewModel: PokemonViewModel = viewModel()){
    Column(Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button({}) {
            Text("Buscar pokemon en la hierva")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonHuntScreenPreview(){
    PokemonHuntScreen(PaddingValues(15.dp))
}