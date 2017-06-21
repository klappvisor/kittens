package kats.kinds

interface Kind2<out F, out A, out B> : Kind1<Kind1<F, A>, B>