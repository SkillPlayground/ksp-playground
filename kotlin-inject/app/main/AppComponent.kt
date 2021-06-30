package com.javiersc.ksp.playground.consumer.app

import com.javiersc.ksp.playground.consumer.internal.InternalComponent
import com.javiersc.ksp.playground.consumer.presentation.UserViewModel
import me.tatarka.inject.annotations.Component

@Component
abstract class AppComponent(@Component val internalComponent: InternalComponent) {
    abstract val viewModel: UserViewModel
}
