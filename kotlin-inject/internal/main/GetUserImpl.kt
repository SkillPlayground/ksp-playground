package com.javiersc.ksp.playground.consumer.internal

import com.javiersc.ksp.playground.consumer.open.GetUser
import me.tatarka.inject.annotations.Inject

@Inject
class GetUserImpl(private val remoteDataSource: RemoteDataSource) : GetUser {

    override fun invoke(): String = remoteDataSource.user
}
