package io.github.mdjoo0810.keepgoing.common.lock

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import java.lang.Thread.sleep
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LockManagerTest {
    private lateinit var testObject: TestObject
    private lateinit var lockManager: LockManager

    @BeforeEach
    fun setUp() {
        testObject = TestObject()
        lockManager = LockManager()
    }

    @Test
    fun `withLock 은 락이 걸려있는 경우 폴링하여 모든 작업을 수행한다`() {
        val executor = Executors.newFixedThreadPool(2)
        executor.submit {
            lockManager.withLock("new-lock") {
                testObject.toggle()
            }
        }

        executor.submit {
            lockManager.withLock("new-lock") {
                testObject.toggle()
            }
        }

        executor.shutdown()
        assertFalse(testObject.isUsed())
    }

    @Test
    fun `mustNotLocked 는 락이 걸려있지 않는 경우 수행된다`() {
        lockManager.mustNotLocked("new-lock") {
            testObject.toggle()
        }

        assertTrue(testObject.isUsed())
    }

    @Test
    fun `mustNotLocked 는 락이 결려있다면 예외가 발생한다`() {
        val executor = Executors.newFixedThreadPool(2)
        val errors = mutableListOf<Exception>()
        executor.submit {
            lockManager.withLock("new-lock") {
                sleep(1000)
            }
        }

        executor.submit {
            sleep(500)
            try {
                lockManager.mustNotLocked("new-lock") {
                    testObject.toggle()
                }
            } catch (e: Exception) {
                errors.add(e)
            }
        }

        executor.shutdown()
        executor.awaitTermination(1, TimeUnit.SECONDS)

        assertEquals(1, errors.size)
    }

    private class TestObject {
        private var used: Boolean = false

        fun toggle() {
            sleep(300)
            used = !used
        }

        fun isUsed(): Boolean {
            return used
        }
    }
}
