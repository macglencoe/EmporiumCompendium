@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.emporiumprealpha3

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.emporiumprealpha3.data.DemoData
import com.example.emporiumprealpha3.model.ToolBarButtonOption
import com.example.emporiumprealpha3.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationPage()
                }
            }
        }
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Splash : Screen("splashPage", R.string.splash, Icons.Filled.Info)
    object Catalogue : Screen("CigarCatalogue", R.string.catalogue, Icons.Filled.List)
    object Profile : Screen("CigarProfile", R.string.profile, Icons.Filled.Info)
}
val items = listOf(
    Screen.Splash,
    Screen.Catalogue,
    Screen.Profile
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NavigationPage(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val startPage = "splashPage"

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("King Street Compendium", modifier = Modifier.padding(16.dp))
                Divider()
                items.forEach { screen ->
                    NavigationDrawerItem(
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = false,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                            scope.launch {
                                drawerState.apply {
                                    close()
                                }
                            }
                        }
                    )
                }
                // ...other drawer items
            }
        }
    ) {
        // Screen content
        NavHost(navController = navController, startDestination = startPage) {
            composable("splashPage") { splashPage(
                drawerState, scope, navController
            ) }
            composable("CigarCatalogue") { CigarCatalogue(
                drawerScope = scope,
                drawerState = drawerState,
                navController = navController
            ) }
            composable(
                "CigarProfile/{cigarId}",
                arguments = listOf(navArgument("cigarId") {type = NavType.StringType} )
            ) { backStackEntry ->
                CigarProfile(
                cigarId = backStackEntry.arguments?.getString("cigarId"),
                drawerScope = scope,
                drawerState = drawerState,
                navController = navController
            ) }
            composable("CigarProfile") {
                CigarProfile(
                    drawerScope = scope,
                    drawerState = drawerState,
                    navController = navController
                )
            }
            composable(
                "CigarScaleVisual/{cigarId}",
                arguments = listOf(navArgument("cigarId") {type = NavType.StringType})
            ) { backStackEntry ->
                CigarScaleVisual(
                    cigarId = backStackEntry.arguments?.getString("cigarId"),
                    drawerScope = scope,
                    drawerState = drawerState,
                    navController = navController
                )
            }
        }
    }
}

@Composable
private fun splashPage(
    drawerState: DrawerState? = null,
    drawerScope: CoroutineScope? = null,
    navController: NavController? = null,
    modifier: Modifier = Modifier) {

    ToolBar(
        title = "K S E",
        option1 = ToolBarButtonOption.MENU,{
            drawerScope?.launch { // Launch scope coroutine *if scope is not null*
                drawerState?.apply { // Apply *if drawerState is not null*
                    if (isClosed) open() else close() // Toggle drawer
                }
            }
        },
        option1State = false,
        option2 = ToolBarButtonOption.NONE,
        option2OnClick = {},
        option2State = false
    )

}

//@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun splashPagePreview() {
    splashPage()
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    widthDp = 360, heightDp = 800,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    widthDp = 360, heightDp = 800,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun CigarCataloguePreview() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CigarCatalogue()
        }
    }
}
@Preview(
    widthDp = 360, heightDp = 800,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    widthDp = 360, heightDp = 800,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun CigarProfilePreview() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CigarProfile("1")
        }
    }
}