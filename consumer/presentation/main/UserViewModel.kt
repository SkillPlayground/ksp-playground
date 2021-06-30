package com.javiersc.ksp.playground.consumer.presentation

import com.javiersc.ksp.playground.consumer.open.GetUser
import com.javiersc.ksp.playground.processor.annotations.Injectable

@Injectable
class UserViewModel(private val getUser: GetUser) {

    fun getUser(): String = getUser()
}
