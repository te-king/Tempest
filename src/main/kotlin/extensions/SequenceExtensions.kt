package extensions

fun <T> Sequence<T>.pairedPermutations(): Sequence<Pair<T, T>> =
    sequence {
        val current = firstOrNull() ?: return@sequence
        val rest = drop(1)
        yieldAll(rest.map { current to it })
        yieldAll(rest.pairedPermutations())
    }