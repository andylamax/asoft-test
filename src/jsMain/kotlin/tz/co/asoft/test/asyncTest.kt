package tz.co.asoft.test

import kotlinext.js.require
import kotlinx.coroutines.*
import tz.co.asoft.enzyme.adapter.react16.ReactSixteenAdapter
import tz.co.asoft.logging.Logger
import tz.co.asoft.test.enzyme.Enzyme
import kotlin.coroutines.CoroutineContext

actual fun asyncTest(block: suspend () -> Unit) = GlobalScope.async {
    block()
}.asPromise().unsafeCast<dynamic>()

actual abstract class AsyncTest actual constructor() : CoroutineScope {

    actual override val coroutineContext: CoroutineContext = Dispatchers.Default
    actual val log by lazy { Logger(this::class.simpleName!!) }

    actual fun asyncTest(block: suspend () -> Unit) = promise {
        block()
    }.apply {
        catch { log.e(it) }
    }.unsafeCast<Unit>()

    init {
        require("jsdom-global/register")
        Enzyme.configure {
            adapter = ReactSixteenAdapter()
        }
    }
}