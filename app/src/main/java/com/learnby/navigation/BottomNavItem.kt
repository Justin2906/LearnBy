package com.learnby.navigation

import com.example.learnby.R

sealed class BottomNavItem(var title:String, var icon:Int, var route:String){

    object Profile: BottomNavItem("Profile",R.drawable.ic_profile,"my_network")
    object Home : BottomNavItem("Home", R.drawable.casa2,"home")
    object Saves: BottomNavItem("Favs",R.drawable.ic_saved2,"Favs")
    object Exit: BottomNavItem("Exit",R.drawable.ic_logout2,"jobs")
}