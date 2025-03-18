package io.github.mdjoo0810.keepgoing.domain.account

import java.math.BigDecimal

data class TransferCommand(
    val fromAccountNumber: String,
    val toAccountNumber: String,
    val amount: BigDecimal,
)
