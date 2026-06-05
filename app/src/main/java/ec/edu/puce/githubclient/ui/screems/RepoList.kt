package ec.edu.puce.githubclient.ui.screems

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ec.edu.puce.githubclient.ui.componets.RepoItem
import ec.edu.puce.githubclient.ui.theme.GithubClientTheme
import ec.edu.puce.githubclient.viewmodels.RepoListViewModel
import ec.edu.puce.githubclient.models.Repository

@Composable
fun RepoList(
    onNavigateToForm: (Repository?) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RepoListViewModel = viewModel()
) {
    val repos by viewModel.repos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errMsg by viewModel.errMsg.collectAsState()


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick={onNavigateToForm(null)},
                shape= CircleShape,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir repositorio"
                )
            }
        }
    ){  innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            errMsg?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(all = 16.dp)
                )
            }

            if (!isLoading && errMsg == null) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(count = repos.size) { i ->
                        val repo = repos[i]
                        RepoItem(
                            repository = repo,
                            onDelete = { viewModel.deleteRepo(repo.owner.login, repo.name) },
                            onEdit = {
                                onNavigateToForm(repo)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RepoListPreview() {
    GithubClientTheme {
        RepoList(onNavigateToForm = {})
    }
}

