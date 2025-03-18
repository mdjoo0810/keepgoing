package io.github.mdjoo0810.keepgoing.infrastructure.db.account.jpa

import io.github.mdjoo0810.keepgoing.infrastructure.db.account.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface AccountJpaRepository : JpaRepository<AccountEntity, Long> {
    fun findByAccountNumber(accountNumber: String): AccountEntity?
}
