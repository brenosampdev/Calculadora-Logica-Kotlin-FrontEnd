package br.unifor.frontendcalclogica.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.unifor.frontendcalclogica.ui.theme.screens.MainMenuScreen
import br.unifor.frontendcalclogica.ui.theme.screens.SolutionScreen

/**
 * Responsável por toda a navegação do app.
 */
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoutes.HOME) {

        composable(NavRoutes.HOME) {
            MainMenuScreen(
                onGoToSolution = { expr: String ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("expression", expr)

                    navController.navigate(NavRoutes.SOLUTION)
                }
            )
        }

        composable(NavRoutes.SOLUTION) {
            val expr = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<String>("expression")
                ?: ""

            SolutionScreen(
                expression = expr,
                onBack = { navController.popBackStack() }
            )
        }
    }
}