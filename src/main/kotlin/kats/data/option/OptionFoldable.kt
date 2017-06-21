package kats.data.option

import kats.Foldable
import kats.kernel.Monoid

object OptionFoldable : Foldable<Option.F> {
    override fun <A, B> foldLeft(fa: OptionKind<A>, b: B, f: (B, A) -> B): B {
        val option = fa.narrowOption()
        return when(option) {
            is Option.None -> b
            is Option.Some -> f(b, option.value)
        }
    }

    override fun <A, B> foldRight(fa: OptionKind<A>, b: B, f: (A, B) -> B): B {
        val option = fa.narrowOption()
        return when(option) {
            is Option.None -> b
            is Option.Some -> f(option.value, b)
        }
    }
}

fun <A> Option<A>.fold(m: Monoid<A>): A = OptionFoldable.fold(this, m)