package ec.edu.puce.githubclient.ui.screems

import android.text.Layout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ec.edu.puce.githubclient.models.Repository
import ec.edu.puce.githubclient.models.RepositoryPayload
import ec.edu.puce.githubclient.ui.theme.GithubClientTheme
import ec.edu.puce.githubclient.viewmodels.RepoFormViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoForm(
    repoToEdit: Repository? = null,
    onBackClick:() -> Unit = {},
    onSaveSuccess:() -> Unit = {},
    viewModel: RepoFormViewModel= viewModel()
){

    val isLoading by viewModel.isLoading.collectAsState()
    val isSuccess by viewModel.isSuccess.collectAsState()
    val errMsg by viewModel.errMsg.collectAsState()
    var name by remember { mutableStateOf(repoToEdit?.name ?: "") }
    var description by remember { mutableStateOf(repoToEdit?.description ?: "") }

    LaunchedEffect(key1 = isSuccess) {
        if (isSuccess){
            onSaveSuccess()
            viewModel.resetSucess()
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Formulario de repositorio") },
                navigationIcon={
                    IconButton(onClick = {onBackClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            "Regresar",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )

            )

        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ){
            if(isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align (Alignment.CenterHorizontally)
                )
            }else{

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Nombre del repositorio") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true

                )
                Spacer(modifier = Modifier.height(height = 12.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = "Descripcion del repositorio") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 5

                )

                if (!errMsg.isNullOrBlank()) {
                    Text(
                        text = errMsg!!, // Ahora es seguro porque ya verificaste que no es nulo
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.height(height = 48.dp))
                Button(
                    onClick = {
                        if (repoToEdit != null) {

                            // Lógica para ACTUALIZAR O EDITAR (PATCH)

                            val payload = RepositoryPayload(name = name, description = description)
                            viewModel.updateRepository(repoToEdit.owner.login, repoToEdit.name, payload)
                        } else {
                            // Lógica para CREAR (POST)
                            viewModel.createRepository(name, description)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = if (repoToEdit != null) Icons.Default.Edit else Icons.Default.Send,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = if (repoToEdit != null) "Actualizar" else "Guardar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepoFormPreview(){
    GithubClientTheme() {
        RepoForm()
    }
}