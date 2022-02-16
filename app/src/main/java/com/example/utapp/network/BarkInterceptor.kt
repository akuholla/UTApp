package com.example.utapp.network

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Singleton

@Singleton
class BarkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()
        val responseString = when {
            uri.endsWith("dogs") -> getListOfDogs
            uri.endsWith("code_name") -> getPuppyName((chain.request().body as FormBody).encodedValue(0).toInt())
            else -> ""
        }

        return Response.Builder()
            .code(200)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .message(responseString)
            .body(
                responseString.toByteArray()
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}

const val getListOfDogs = """
[{
    "name": "German Short Haired Pointer",
    "code": 1007
},
{
    "name": "Coorgi",
    "code": 9006
},
{
    "name": "Labrador",
    "code": 6007
}]
"""

fun getPuppyName(code: Int): String {
    return when (code) {
        6007 -> """{"code":"Labby"} """
        1007 -> """{"code":"Nala"} """
        9006 -> """{"code":"Cookie"} """
        else -> {
            """{"code":"Unknown"} """
        }
    }
}