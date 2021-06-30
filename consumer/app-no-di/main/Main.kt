package com.javiersc.ksp.playground.consumer.app

import com.javiersc.ksp.playground.consumer.internal.GetUserImpl
import com.javiersc.ksp.playground.consumer.internal.provideRemoteDataSource
import com.javiersc.ksp.playground.consumer.presentation.UserViewModel

fun main() {
    val viewModel: UserViewModel by inject()

    println(viewModel.getUser())
}

private fun inject(): Lazy<UserViewModel> = lazy {
    UserViewModel(GetUserImpl(provideRemoteDataSource()))
}
