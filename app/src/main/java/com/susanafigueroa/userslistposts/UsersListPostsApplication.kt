package com.susanafigueroa.userslistposts

import android.app.Application
import com.susanafigueroa.userslistposts.data.AppContainer
import com.susanafigueroa.userslistposts.data.DefaultAppContainer

class UsersListPostsApplication: Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}