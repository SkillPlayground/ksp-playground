package com.javiersc.ksp.playground.consumer.app

import com.javiersc.ksp.playground.consumer.internal.InternalComponent
import com.javiersc.ksp.playground.consumer.internal.create

fun main() {
    val appComponent: AppComponent = AppComponent::class.create(InternalComponent::class.create())
    println(appComponent.viewModel.loadUser())
}
