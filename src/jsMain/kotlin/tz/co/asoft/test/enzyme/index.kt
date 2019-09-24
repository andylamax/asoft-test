@file:JsModule("enzyme")

package tz.co.asoft.test.enzyme

import org.w3c.dom.Node
import react.Component
import react.RProps
import react.RState
import react.ReactElement

external interface EnzymeAdapter

external fun configure(config: Configuration)

external interface Configuration {
    var adapter: EnzymeAdapter
}

external fun <P : RProps, S : RState, C : Component<P, S>> shallow(el: ReactElement): ShallowWrapper<P, S, C>

external fun <P : RProps, S : RState, C : Component<P, S>> mount(el: ReactElement): ReactWrapper<P, S, C>

external fun <P : RProps, S : RState, C : Component<P, S>> render(el: ReactElement): CherioWrapper<P, S, C>

external interface ShallowWrapper<P : RProps, S : RState, C : Component<P, S>> : Wrapper<P, S, C>

external interface ReactWrapper<P : RProps, S : RState, C : Component<P, S>> : Wrapper<P, S, C> {
    
}

external interface CherioWrapper<P : RProps, S : RState, C : Component<P, S>> : Wrapper<P, S, C>

external interface Wrapper<P : RProps, S : RState, C : Component<P, S>> {
    fun html(): String
    fun find(selector: String): Wrapper<*, *, *>?
    fun text(): String
    fun <T : Node> getDOMNode(): T
    fun get(index: Int): ReactElement?
    fun at(index: Int): Wrapper<*, *, *>?
    fun simulate(event: String, mockObject: MockEvent = definedExternally): Wrapper<*, *, *>
    fun state(): S
    fun props(): P
    fun instance() : C
}

external interface TargetEvent {
    var value: String
}

external interface MockEvent {
    var target: TargetEvent?
}