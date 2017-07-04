 package kittens.data.either

import kittens.FunctorTest

object L

class EitherFunctorTest : FunctorTest<EitherF<L>, Either<L, Int>>() {
  override val functor = EitherFunctor.instance<L>()
  override val fa = Either.Right<L, Int>(5)

  init {
    "right biased map on Left" {
      val left = Either.Left<L, Int>(L)
      when(left.map(Int::toString)) {
        is Either.Left -> left.value shouldBe L
        is Either.Right -> fail("Expected left")
      }
    }

    "right biased map on Right" {
      val right = Either.Right<L, Int>(10)
      when(right.map(Int::toString)) {
        is Either.Left -> fail("Expected right")
        is Either.Right -> right.value shouldBe 10
      }
    }
  }
}