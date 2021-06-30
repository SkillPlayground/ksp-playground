package com.javiersc.ksp.playground.consumer.internal

import dagger.Module
import dagger.Provides

@Module
object InternalProvidesModule {

    @Provides fun providesRemoteDataSource(): RemoteDataSource = RemoteDataSource()
}
