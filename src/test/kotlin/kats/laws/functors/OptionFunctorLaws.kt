package kats.laws.functors

import kats.data.option.Option
import kats.data.option.Option.*
import kats.data.option.OptionFunctor

abstract class OptionFunctorLaws(override val fa: Option<Int>) : FunctorLaws<Option.F, Option<Int>>() {
  override val functor = OptionFunctor
}

class SomeFunctorLaws: OptionFunctorLaws(Some(1))
class NoneFunctorLaws: OptionFunctorLaws(None)