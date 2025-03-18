package io.github.mdjoo0810.keepgoing.domain.account

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
class BalanceServiceTest {
    @Autowired
    private lateinit var accountManager: AccountManager

    @Autowired
    private lateinit var service: BalanceService

    private val givenAccountNumber = "0123456789"

    @BeforeEach
    fun setUp() {
        accountManager.register(givenAccountNumber)
    }

    @AfterEach
    fun tearDown() {
        accountManager.delete(givenAccountNumber)
    }

    @Test
    fun `입금 처리`() {
        service.deposit(givenAccountNumber, BigDecimal(1000))

        val balance = service.getBalance(givenAccountNumber)
        assertEquals(1000L, balance.toLong())
    }

    @Test
    fun `동시 입금 처리`() {
        val executor = Executors.newFixedThreadPool(10)

        repeat(10) {
            executor.submit {
                service.deposit(givenAccountNumber, BigDecimal(1000))
            }
        }

        executor.shutdown()
        executor.awaitTermination(1, TimeUnit.SECONDS)

        val balance = service.getBalance(givenAccountNumber)
        assertEquals(10000L, balance.toLong())
    }

    @Test
    fun `출금 처리`() {
        service.deposit(givenAccountNumber, BigDecimal(1000))

        val balance = service.withdraw(givenAccountNumber, BigDecimal(1000))
        assertEquals(0L, balance.toLong())
    }

    @Test
    fun `동시 출금처리`() {
        service.deposit(givenAccountNumber, BigDecimal(10000))

        val executor = Executors.newFixedThreadPool(10)

        repeat(10) {
            executor.submit {
                service.withdraw(givenAccountNumber, BigDecimal(1000))
            }
        }

        executor.shutdown()
        executor.awaitTermination(1, TimeUnit.SECONDS)

        val balance = service.getBalance(givenAccountNumber)
        assertEquals(0L, balance.toLong())
    }
}
