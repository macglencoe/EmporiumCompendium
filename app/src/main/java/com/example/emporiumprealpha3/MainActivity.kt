@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.emporiumprealpha3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.example.emporiumprealpha3.model.ToolBarButtonOption
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
                    //CigarCatalogue(Modifier)
                    //splashPage(Modifier)
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
        NavHost(navController = navController, startDestination = "splashPage") {
            composable("splashPage") { splashPage(navController) }
            composable("CigarCatalogue") { CigarCatalogue(scope = scope, drawerState = drawerState) }
            composable("CigarProfile") { CigarProfile(
                scope = scope,
                drawerState = drawerState,
                navController = navController
            ) }
        }
    }
}

@Composable
private fun splashPage(navController: NavController, modifier: Modifier = Modifier) {

}

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun splashPagePreview() {
    //splashPage()
}
@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun CigarCataloguePreview() {
    //CigarCatalogue(Modifier)
}