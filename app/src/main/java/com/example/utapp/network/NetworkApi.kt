package com.example.utapp.network

import com.example.utapp.data.CodeName
import com.example.utapp.data.Dog
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkApi {

    @GET("dogs")
    fun getListOfDogs(): Single<List<Dog>>

    @POST("code_name")
    @FormUrlEncoded
    fun getCodeName( @Field("code") code: Int): Single<CodeName>
}