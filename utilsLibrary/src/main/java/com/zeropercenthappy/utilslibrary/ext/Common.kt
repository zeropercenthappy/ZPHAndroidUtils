package com.zeropercenthappy.utilslibrary.ext

fun <T1, T2, R> notNullLet(t1: T1?, t2: T2?, block: (T1, T2) -> R): R? {
    return if (t1 != null && t2 != null) {
        block(t1, t2)
    } else {
        null
    }
}

fun <T1, T2, T3, R> notNullLet(t1: T1?, t2: T2?, t3: T3?, block: (T1, T2, T3) -> R): R? {
    return if (t1 != null && t2 != null && t3 != null) {
        block(t1, t2, t3)
    } else {
        null
    }
}

fun <T1, T2, T3, T4, R> notNullLet(t1: T1?, t2: T2?, t3: T3?, t4: T4?, block: (T1, T2, T3, T4) -> R): R? {
    return if (t1 != null && t2 != null && t3 != null && t4 != null) {
        block(t1, t2, t3, t4)
    } else {
        null
    }
}

fun <T1, T2, T3, T4, T5, R> notNullLet(t1: T1?, t2: T2?, t3: T3?, t4: T4?, t5: T5?, block: (T1, T2, T3, T4, T5) -> R): R? {
    return if (t1 != null && t2 != null && t3 != null && t4 != null && t5 != null) {
        block(t1, t2, t3, t4, t5)
    } else {
        null
    }
}