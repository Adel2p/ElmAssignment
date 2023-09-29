package com.elm.elmassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elm.elmassignment.ui.theme.ComposeSampleTheme
import com.elm.elmassignment.ui.theme.feature.categories.FoodCategoriesScreen
import com.elm.elmassignment.ui.theme.feature.categories.IncidentCategoriesViewModel
import com.elm.elmassignment.ui.theme.feature.create.IncidentCreateScreen
import com.elm.elmassignment.utils.NavScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.receiveAsFlow


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = NavScreen.IncidentsScreen.route
                ) {
                    composable(route = NavScreen.IncidentsScreen.route) {
                        val viewModel: IncidentCategoriesViewModel = hiltViewModel()

                        FoodCategoriesScreen(navController ,state = viewModel.state,
                            effectFlow = viewModel.effects.receiveAsFlow()){


                        }

                    }
                    composable(
                        route = NavScreen.AddEditIncidentScreen.route,

                    ) {
                        IncidentCreateScreen(navController)
                    }

                }

            }

        }
    }
}



