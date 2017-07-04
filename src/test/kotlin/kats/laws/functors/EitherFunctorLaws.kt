package kats.laws.functors

import kats.data.either.Either
import kats.data.either.EitherF
import kats.data.either.EitherFunctor
import kats.data.either.L

abstract class EitherFunctorLaws(override val fa: Either<L, Int>) : FunctorLaws<EitherF<L>, Either<L, Int>>() {
  override val functor = EitherFunctor.instance<L>()
}

class RightFunctorLaws : EitherFunctorLaws(Either.Right<L, Int>(1))
class LeftFunctorLaws : EitherFunctorLaws(Either.Left(L))