package ec.edu.puce.githubclient.ui.screems

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ec.edu.puce.githubclient.ui.componets.RepoItem

@Composable
fun RepoList (
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier

    ) {
        RepoItem(
            name = "Repositorio de Andrioid",
            description = "Repositorio de Android para 4to nivel de PUCETEC",
            avatarUrl = "https://static.vecteezy.com/system/resources/previews/077/675/681/non_2x/simple-outline-round-user-account-profile-avatar-sign-icon-vector.jpg",
            language =" Kotlin"

        )
        RepoItem(
            name = "Repositorio de Ionic",
            description = "Repositorio de Ionic para 4to nivel de PUCETEC",
            avatarUrl = "https://static.vecteezy.com/system/resources/previews/077/675/681/non_2x/simple-outline-round-user-account-profile-avatar-sign-icon-vector.jpg",
            language =" JavasScript"

        )
        RepoItem(
            name = "Repositorio de Django",
            description = "Repositorio de Django para 4to nivel de PUCETEC",
            avatarUrl = "https://static.vecteezy.com/system/resources/previews/077/675/681/non_2x/simple-outline-round-user-account-profile-avatar-sign-icon-vector.jpg",
            language =" Python"

        )
        RepoItem(
            name = "Repositorio de iOS",
            description = "Repositorio de iOS para 4to nivel de PUCETEC",
            avatarUrl = "https://static.vecteezy.com/system/resources/previews/077/675/681/non_2x/simple-outline-round-user-account-profile-avatar-sign-icon-vector.jpg",
            language =" Swift"

        )
        RepoItem(
            name = "Repositorio de Springboot",
            description = "Repositorio de Springboot para 4to nivel de PUCETEC",
            avatarUrl = "https://static.vecteezy.com/system/resources/previews/077/675/681/non_2x/simple-outline-round-user-account-profile-avatar-sign-icon-vector.jpg",
            language =" Kotlin"

        )
    }
    }
