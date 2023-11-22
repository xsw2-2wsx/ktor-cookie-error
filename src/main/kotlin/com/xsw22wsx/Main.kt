package com.xsw22wsx

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.request.*
import java.net.ServerSocket
import kotlin.concurrent.thread

suspend fun main() {
    startServer(8080) { """
        HTTP/1.1 200
        content-type: text/plain
        content-length: 4
        Set-Cookie: UserInfo={"featureToggles":["t1": true, "t2:" false]}; expires=Thu, 23-Nov-2023 20:10:33 GMT; path=/;
        
        Hi!
    """.trimIndent() }

    val client = HttpClient(CIO) {
        install(HttpCookies) {
            storage = AcceptAllCookiesStorage()
        }
    }

    client.request {
        url("http://127.0.0.1:8080/")
    }

    client.post {
        url("http://127.0.0.1:8080/")
    }
}

fun startServer(port: Int, createResp: () -> String ) = thread {
    val socket = ServerSocket(port)

    while(true) {
        val client = socket.accept()
        thread {
            client.getOutputStream().write(createResp().toByteArray(Charsets.ISO_8859_1))
            client.close()
        }
    }
}