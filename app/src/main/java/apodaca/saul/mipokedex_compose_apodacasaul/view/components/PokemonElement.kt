package apodaca.saul.mipokedex_compose_apodacasaul.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apodaca.saul.mipokedex_compose_apodacasaul.model.data.bulbasaur
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.Pokemon
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.OffWhite
import apodaca.saul.mipokedex_compose_apodacasaul.utilities.getColorType

@Composable
fun PokemonRow(pokemon: Pokemon) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painterResource(pokemon.image),
            contentDescription = "${pokemon.name} image",
            Modifier
                .width(80.dp)
                .padding(10.dp)
        )
        Column(
            Modifier.fillMaxWidth(0.70f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(pokemon.name, style = MaterialTheme.typography.labelLarge)
            Text(pokemon.description, fontSize = 10.sp)
            Row(Modifier.fillMaxWidth(0.85f), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Height: ${pokemon.height}", style = MaterialTheme.typography.labelMedium)
                Text("Weight: ${pokemon.weight}", style = MaterialTheme.typography.labelMedium)
            }
        }
        NumberChip(
            text = pokemon.number.toString(),
            modifier = Modifier,
            getColorType(pokemon.type)
        )
    }
}

@Composable
fun FavoritePokemon(pokemon: Pokemon, onNavigateToDetail: (id: Int) -> Unit) {
    val colors = getColorType(pokemon.type)
    Column(
        modifier = Modifier
            .padding(vertical = 15.dp)
            .clickable(true, onClick = { onNavigateToDetail(pokemon.number as Int) }),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Box(
                modifier = Modifier
                    .border(
                        border = BorderStroke(
                            width = 5.dp,
                            brush = Brush.sweepGradient(
                                colors = listOf(
                                    colors.first,
                                    OffWhite,
                                    colors.first,
                                    OffWhite,
                                    colors.first
                                )
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = pokemon.image),
                    contentDescription = "${pokemon.name} image",
                    modifier = Modifier
                        .width(100.dp)
                        .padding(5.dp)
                )
            }
            NumberChip(
                text = pokemon.number.toString(),
                modifier = Modifier.align(Alignment.BottomEnd)
                    .offset(5.dp, 5.dp),
                getColorType(pokemon.type),
            )
        }
        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun PokemonCell(pokemon: Pokemon) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Image(
                painter = painterResource(id = pokemon.image),
                contentDescription = "${pokemon.name} image",
                modifier = Modifier
                    .size(150.dp)
                    .padding(10.dp)
            )
            NumberChip(
                text = pokemon.number.toString(),
                modifier = Modifier.align(Alignment.TopEnd),
                colors = getColorType(pokemon.type)
            )
        }
        Text(
            text = pokemon.name,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonElementPreview() {
    PokemonRow(bulbasaur)
}

@Preview(showBackground = true)
@Composable
fun PokemonFavoritePreview() {
    FavoritePokemon(bulbasaur, {})
}

@Preview(showBackground = true)
@Composable
fun PokemonCellPreview() {
    PokemonCell(bulbasaur)
}


