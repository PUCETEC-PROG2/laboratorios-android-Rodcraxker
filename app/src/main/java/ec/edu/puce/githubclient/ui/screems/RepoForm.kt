package ec.edu.puce.githubclient.ui.screems

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
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ec.edu.puce.githubclient.ui.theme.GithubClientTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoForm(
    onBackClick:() -> Unit = {}
){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Formulario de repositorio") },
                navigationIcon={
                    IconButton(onClick = {}) {
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
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = {Text(text = "Nombre del repositorio")},
                modifier = Modifier.fillMaxWidth(),
                singleLine = true

            )
            Spacer(modifier = Modifier.height(height = 12.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label =  {Text(text = "Descripcion del repositorio") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 5

            )
            Spacer(modifier = Modifier.height(height = 48.dp))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),

            ){
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Guardar"
                )
                Spacer(modifier = Modifier.width(width = 8.dp))
                Text(text = "Guardar")
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