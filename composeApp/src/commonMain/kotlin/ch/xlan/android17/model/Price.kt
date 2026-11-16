package ch.xlan.android17.model

fun formatPrice(cents: Int): String =
    "${cents / 100}.${(cents % 100).toString().padStart(2, '0')} CHF"
