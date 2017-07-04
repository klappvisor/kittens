package kats.data.either

import kats.kinds.K1

typealias EitherF<L> = K1<Either.F, L>
typealias EitherKind<L, R> = K1<EitherF<L>, R>

fun <L, R> EitherKind<L, R>.narrowEither() = this as Either<L, R>

@Suppress("UNCHECKED_CAST")
fun <L, R> Either.Left<L, *>.castRight(): Either<L, R> = this as Either.Left<L, R>

sealed class Either<out L, out R> : EitherKind<L, R> {

    class F

    abstract val isLeft: Boolean
    abstract val isRight: Boolean

    data class Left<out L, out R>(val value: L) : Either<L, R>() {
        override val isLeft: Boolean = true
        override val isRight: Boolean = false
    }

    data class Right<out L, out R>(val value: R) : Either<L, R>() {
        override val isLeft: Boolean = false
        override val isRight: Boolean = true
    }

    fun <T> fold(onLeft: T, f: (R) -> T): T = when (this) {
        is Left -> onLeft
        is Right -> f(value)
    }
}

inline fun <L, R> Either<L, R>.getOrElse(default: () -> R): R = when (this) {
    is Either.Left -> default()
    is Either.Right -> value
}

inline fun <L, R> Either<L, R>.orElse(default: () -> Either<L, R>): Either<L, R> = when (this) {
    is Either.Left -> default()
    is Either.Right -> this
}