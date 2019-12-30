package tz.co.asoft.test

import kotlinext.js.require
import kotlinx.coroutines.*
import tz.co.asoft.test.enzyme.adapter.react16.ReactSixteenAdapter
import tz.co.asoft.test.enzyme.Enzyme
import kotlin.coroutines.CoroutineContext

actual fun asyncTest(block: suspend () -> Unit) = GlobalScope.promise {
    block()
}.unsafeCast<dynamic>()

actual abstract class AsyncTest actual constructor() : CoroutineScope {

    actual override val coroutineContext: CoroutineContext = Dispatchers.Default

    actual fun asyncTest(block: suspend () -> Unit) = promise {
        block()
    }.unsafeCast<Unit>()

    init {
        require("jsdom-global/register")
        Enzyme.configure {
            adapter = ReactSixteenAdapter()
        }
    }
}