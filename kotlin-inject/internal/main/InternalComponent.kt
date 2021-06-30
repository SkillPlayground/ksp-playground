package com.javiersc.ksp.playground.consumer.internal

import com.javiersc.ksp.playground.consumer.open.GetUser
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class InternalComponent {

    @Provides fun providesRemoteDataSource(): RemoteDataSource = RemoteDataSource()

    @Provides fun bindGetUser(getUserImpl: GetUserImpl): GetUser = getUserImpl
}
