package com.example.movie.core.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.movie.core.presentation.navigation.BottomNavigationBar
import com.example.movie.core.presentation.navigation.NavigationGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {

    Scaffold (
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        content = {
            NavigationGraph(navController = navController)
        }
    )

}