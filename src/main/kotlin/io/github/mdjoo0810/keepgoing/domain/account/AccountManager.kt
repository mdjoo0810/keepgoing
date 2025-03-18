package io.github.mdjoo0810.keepgoing.domain.account

import io.github.mdjoo0810.keepgoing.common.errors.BusinessException
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class AccountManager(
    private val accountRepository: AccountRepository,
) {
    fun listAccounts(): List<Account> {
        return accountRepository.findAll()
    }

    fun register(accountNumber: String): Account {
        val existAccount = accountRepository.getAccount(accountNumber)

        if (existAccount != null) {
            throw BusinessException("이미 등록된 계좌")
        }

        val newAccount =
            Account(
                number = accountNumber,
                balance = BigDecimal.ZERO,
            )
        accountRepository.insert(newAccount)

        return newAccount
    }

    fun delete(accountNumber: String) {
        val existAccount = accountRepository.getAccount(accountNumber) ?: throw BusinessException("계좌를 찾을 수 없습니다.")
        accountRepository.deleteAccount(existAccount)
    }
}
