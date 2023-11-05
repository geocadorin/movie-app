package com.example.movie.core.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movie.movie_popular_feature.presentation.MoviePopularScreen
import com.example.movie.movie_popular_feature.presentation.MoviePopularViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {

        composable(BottomNavItem.MoviePopular.route){
            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            MoviePopularScreen(
                uiState = uiState,
                navigateToDetailMovie = {}
            )
        }

        composable(BottomNavItem.MovieSearch.route){
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalArrangement =  Arrangement.Center,
                verticalAlignment =   Alignment.CenterVertically
            ) {
                Text(text = "Pesquisar Filmes", fontSize = 20.sp)
            }
        }

        composable(BottomNavItem.MovieFavorites.route){
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalArrangement =  Arrangement.Center,
                verticalAlignment =   Alignment.CenterVertically
            ) {
                Text(text = "Filmes Favoritos!!!", fontSize = 20.sp)
            }
        }
    }

}