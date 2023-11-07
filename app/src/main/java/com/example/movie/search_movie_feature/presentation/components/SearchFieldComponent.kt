package com.example.movie.search_movie_feature.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movie.search_movie_feature.presentation.MovieSearchEvent
import com.example.movie.ui.theme.black
import com.example.movie.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFieldComponent(
    modifier: Modifier = Modifier,
    query: String,
    onSearch: (String) -> Unit,
    onQueryChangeEvent: (MovieSearchEvent) -> Unit
) {

    OutlinedTextField(
        value = query,
        onValueChange = {
            onQueryChangeEvent(MovieSearchEvent.EnteredQuery(it))
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    onSearch(query)
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null
                )
            }
        },
        placeholder = {
            Text("Pesquisa de Filmes")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query)
            }
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = black,
            unfocusedContainerColor= black,
            unfocusedPlaceholderColor = white,
            cursorColor = white,
            focusedTextColor = white,
            unfocusedTextColor = white,
            unfocusedTrailingIconColor = white,
            focusedTrailingIconColor = white
        ),
        modifier = modifier
            .fillMaxWidth()
            //.padding(16.dp)
            .heightIn(min = 60.dp)
    )

}

@Preview
@Composable
fun SearchFieldComponentPreview() {
    SearchFieldComponent(
        query = "",
        onSearch = {},
        onQueryChangeEvent = {}
    )
}