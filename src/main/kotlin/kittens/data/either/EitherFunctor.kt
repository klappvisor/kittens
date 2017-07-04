package kittens.data.either

import kittens.Functor
import kittens.data.either.Either.Left
import kittens.data.either.Either.Right

interface EitherFunctor<L> : Functor<EitherF<L>> {

    override fun <R, T> map(fa: EitherKind<L, R>, f: (R) -> T): Either<L, T> {
        val e = fa.narrowEither()
        return when (e) {
            is Left -> Left(e.value)
            is Right -> Right(f(e.value))
        }
    }

    companion object {
        private val instance = object : EitherFunctor<Any> {}

        @Suppress("UNCHECKED_CAST")
        fun <L> instance() = instance as EitherFunctor<L>
    }
}

infix fun <L, R, T> Either<L, R>.map(f: (R) -> T): Either<L, T> = EitherFunctor.instance<L>().map(this, f)