package com.example.movie.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.movie.core.presentation.navigation.BottomNavigationBar
import com.example.movie.core.presentation.navigation.NavigationGraph


@Composable
fun MainScreen(navController: NavHostController) {

    Scaffold (
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        content = {paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                NavigationGraph(navController = navController)
            }
        }
    )

}