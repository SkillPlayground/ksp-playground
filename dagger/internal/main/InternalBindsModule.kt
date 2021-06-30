package com.javiersc.ksp.playground.consumer.internal

import com.javiersc.ksp.playground.consumer.open.GetUser
import dagger.Binds
import dagger.Module

@Module
interface InternalBindsModule {

    @Binds fun bindsGetUser(getUserImpl: GetUserImpl): GetUser
}
