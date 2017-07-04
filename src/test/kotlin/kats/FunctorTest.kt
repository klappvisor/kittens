package kats

import io.kotlintest.specs.StringSpec
import kats.kinds.K1

abstract class FunctorTest<F, out K : K1<F, Int>> : StringSpec() {
  abstract val functor: Functor<F>

  abstract val fa: K

  init {
    "fproduct" {
      functor.fproduct(fa, ::f) shouldBe functor.map(fa) { Pair(it, f(it)) }
    }

    "void" {
      functor.void(fa) shouldBe functor.map(fa) { Unit }
    }

    "replace" {
      functor.replace(fa, 6) shouldBe functor.map(fa) { _ -> 6 }
    }
  }
}