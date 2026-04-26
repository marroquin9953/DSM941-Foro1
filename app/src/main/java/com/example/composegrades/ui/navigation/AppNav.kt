package com.example.composegrades.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composegrades.ui.screens.*

sealed class Route(val route: String) {
    data object Login: Route("login")
    data object Welcome: Route("welcome/{username}") {
        fun create(username: String) = "welcome/$username"
    }
    data object Grades: Route("grades/{username}") {
        fun create(username: String) = "grades/$username"
    }
    data object Result: Route("result/{username}/{avg}") {
        fun create(username: String, avg: String) = "result/$username/$avg"
    }
}

@Composable
fun AppNav() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = Route.Login.route) {

        composable(Route.Login.route) {
            val vm: LoginViewModel = viewModel()
            LoginScreen(
                state = vm.state,
                onEmailChange = vm::onEmailChange,
                onPasswordChange = vm::onPasswordChange,
                onLogin = {
                    if (vm.canLogin()) {
                        nav.navigate(Route.Welcome.create(vm.username()))
                    }
                }
            )
        }

        composable(
            Route.Welcome.route,
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStack ->
            val username = backStack.arguments?.getString("username") ?: "Usuario"
            WelcomeScreen(
                username = username,
                onContinue = { nav.navigate(Route.Grades.create(username)) }
            )
        }

        composable(
            Route.Grades.route,
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStack ->
            val username = backStack.arguments?.getString("username") ?: "Usuario"
            val vm: GradesViewModel = viewModel()
            GradesScreen(
                state = vm.state,
                onNoteChange = vm::onNoteChange,
                onCalculate = {
                    val avg = vm.calculateIfValid()
                    if (avg != null) {
                        nav.navigate(Route.Result.create(username, String.format("%.2f", avg)))
                        vm.clearNotes()
                    }
                },
                onBack = { nav.popBackStack() }
            )
        }

        composable(
            Route.Result.route,
            arguments = listOf(
                navArgument("username") { type = NavType.StringType },
                navArgument("avg") { type = NavType.StringType }
            )
        ) { backStack ->
            val username = backStack.arguments?.getString("username") ?: "Usuario"
            val avg = backStack.arguments?.getString("avg") ?: "0.00"
            ResultScreen(
                username = username,
                average = avg,
                onEnterAgain = {
                    nav.popBackStack(Route.Grades.route.substringBefore("/{"), false)
                },
                onLogout = {
                    nav.navigate(Route.Login.route) {
                        popUpTo(Route.Login.route) { inclusive = true }
                    }
                }
            )
        }
    }
}