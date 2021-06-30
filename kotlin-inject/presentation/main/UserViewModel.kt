package com.javiersc.ksp.playground.consumer.presentation

import com.javiersc.ksp.playground.consumer.open.GetUser
import me.tatarka.inject.annotations.Inject

@Inject
class UserViewModel(private val getUser: GetUser) {

    fun loadUser(): String = getUser()
}
