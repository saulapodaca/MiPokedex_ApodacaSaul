package apodaca.saul.mipokedex_compose_apodacasaul.view.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ImagePicker(
    currentUri: Uri,
    onUriChange: (uri: Uri?) -> Unit,
    pickerModifier: Modifier? = null,
    previewModifier: Modifier? = null
){
    val selectedModifier = pickerModifier?: Modifier
    val imagePreviewModifier = previewModifier?: Modifier

    val context = LocalContext.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri -> onUriChange(uri)}
    )

    Button({
        imagePickerLauncher.
        launch(PickVisualMediaRequest(
            ActivityResultContracts.PickVisualMedia.ImageOnly
        ))
    },
        modifier = selectedModifier
    ) {
        Text("Seleccciona una imagen")
    }
}

@Preview
@Composable
fun ImagePickerPreview(){

}