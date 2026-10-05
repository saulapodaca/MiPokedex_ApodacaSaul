package apodaca.saul.mipokedex_compose_apodacasaul.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apodaca.saul.mipokedex_compose_apodacasaul.data.bulbasaur
import apodaca.saul.mipokedex_compose_apodacasaul.data.getFavoritePokemons
import apodaca.saul.mipokedex_compose_apodacasaul.utilities.getColorType

@Composable
fun NumberChip(
    text: String,
    modifier: Modifier = Modifier,
    colors: Pair<Color, Color>
) {
    Row(modifier = modifier
        .size(30.dp)
        .padding(5.dp)
        .background(color = colors.first, shape = CircleShape),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text,
            fontSize = 12.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Black,
            color = colors.second)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNumberChip() {
    NumberChip("888", Modifier, getColorType("grass"))
}