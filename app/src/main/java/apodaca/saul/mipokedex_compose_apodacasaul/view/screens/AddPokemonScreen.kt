package apodaca.saul.mipokedex_compose_apodacasaul.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.pokemonTypes
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Typography
import apodaca.saul.mipokedex_compose_apodacasaul.viewmodel.AddPokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPokemonScreen(
    innerPadding: PaddingValues,
    addPokemonViewModel: AddPokemonViewModel = viewModel()
) {
    // 1. Colectamos el flujo de pokemones como estado
    val pokemonList by addPokemonViewModel.pokemons.collectAsStateWithLifecycle()

    val number = rememberTextFieldState()
    val name = rememberTextFieldState()
    val type = rememberTextFieldState(pokemonTypes[0])
    val description = rememberTextFieldState()
    val height = rememberTextFieldState()
    val weight = rememberTextFieldState()
    val ability = rememberTextFieldState()
    var openType by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Agregar pokemon",
            style = Typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            state = number,
            label = { Text("Número del pokemon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            state = name,
            label = { Text("Nombre del pokemon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenuBox(
            expanded = openType,
            onExpandedChange = { openType = it }
        ) {
            OutlinedTextField(
                readOnly = true,
                label = { Text("Selecciona un tipo") },
                state = type,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = openType) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
            )
            ExposedDropdownMenu(
                expanded = openType,
                onDismissRequest = { openType = false }
            ) {
                pokemonTypes.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            // Actualizamos el mismo state 'type' que usa el OutlinedTextField
                            type.setTextAndPlaceCursorAtEnd(selectedOption)
                            openType = false
                        },
                        text = { Text(selectedOption) }
                    )
                }
            }
        }
        TextField(
            state = description,
            label = { Text("Descripción del pokemon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            state = height,
            label = { Text("Altura del pokemon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            state = weight,
            label = { Text("Peso del pokemon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            state = ability,
            label = { Text("Habilidad del pokemon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                addPokemonViewModel.savePokemon(
                    number = number.text.toString().toIntOrNull() ?: 0,
                    name = name.text.toString(),
                    type = type.text.toString(),
                    description = description.text.toString(),
                    height = height.text.toString().toFloatOrNull() ?: 0f,
                    weight = weight.text.toString().toFloatOrNull() ?: 0f,
                    ability = ability.text.toString()
                )
            }
        ) {
            Text(
                text = "Guardar Pokemon",
                fontSize = 20.sp
            )
        }

        // 2. LazyColumn para mostrar los pokemones guardados
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pokemonList) { pokemon ->
                Text(
                    text = pokemon.toString(),
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}
@Preview (showBackground = true)
@Composable
fun AddPokemonScreenPreview(){
    AddPokemonScreen(PaddingValues(20.dp))
}