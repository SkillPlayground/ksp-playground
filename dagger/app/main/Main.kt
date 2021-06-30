package com.javiersc.ksp.playground.consumer.app

fun main() {
    val appComponent: AppComponent = DaggerAppComponent.create()
    println(appComponent.viewModel.loadUser())
}
