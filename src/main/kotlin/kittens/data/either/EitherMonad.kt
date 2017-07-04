package kittens.data.either

import kittens.Monad
import kittens.data.either.Either.Right
import kittens.data.either.Either.Left

interface EitherMonad<L> : EitherApplicative<L>, Monad<EitherF<L>> {
    override fun <A, B> ap(fa: EitherKind<L, A>, ffa: EitherKind<L, (A) -> B>): Either<L, B> =
        super<EitherApplicative>.ap(fa, ffa)

    override fun <A, B> flatMap(fa: EitherKind<L, A>, f: (A) -> EitherKind<L, B>): Either<L, B> {
        val e = fa.narrowEither()
        return when (e) {
            is Left -> e.castRight()
            is Right -> f(e.value).narrowEither()
        }
    }

    companion object {
        private val instance = object : EitherMonad<Any> {}

        @Suppress("UNCHECKED_CAST")
        fun <L> instance() = instance as EitherMonad<L>
    }
}

infix fun <L, R, T> Either<L, R>.flatMap(f: (R) -> Either<L, T>): Either<L, T> =
        EitherMonad.instance<L>().flatMap(this, f)