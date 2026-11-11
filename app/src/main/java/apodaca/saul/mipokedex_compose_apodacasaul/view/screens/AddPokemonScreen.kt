package apodaca.saul.mipokedex_compose_apodacasaul.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
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
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.pokemonTypes
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPokemonScreen(
    innerPadding: PaddingValues
){

    var number = remember { TextFieldState() }
    var name = remember {TextFieldState()}
    val textFieldState = rememberTextFieldState(pokemonTypes[0])
    var type = remember { TextFieldState() }
    var description = remember { TextFieldState() }
    var height = remember {TextFieldState() }
    var openType by remember {mutableStateOf(false)}

    Column(
        Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            "Agregar pokemon",
            style = Typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
            )

        Spacer(Modifier.size(20.dp))
        TextField(
            state = number,
            label = {
                Text("Número del pokemon")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            state = name,
            label = {
                Text("Nombre del pokemon")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        ExposedDropdownMenuBox(
            expanded = openType,
            {openType = it}
        ) {
            OutlinedTextField(
                readOnly = true,
                label = {Text("Selecciona un tipo")},
                state = type,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = openType) },
                modifier =  Modifier
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
            )
            ExposedDropdownMenu(expanded = openType,
                onDismissRequest = {openType = false}
                ) {
                pokemonTypes.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            textFieldState.setTextAndPlaceCursorAtEnd(selectedOption)
                            openType = false
                        },
                        text = {Text(selectedOption)}
                    )
                }
            }
        }
        TextField(
            state = description,
            label = {

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        TextField(
            state = height,
            label = {

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
    }
}

@Preview (showBackground = true)
@Composable
fun AddPokemonScreenPreview(){
    AddPokemonScreen(PaddingValues(20.dp))
}