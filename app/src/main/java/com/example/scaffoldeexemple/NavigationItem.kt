package com.example.scaffoldeexemple

//représentent des hiérarchies de classes restreintes qui offrent plus de contrôle sur l'héritage.
sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Search : NavigationItem("search", R.drawable.ic_search, "Search")
    object Favorites : NavigationItem("favorites", R.drawable.ic_favorite, "Favorites")
    object Settings : NavigationItem("settings", R.drawable.ic_settings, "Settings")
}
