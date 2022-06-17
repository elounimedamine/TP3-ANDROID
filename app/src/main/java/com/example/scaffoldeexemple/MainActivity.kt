package com.example.scaffoldeexemple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //body block couleur = white
            Surface(color = Color.White) {
                // Scaffold we created
                LayoutDesign()
            }
        }
    }
}

@Composable
fun Drawer() {
    // Column Composable
    Column(
            modifier = Modifier
              .background(Color.White)
              .fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.profile),
            contentDescription ="Profile image",
            modifier=Modifier.size(70.dp)
                .padding(all = 8.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .clip(shape= CircleShape) //définier un cercle
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape) //bordure du cercle
        )
        // Repeat is a loop which
        // takes count as argument
        repeat(5) { item ->
            Text(text = "Tp $item", fontSize = 30.sp, modifier = Modifier.padding(8.dp), color = Color.Black)
        }
    }
}

@Composable
fun LayoutDesign(){
    //Construire l'état d'une portée de coroutine
    val coroutinescope= rememberCoroutineScope()
    /// construire l'état de scaffold supposée que cette scaffold n'est pas ouvrante à l'état initiale
    val scaffoldState =
        rememberScaffoldState(
            rememberDrawerState(initialValue = DrawerValue.Closed)
        )
    Scaffold (
        //on prend l'état du scaffold
        scaffoldState = scaffoldState,

        //la bar la plus haute
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Tp Androids")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",

                        // When clicked trigger onClick
                        // Callback to trigger drawer open
                        //quand je vais cliquer sur l'icon il va ouvrir une autre page
                        modifier = Modifier.clickable(onClick = {coroutinescope.launch { scaffoldState.drawerState.open() }}),
                        tint = Color.White
                    )}
            )
        },

        //le flottement du bouton au centre (bouton +)
        floatingActionButtonPosition = FabPosition.Center,

        //footer
        bottomBar = {
            BottomNavigationBar() //appel de cette méthode
        },

        //résultat de la page ouvrante du scaffold
        drawerContent = {Drawer()}, //appel

        //le flottement d'un bouton
        floatingActionButton = {
            //si je vais cliquer sur ce bouton, il va le couroutin scope et afficher cette snakbar
            FloatingActionButton(onClick = {  coroutinescope.launch {
                when (
                    scaffoldState.snackbarHostState.showSnackbar(
                        // Message In the snackbar
                        message = "Snack Bar",
                        actionLabel = "Dismiss"
                    )) {}

            } }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add",
                    // When clicked trigger onClick
                    // Callback to trigger drawer open
                    //couleur du backgroud de "+"
                    tint = Color.White
                )
            }
        }
    )
    { innerPadding->
        BodyContent(Modifier.padding(innerPadding))
    }

}

//liste des objets dans le footer comme home,search...
@Composable
fun BottomNavigationBar() {
    //appel à la page navigationitem
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Search,
        NavigationItem.Favorites,
        NavigationItem.Settings
    )
    //footer
    BottomNavigation(
        //arrière plan
        backgroundColor = colorResource(id = R.color.purple_500),
        //le couleur de leur contenu
        contentColor = Color.White
    ) {
        //il va parcourir la liste des objets et affiche l'icon et leur titre
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    /* Add code later */
                }
            )
        }
    }
}

//Affichage de preview
//just in preview on test l'affichage du bottom bar
@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}

//le contenu du body content
@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding((8.dp))) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}