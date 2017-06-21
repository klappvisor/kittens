package kats.data.option

import kats.kinds.Kind1

typealias OptionKind<A> = Kind1<Option.F, A>

fun <A> OptionKind<A>.narrowOption(): Option<A> = this as Option<A>

sealed class Option<out A> : OptionKind<A> {
    class F

    object None : Option<Nothing>() {
        override val isEmpty: Boolean = true
        override fun toString(): String = "None"
    }

    data class Some<out A>(val value: A) : Option<A>() {
        override val isEmpty: Boolean = false
    }

    abstract val isEmpty: Boolean

    val orNull: A?
        get() = when (this) {
            is Some -> value
            is None -> null
        }

    inline fun <B> fold(empty: B, f: (A) -> B): B = when (this) {
        is Option.Some -> f(value)
        is Option.None -> empty
    }
}
