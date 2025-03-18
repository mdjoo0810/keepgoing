package io.github.mdjoo0810.keepgoing.domain.account

import java.math.BigDecimal

class Account(
    val number: String,
    var balance: BigDecimal,
) {
    fun deposit(
        amount: BigDecimal,
        accountRepository: AccountRepository,
    ) {
        this.balance = this.balance.add(amount)
        accountRepository.update(this)
    }

    fun withdraw(
        amount: BigDecimal,
        accountRepository: AccountRepository,
    ) {
        this.balance = this.balance.subtract(amount)
        accountRepository.update(this)
    }

    fun checkWithdraw(amount: BigDecimal): Boolean {
        return this.balance >= amount
    }
}
