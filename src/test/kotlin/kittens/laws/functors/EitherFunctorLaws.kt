package kittens.laws.functors

import kittens.data.either.Either
import kittens.data.either.EitherF
import kittens.data.either.EitherFunctor
import kittens.data.either.L

abstract class EitherFunctorLaws(override val fa: Either<L, Int>) : FunctorLaws<EitherF<L>, Either<L, Int>>() {
  override val functor = EitherFunctor.instance<L>()
}

class RightFunctorLaws : EitherFunctorLaws(Either.Right<L, Int>(1))
class LeftFunctorLaws : EitherFunctorLaws(Either.Left(L))