package kittens.data.option

import kittens.FunctorTest
import kittens.data.option.Option.*

class OptionFunctorTest : FunctorTest<Option.F, Option<Int>>() {
  override val functor = OptionFunctor
  override val fa = Some(10)

  init {
    "map None" {
      val none: Option<Int> = None
      none.map(Int::toString) shouldBe None
    }

    "map Some" {
      fa.map(Int::toString) shouldBe Some("10")
    }
  }
}