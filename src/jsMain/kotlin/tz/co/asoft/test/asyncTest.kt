package tz.co.asoft.test

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asPromise
import kotlinx.coroutines.async

actual fun asyncTest(block: suspend () -> Unit) = GlobalScope.async {
    block()
}.asPromise().unsafeCast<dynamic>()