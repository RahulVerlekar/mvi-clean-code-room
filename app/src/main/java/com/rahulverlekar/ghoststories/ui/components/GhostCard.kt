package com.rahulverlekar.ghoststories.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rahulverlekar.domain.models.GhostSighting

@Composable
fun GhostCard(ghost: GhostSighting, onClick: (id: Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(ghost.id) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row {
            Text(text = "\uD83D\uDC7B", style = MaterialTheme.typography.titleLarge, modifier = Modifier.align(Alignment.CenterVertically).padding(start = 8.dp))
            Column(Modifier.padding(16.dp)) {
                Text(text = ghost.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Scariness: ${ghost.scariness}")
            }
        }
    }
}
