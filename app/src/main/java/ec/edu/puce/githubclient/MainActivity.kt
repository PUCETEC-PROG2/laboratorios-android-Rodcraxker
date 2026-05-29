package ec.edu.puce.githubclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ec.edu.puce.githubclient.ui.screems.RepoForm
import ec.edu.puce.githubclient.ui.screems.RepoList
import ec.edu.puce.githubclient.ui.theme.GithubClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GithubClientTheme {
                var currentScreen by remember { mutableStateOf("RepoList") }
                when (currentScreen){
                    "RepoList" -> RepoList (
                        onNavigateToForm = { currentScreen = "RepoForm"}
                    )
                    "RepoForm" -> RepoForm(
                        onBackClick = {currentScreen = "RepoList"}
                    )
                }
            }
        }
    }
}
