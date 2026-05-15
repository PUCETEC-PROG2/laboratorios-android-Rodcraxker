package ec.edu.puce.githubclient.ui.componets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ec.edu.puce.githubclient.ui.theme.GithubClientTheme


@Composable

fun RepoItem (
    name: String,
    description: String,
    avatarUrl: String,
    language: String
) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()

    ) {
        Row(
            modifier = Modifier
                .padding( all= 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = avatarUrl,
                contentDescription = "Imagen de repositorio \"$name\"",
                modifier= Modifier.size(60.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(width = 16.dp))
            Column(modifier = Modifier.weight(weight = 1f) ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(height = 4.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3
                )
                Text(
                    text = language,
                    style = MaterialTheme.typography.labelSmall,
                )

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview() {
    GithubClientTheme() {
        RepoItem(
            name = "Nombre del repositorio",
            description = "Descripcion del repositorio",
            avatarUrl = "https://static.vecteezy.com/system/resources/previews/077/675/681/non_2x/simple-outline-round-user-account-profile-avatar-sign-icon-vector.jpg",
            language = "Lenguaje"


        )
    }

}