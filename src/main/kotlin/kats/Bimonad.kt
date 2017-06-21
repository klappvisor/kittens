package kats

interface Bimonad<F> : Monad<F>, Comonad<F>