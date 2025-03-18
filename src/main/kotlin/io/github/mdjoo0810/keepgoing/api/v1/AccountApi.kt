package io.github.mdjoo0810.keepgoing.api.v1

import io.github.mdjoo0810.keepgoing.api.API_V1_PREFIX
import io.github.mdjoo0810.keepgoing.api.v1.dto.AccountApiDto
import io.github.mdjoo0810.keepgoing.common.http.ApiResult
import io.github.mdjoo0810.keepgoing.domain.account.AccountManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$API_V1_PREFIX/accounts")
class AccountApi(
    private val accountManager: AccountManager,
) {
    @GetMapping
    fun getAccounts(): ApiResult<AccountApiDto.ListAccountsResponse> {
        val accounts = accountManager.listAccounts()
        return ApiResult.success(
            AccountApiDto.ListAccountsResponse(
                accounts = accounts.map { AccountApiDto.AccountDto.from(it) },
            ),
        )
    }

    @PostMapping
    fun register(
        @RequestBody request: AccountApiDto.RegisterAccountRequest,
    ): ApiResult<AccountApiDto.RegisterAccountResponse> {
        val account = accountManager.register(request.accountNumber)
        return ApiResult.success(
            AccountApiDto.RegisterAccountResponse(
                account = AccountApiDto.AccountDto.from(account),
            ),
        )
    }
}
