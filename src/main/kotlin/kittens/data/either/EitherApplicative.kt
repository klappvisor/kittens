package kittens.data.either

import kittens.Applicative
import kittens.data.either.Either.Left
import kittens.data.either.Either.Right

interface EitherApplicative<L> : Applicative<EitherF<L>> {

    override fun <A> pure(a: A): Either<L, A> = Right(a)

    override fun <A, B> ap(fa: EitherKind<L, A>, ffa: EitherKind<L, (A) -> B>): Either<L, B> {
        val ela = fa.narrowEither()
        val elfab = ffa.narrowEither()

        return when (ela) {
            is Left -> ela.castRight()
            is Right -> when (elfab) {
                is Right -> Right(elfab.value(ela.value))
                is Left -> elfab.castRight()
            }
        }
    }
}