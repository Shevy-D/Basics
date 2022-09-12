@file:OptIn(DelicateCoroutinesApi::class)

package com.shevy.basics

import kotlinx.coroutines.*

suspend fun sendEmail(r: String, msg: String): Boolean { //(6)
    delay(2000)
    println("Sent '$msg' to $r")
    return true
}

suspend fun getReceiverAddressFromDatabase(): String { //(4)
    delay(1000)
    return "coroutine@kotlin.org"
}

suspend fun sendEmailSuspending(): Boolean {
    val msg = GlobalScope.async {
        delay(500)
        "The message content"
    }
    val recipient = GlobalScope.async { getReceiverAddressFromDatabase() }
    println("Waiting for email data")

    return sendEmail(recipient.await(), msg.await()) //(7)
}

fun main(args: Array<String>) = runBlocking { //(1)
    sendEmailSuspending()  //(2)
    println("Email sent successfully.")
    println("Finished")
}
