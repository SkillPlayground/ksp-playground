package com.javiersc.ksp.playground.consumer.internal

import com.javiersc.ksp.playground.consumer.open.GetUser
import javax.inject.Inject

class GetUserImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : GetUser {

    override fun invoke(): String = remoteDataSource.user
}
