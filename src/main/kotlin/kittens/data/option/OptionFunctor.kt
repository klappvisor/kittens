package kittens.data.option

import kittens.Functor
import kittens.kinds.K1

object OptionFunctor : Functor<Option.F> {
  override fun <A, B> map(fa: K1<Option.F, A>, f: (A) -> B): Option<B> {
    val option = fa.narrowOption()
    return when (option) {
      is Option.None -> Option.None
      is Option.Some -> Option.Some(f(option.value))
    }
  }
}

infix fun <A, B> Option<A>.map(f: (A) -> B): Option<B> = OptionFunctor.map(this, f)