package common

interface ReadOnlyIndexer<T> {
    operator fun get(index: Int): T
}