package com.rahulverlekar.ghoststories.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rahulverlekar.domain.models.GhostSighting

@Composable
fun HomeScreen(data: List<GhostSighting>, modifier: Modifier = Modifier) {

    LazyColumn {
        items(count = data.size, key = { data[it] }, contentType = {}) { index ->
            val value = data[index]
            Text(
                text = "Hello ${value.name}",
                modifier = modifier
            )
            Text(
                text = "${value.name}",
                modifier = modifier
            )
        }
    }

}