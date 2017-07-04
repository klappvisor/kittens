package kats.data.option

import kats.kinds.K1

typealias OptionKind<A> = K1<Option.F, A>

fun <A> OptionKind<A>.narrowOption(): Option<A> = this as Option<A>

sealed class Option<out A> : OptionKind<A> {

    class F

    object None : Option<Nothing>() {
        override val isEmpty: Boolean = true
        override val hasValue: Boolean = false
        override fun toString(): String = "None"
    }

    data class Some<out A>(val value: A) : Option<A>() {
        override val isEmpty: Boolean = false
        override val hasValue: Boolean = true
    }

    abstract val isEmpty: Boolean
    abstract val hasValue: Boolean

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
