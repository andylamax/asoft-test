package tz.co.asoft.test

import kotlinx.coroutines.CoroutineScope
import tz.co.asoft.logging.Logger
import kotlin.coroutines.CoroutineContext

expect fun asyncTest(block: suspend () -> Unit)

expect abstract class AsyncTest() : CoroutineScope {
    override val coroutineContext: CoroutineContext
    val log: Logger
    fun asyncTest(block: suspend () -> Unit)
}