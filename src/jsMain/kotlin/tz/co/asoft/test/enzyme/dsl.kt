package tz.co.asoft.test.enzyme

import kotlinext.js.jsObject
import kotlinx.html.injector.InjectByTagName
import react.*
import kotlin.reflect.KClass

object Enzyme {
    fun configure(builder: Configuration.() -> Unit) = configure(jsObject(builder))
}

fun <P : RProps, S : RState, T : Component<P, S>> shallow(clazz: KClass<T>, props: P = jsObject { }): Wrapper<P, S, T> =
    shallow { child(clazz.js, props) {} }

fun <P : RProps, S : RState, T : Component<P, S>> shallow(builder: RBuilder.() -> Unit): Wrapper<P, S, T> =
    shallow(buildElement(builder)!!)

fun <P : RProps, S : RState, C : Component<P, S>> mount(
    clazz: KClass<C>,
    props: P = jsObject { }
): ReactWrapper<P, S, C> =
    mount { child(clazz.js, props) {} }

fun <P : RProps, S : RState, C : Component<P, S>> mount(builder: RBuilder.() -> Unit): ReactWrapper<P, S, C> =
    mount(buildElement(builder)!!)

fun <P : RProps, S : RState, T : Component<P, S>> render(clazz: KClass<T>, props: P = jsObject { }): Wrapper<P, S, T> =
    render { child(clazz.js, props) {} }

fun <P : RProps, S : RState, C : Component<P, S>> render(builder: RBuilder.() -> Unit): Wrapper<P, S, C> =
    render(buildElement(builder)!!)

class By(val selector: String) {
    companion object {
        fun attr(name: String, value: String) = By("[$name='$value']")
        fun name(name: String) = attr("name", name)
        fun id(id: String) = By("#$id")
        fun className(className: String) = By(".$className")
        fun tagName(tagName: String) = By(tagName)
    }
}

fun Wrapper<*,*,*>.find(by: By) = find(by.selector)!!.at(0)
fun Wrapper<*,*,*>.findAll(by: By) = find(by.selector)

fun Wrapper<*, *, *>.findAllByName(name: String) = findAll(By.name(name))
fun Wrapper<*, *, *>.findByName(name: String) = find(By.name(name))

fun Wrapper<*, *, *>.click(mockObject: MockEvent = jsObject { }) = simulate("click", mockObject)

fun Wrapper<*, *, *>.type(text: String) = simulate("change", jsObject {
    target = jsObject { value = text }
})