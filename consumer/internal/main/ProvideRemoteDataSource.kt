package com.javiersc.ksp.playground.consumer.internal

import com.javiersc.ksp.playground.processor.annotations.Provides

@Provides fun provideRemoteDataSource(): RemoteDataSource = RemoteDataSource()
