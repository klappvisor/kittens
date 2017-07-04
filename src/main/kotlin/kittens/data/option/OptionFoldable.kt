package kittens.data.option

import kittens.Foldable
import kittens.kernel.Monoid

object OptionFoldable : Foldable<Option.F> {
    override fun <A, B> foldLeft(fa: OptionKind<A>, b: B, f: (B, A) -> B): B {
        val option = fa.narrowOption()
        return when(option) {
            is Option.None -> b
            is Option.Some -> f(b, option.value)
        }
    }

    override fun <A, B> foldRight(fa: OptionKind<A>, b: Lazy<B>, f: (A, Lazy<B>) -> Lazy<B>): Lazy<B> {
        val option = fa.narrowOption()
        return when(option) {
            is Option.None -> b
            is Option.Some -> f(option.value, b)
        }
    }
}

fun <A> Option<A>.fold(m: Monoid<A>): A = OptionFoldable.fold(this, m)