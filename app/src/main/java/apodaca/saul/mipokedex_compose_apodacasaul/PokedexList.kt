package apodaca.saul.mipokedex_compose_apodacasaul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import apodaca.saul.mipokedex_compose_apodacasaul.components.MenuPokedex
import apodaca.saul.mipokedex_compose_apodacasaul.data.pokemonList
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.MiPokedex_Compose_ApodacaSaulTheme

class PokedexList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiPokedex_Compose_ApodacaSaulTheme {
                Scaffold (modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MenuPokedex(
                        pokemonList = pokemonList,
                        innerPadding = PaddingValues())
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiPokedex_Compose_ApodacaSaulTheme {
        MenuPokedex(pokemonList, innerPadding = PaddingValues())
    }
}