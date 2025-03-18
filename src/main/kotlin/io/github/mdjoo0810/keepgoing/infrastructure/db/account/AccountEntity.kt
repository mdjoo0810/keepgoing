package io.github.mdjoo0810.keepgoing.infrastructure.db.account

import io.github.mdjoo0810.keepgoing.infrastructure.db.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
internal class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val accountNumber: String,
    var balance: BigDecimal,
) : BaseEntity()
