package io.github.mdjoo0810.keepgoing.api.v1.dto

import io.github.mdjoo0810.keepgoing.domain.account.Account
import java.math.BigDecimal

class AccountApiDto {
    data class ListAccountsResponse(val accounts: List<AccountDto>)

    data class RegisterAccountRequest(val accountNumber: String)

    data class RegisterAccountResponse(val account: AccountDto)

    data class AccountDto(
        val number: String,
        val balance: BigDecimal,
    ) {
        companion object {
            fun from(account: Account): AccountDto {
                return AccountDto(account.number, account.balance)
            }
        }
    }
}
