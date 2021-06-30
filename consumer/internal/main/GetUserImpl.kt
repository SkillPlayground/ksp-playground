package com.javiersc.ksp.playground.consumer.internal

import com.javiersc.ksp.playground.consumer.open.GetUser
import com.javiersc.ksp.playground.processor.annotations.Injectable

@Injectable
class GetUserImpl(private val remoteDataSource: RemoteDataSource) : GetUser {

    override fun invoke(): String = remoteDataSource.user
}
