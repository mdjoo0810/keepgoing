package io.github.mdjoo0810.keepgoing.api.v1.dto

import java.math.BigDecimal

class BalanceApiDto {
    data class GetBalanceResponse(
        val balance: BigDecimal,
    )

    data class DepositRequest(
        val accountNumber: String,
        val amount: BigDecimal,
    )

    data class DepositResponse(
        val balance: BigDecimal,
    )

    data class WithdrawRequest(
        val accountNumber: String,
        val amount: BigDecimal,
    )

    data class WithdrawResponse(
        val balance: BigDecimal,
    )
}
