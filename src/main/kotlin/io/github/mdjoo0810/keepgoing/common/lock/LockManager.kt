package io.github.mdjoo0810.keepgoing.common.lock

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantLock

class LockManager {
    private val lockStore = ConcurrentHashMap<String, ReentrantLock>()

    fun <T> withLock(
        key: String,
        func: () -> T,
    ): T {
        val lock = getLockFromStore(key)
        lock.lock()

        return runCatching { func() }
            .also { lock.unlock() }
            .getOrElse { throw it }
    }

    fun <T> mustNotLocked(
        key: String,
        func: () -> T,
    ): T {
        val lock = getLockFromStore(key)
        if (!lock.tryLock()) {
            throw IllegalArgumentException("이미 Lock 이 걸려있습니다. $key")
        }

        return runCatching { func() }
            .also { lock.unlock() }
            .getOrElse { throw it }
    }

    private fun getLockFromStore(key: String): ReentrantLock {
        return lockStore.computeIfAbsent(key) { ReentrantLock() }
    }
}
