package com.facts.fruit.tuti.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SelectorStatus (
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    when (val result = state.value.status) {
        ApplicationStatus.Loading -> {
            LoadingScreen()
        }
        ApplicationStatus.Mock -> {
            Content(
                imageId = state.value.fruit.image,
                content = state.value.fruit.content,
                onClick = { viewModel.nextFact() }
            )
        }
        is ApplicationStatus.Succsess -> {
            WebScreen(
                url = result.url
            )
        }

        is ApplicationStatus.Error -> {
            ErrorScreen(error = result.error)
        }
    }
}