package ec.edu.puce.githubclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import ec.edu.puce.githubclient.models.Repository
import ec.edu.puce.githubclient.ui.screems.RepoForm
import ec.edu.puce.githubclient.ui.screems.RepoList
import ec.edu.puce.githubclient.ui.theme.GithubClientTheme
import ec.edu.puce.githubclient.viewmodels.RepoListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GithubClientTheme {
                var currentScreen by remember { mutableStateOf("RepoList") }
                var repoToEdit by remember { mutableStateOf<Repository?>(null) } // Variable que mantiene el estado
                val listViewModel: RepoListViewModel = viewModel()

                when (currentScreen) {
                    "RepoList" -> RepoList(

                        onNavigateToForm = { repoSelected ->
                            repoToEdit = repoSelected // Guarda el repositorio (o null si es nuevo)
                            currentScreen = "RepoForm" // Cambia a la pantalla del formulario
                        }
                    )
                    "RepoForm" -> RepoForm(
                        repoToEdit = repoToEdit, // Pasa el repositorio al formulario
                        onBackClick = {
                            repoToEdit = null // Limpia la variable al salir
                            currentScreen = "RepoList"
                        },
                        onSaveSuccess = {
                            repoToEdit = null // Limpia la variable al terminar
                            listViewModel.fetchRepos() // Recarga la lista
                            currentScreen = "RepoList"
                        }
                    )
                }
            }
        }
    }
}
