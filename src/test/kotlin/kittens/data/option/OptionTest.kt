package kittens.data.option

import io.kotlintest.specs.StringSpec
import kittens.data.option.Option.Some
import kittens.data.option.Option.None

class OptionTest : StringSpec() {
  val x = 1
  val y = 0
  init {
    "getOrElse for Some" {
      Some(x).getOrElse { y } shouldBe x
    }

    "getOrElse for None" {
      None.getOrElse { x } shouldBe x
    }

    "orElse for Some" {
      Some(x).orElse { Some(y) } shouldBe Some(x)
    }

    "orElse for None" {
      None.orElse { Some(x) } shouldBe Some(x)
    }

    "fold for Some" {
      Some(x).fold("0", Int::toString) shouldBe "1"
    }

    "fold for None" {
      None.fold("0", Int::toString) shouldBe "0"
    }
  }
}