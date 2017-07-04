package kittens.laws.functors

import io.kotlintest.specs.StringSpec
import kittens.Functor
import kittens.f
import kittens.function.compose
import kittens.function.identity
import kittens.g
import kittens.kinds.K1

abstract class FunctorLaws<F, out K: K1<F, Int>> : StringSpec() {

  abstract val functor: Functor<F>

  abstract val fa: K

  init {
    "covariant identity (map id = id)" {
      functor.map(fa, ::identity) shouldBe fa
    }

    "covariant composition ( (map g) ∘ (map f) = map (g ∘ f)" {
      functor.map(functor.map(fa, ::f), ::g) shouldBe functor.map(fa, ::g compose ::f)
    }
  }
}