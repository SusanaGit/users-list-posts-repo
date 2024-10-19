package com.susanafigueroa.userslistposts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.susanafigueroa.userslistposts.ui.UsersListPostsApp
import com.susanafigueroa.userslistposts.ui.theme.UsersListPostsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            UsersListPostsTheme {
                UsersListPostsApp()
            }
        }
    }
}