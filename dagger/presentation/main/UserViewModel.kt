package com.javiersc.ksp.playground.consumer.presentation

import com.javiersc.ksp.playground.consumer.open.GetUser
import javax.inject.Inject

class UserViewModel @Inject constructor(private val getUser: GetUser) {

    fun loadUser(): String = getUser()
}
