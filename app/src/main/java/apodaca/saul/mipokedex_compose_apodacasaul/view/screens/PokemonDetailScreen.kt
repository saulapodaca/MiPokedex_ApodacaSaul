package apodaca.saul.mipokedex_compose_apodacasaul.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apodaca.saul.mipokedex_compose_apodacasaul.R
import apodaca.saul.mipokedex_compose_apodacasaul.model.data.bulbasaur
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.Pokemon
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Favorite
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Gray
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.OffWhite
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Red
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.White
import apodaca.saul.mipokedex_compose_apodacasaul.utilities.getColorType

@Composable
fun PokemonDetailScreen(innerPadding: PaddingValues, pokemon: Pokemon, onNavigateToDetail: (id:Int) -> Unit) {

    var isFavorite by remember(pokemon.number) { mutableStateOf(pokemon.favorite) }
    val prevPokemon = PokemonViewModel.getPrevPokemon(pokemon.number)
    val nextPokemon = PokemonViewModel.getNextPokemon(pokemon.number)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(getColorType(pokemon.type).first)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_pokeball),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.TopEnd)
                .offset(x = 100.dp, y = 40.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = pokemon.name,
                    color = OffWhite,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "#${pokemon.number}",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Serif
                )
            }
            IconButton(
                onClick = {
                    isFavorite = PokemonViewModel.toggleFavorite(pokemon.number)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star_favorite),
                    tint = if (isFavorite) Favorite else Color.White.copy(alpha = 0.6f),
                    contentDescription = if (isFavorite) "Quitar de favoritos" else "Agregar a favoritos",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .align(Alignment.BottomCenter)
            //Es para hacer la forma de la caja visible y darle las esquinas redondeadas
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .background(OffWhite)
            .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Es para el espacio para la imagen del pokemon
                Spacer(modifier = Modifier.height(70.dp))

                //las pastillas de los tipos TODO HAY QUE MODIFICAR ESTO PARA QUE SEA
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    TypePill(text = pokemon.type, getColorType(pokemon.type).first)
                }

                Spacer(modifier = Modifier.height(20.dp))

                //La parte de las habilidades
                Row(modifier = Modifier.fillMaxWidth()
                    .offset(y = (25).dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        StatItem(
                            label = stringResource(R.string.label_height),
                            value = pokemon.height.toString() + " m"
                        )
                        StatItem(
                            label = stringResource(R.string.label_weight),
                            value = pokemon.weight.toString() + " kg"
                        )
                    }
                    Column {
                        StatItem(
                            label = stringResource(R.string.label_ability),
                            value = pokemon.ability
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Descripción
                Text(
                    text = pokemon.description,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Gray,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                        .offset(y = (50).dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                //Barra de las flechas con pokemon
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = prevPokemon != null) {
                                prevPokemon?.let { onNavigateToDetail(it.number.toInt()) }
                            }
                    ) {
                        if (prevPokemon != null) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_arrow_prev),
                                contentDescription = "Anterior",
                                modifier = Modifier.size(36.dp)
                            )
                            PokemonThumb(
                                imageRes = prevPokemon.image,
                                label = "#${prevPokemon.number}"
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable(enabled = nextPokemon != null) {
                                nextPokemon?.let { onNavigateToDetail(it.number.toInt()) }
                            }
                    ) {
                        if (nextPokemon != null) {
                            PokemonThumb(
                                imageRes = nextPokemon.image,
                                label = "#${nextPokemon.number}"
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_arrow_next),
                                contentDescription = "Siguiente",
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }
                }
            }
        }
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = "${pokemon.name} image",
            modifier = Modifier
                .size(270.dp)
                .align(Alignment.Center)
                .offset(y = (-200).dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailScreenPreview() {
    PokemonDetailScreen(PaddingValues(0.dp, 0.dp), bulbasaur, {})
}

//Es la pastilla de los tipos
@Composable
private fun TypePill(text: String, color: Color) {
    Text(
        text = text,
        color = White,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(color)
            .padding(horizontal = 20.dp, vertical = 4.dp)
    )
}

//Este es para poner la etiqueta y la estadística
@Composable
private fun StatItem(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$label: ",
            color = Red,
            fontWeight = FontWeight.Black,
            fontSize = 20.sp
        )
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}

@Composable
private fun PokemonThumb(imageRes: Int, label: String) {
    if (imageRes != 0) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        }
    }
}