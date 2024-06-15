package com.enterprise.navigationdrawerjetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import com.enterprise.navigationdrawerjetpackcompose.ui.theme.NavigationDrawerJetpackComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationDrawerJetpackComposeTheme {

                MainContent()

            }
        }
    }
}

@Composable
fun MainContent(){

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerOfModalNavigationDrawer(scope, drawerState) },
        content = { ContentOfModalNavigationDrawer() })

}

@Composable
fun DrawerOfModalNavigationDrawer(scope: CoroutineScope, drawerState: DrawerState) {

    val context = LocalContext.current

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxHeight()
            .width(200.dp)
            .padding(10.dp)){

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Toast.makeText(context, getString(context, R.string.drawer_item_home_clicked_message), Toast.LENGTH_SHORT).show()
                    scope.launch { drawerState.close() }
                }){

            Icon(imageVector = Icons.Default.Home,
                tint = Color.Green,
                contentDescription = stringResource(id = R.string.drawer_item_icon_home_content_description))

            Text(text = stringResource(id = R.string.drawer_item_text_home))

        }

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Toast.makeText(context, getString(context, R.string.drawer_item_settings_clicked_message), Toast.LENGTH_SHORT).show()
                    scope.launch { drawerState.close() }
                }){

            Icon(imageVector = Icons.Default.Settings,
                tint = Color.Red,
                contentDescription = stringResource(id = R.string.drawer_item_icon_settings_content_description))

            Text(text = stringResource(id = R.string.drawer_item_text_settings))

        }

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Toast.makeText(context, getString(context, R.string.drawer_item_about_clicked_message), Toast.LENGTH_SHORT).show()
                    scope.launch { drawerState.close() }
                }){

            Icon(imageVector = Icons.Default.Info,
                tint = Color.Blue,
                contentDescription = stringResource(id = R.string.drawer_item_icon_about_content_description))

            Text(text = stringResource(id = R.string.drawer_item_text_about))

        }


    }
}

@Composable
fun ContentOfModalNavigationDrawer(){

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()){

            Greeting(
                name = "Test Successful!",
                modifier = Modifier.padding(innerPadding)
            )

        }

    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationDrawerJetpackComposeTheme {
        Greeting("Android")
    }
}