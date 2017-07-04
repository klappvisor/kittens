package kats.laws.functors

import io.kotlintest.specs.StringSpec
import kats.Functor
import kats.f
import kats.function.compose
import kats.function.identity
import kats.g
import kats.kinds.K1

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