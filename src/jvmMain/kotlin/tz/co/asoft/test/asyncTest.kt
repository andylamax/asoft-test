package tz.co.asoft.test

import kotlinx.coroutines.runBlocking

actual fun asyncTest(block: suspend () -> Unit) = runBlocking{
    block()
}