package com.javiersc.ksp.playground.consumer.app

import com.javiersc.ksp.playground.consumer.internal.InternalBindsModule
import com.javiersc.ksp.playground.consumer.internal.InternalProvidesModule
import com.javiersc.ksp.playground.consumer.presentation.UserViewModel
import dagger.Component

@Component(modules = [InternalBindsModule::class, InternalProvidesModule::class])
interface AppComponent {
    val viewModel: UserViewModel
}
