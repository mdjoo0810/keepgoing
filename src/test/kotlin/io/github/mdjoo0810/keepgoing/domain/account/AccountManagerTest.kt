package io.github.mdjoo0810.keepgoing.domain.account

import io.github.mdjoo0810.keepgoing.common.errors.BusinessException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
class AccountManagerTest {
    @Autowired
    private lateinit var accountManager: AccountManager

    private val givenAccountNumber = "0123456789"

    @AfterEach
    fun tearDown() {
        accountManager.delete(givenAccountNumber)
    }

    @Test
    fun `계좌가 등록된다`() {
        assertDoesNotThrow {
            accountManager.register(givenAccountNumber)
        }
    }

    @Test
    fun `동일 계좌 등록 시 예외가 발생한다`() {
        accountManager.register(givenAccountNumber)

        val exception =
            assertThrows<BusinessException> {
                accountManager.register(givenAccountNumber)
            }

        assertEquals("이미 등록된 계좌", exception.message)
    }
}
