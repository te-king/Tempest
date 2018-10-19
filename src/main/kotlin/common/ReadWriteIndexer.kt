package common

interface ReadWriteIndexer<T>: ReadOnlyIndexer<T> {
    operator fun set(index: Int, value: T)
}