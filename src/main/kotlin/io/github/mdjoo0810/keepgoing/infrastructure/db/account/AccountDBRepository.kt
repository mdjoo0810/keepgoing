package io.github.mdjoo0810.keepgoing.infrastructure.db.account

import io.github.mdjoo0810.keepgoing.domain.account.Account
import io.github.mdjoo0810.keepgoing.domain.account.AccountRepository
import io.github.mdjoo0810.keepgoing.infrastructure.db.account.jpa.AccountJpaRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import java.sql.SQLException

@Repository
class AccountDBRepository internal constructor(
    private val accountJpaRepository: AccountJpaRepository,
) : AccountRepository {
    override fun findAll(): List<Account> {
        val accountEntities = accountJpaRepository.findAll()
        return accountEntities.map { it.toAccount() }
    }

    override fun getAccount(accountNumber: String): Account? {
        val account = accountJpaRepository.findByAccountNumber(accountNumber) ?: return null
        return account.toAccount()
    }

    @Transactional
    override fun insert(account: Account): Account {
        val initEntity =
            AccountEntity(
                accountNumber = account.number,
                balance = account.balance,
            ).also { accountJpaRepository.save(it) }

        return initEntity.toAccount()
    }

    @Transactional
    override fun update(account: Account): Account {
        val accountEntity =
            accountJpaRepository.findByAccountNumber(account.number)
                ?: throw SQLException("Account ${account.number} not found")
        accountEntity.balance = account.balance
        return accountEntity.toAccount()
    }

    @Transactional
    override fun deleteAccount(account: Account) {
        val accountEntity =
            accountJpaRepository.findByAccountNumber(account.number)
                ?: throw SQLException("Account ${account.number} not found")
        accountJpaRepository.delete(accountEntity)
    }

    private fun AccountEntity.toAccount() =
        Account(
            number = this.accountNumber,
            balance = this.balance,
        )
}
