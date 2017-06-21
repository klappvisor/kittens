package kats

interface Alternative<F> : Applicative<F>, MonoidK<F>