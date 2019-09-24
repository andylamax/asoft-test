package tz.co.asoft.test

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import org.openqa.selenium.WebDriver

open class BrowserTest(
    val url: String = "http://localhost:8088",
    val drivers: List<WebDriver> = injection.drivers()
) : AsyncTest() {
    init {
        drivers.forEach {
            it.manage().window().fullscreen()
        }
    }


    fun WebDriver.visit() = get(url)

    fun browserTest(body: suspend WebDriver.() -> Unit) = asyncTest {
        drivers.map {
            async {
                log.i("Running with ${it.javaClass.name}")
                it.visit()
                it.body()
            }
        }.forEach { it.await() }
    }
}