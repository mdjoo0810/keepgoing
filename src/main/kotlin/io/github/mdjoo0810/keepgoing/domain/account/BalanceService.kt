package io.github.mdjoo0810.keepgoing.domain.account

import io.github.mdjoo0810.keepgoing.common.errors.BusinessException
import io.github.mdjoo0810.keepgoing.common.lock.LockManager
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class BalanceService(
    @Qualifier("accountLockManager")
    private val lockManager: LockManager,
    private val accountRepository: AccountRepository,
) {
    fun getBalance(accountNumber: String): BigDecimal {
        return accountRepository.getAccount(accountNumber)?.balance
            ?: throw BusinessException("계좌를 찾을 수 없습니다.")
    }

    fun deposit(
        accountNumber: String,
        amount: BigDecimal,
    ): BigDecimal =
        lockManager.withLock(accountNumber) {
            val account =
                accountRepository.getAccount(accountNumber)
                    ?: throw BusinessException("계좌를 찾을 수 없습니다.")
            account.deposit(amount, accountRepository)
            account.balance
        }

    fun withdraw(
        accountNumber: String,
        amount: BigDecimal,
    ): BigDecimal =
        lockManager.withLock(accountNumber) {
            val account =
                accountRepository.getAccount(accountNumber)
                    ?: throw BusinessException("계좌를 찾을 수 없습니다.")

            if (!account.checkWithdraw(amount)) {
                throw BusinessException("잔고가 부족합니다")
            }
            account.withdraw(amount, accountRepository)
            account.balance
        }
}
