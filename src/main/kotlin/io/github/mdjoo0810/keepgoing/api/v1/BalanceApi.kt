package io.github.mdjoo0810.keepgoing.api.v1

import io.github.mdjoo0810.keepgoing.api.API_V1_PREFIX
import io.github.mdjoo0810.keepgoing.api.v1.dto.BalanceApiDto
import io.github.mdjoo0810.keepgoing.common.http.ApiResult
import io.github.mdjoo0810.keepgoing.domain.account.BalanceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(API_V1_PREFIX)
class BalanceApi(
    private val balanceService: BalanceService,
) {
    @GetMapping("/balance")
    fun getBalance(
        @RequestParam accountNumber: String,
    ): ApiResult<BalanceApiDto.GetBalanceResponse> =
        ApiResult.success(
            result =
                BalanceApiDto.GetBalanceResponse(
                    balance = balanceService.getBalance(accountNumber),
                ),
        )

    @PostMapping("/balance/deposit")
    fun deposit(
        @RequestBody request: BalanceApiDto.DepositRequest,
    ): ApiResult<BalanceApiDto.DepositResponse> =
        ApiResult.success(
            result =
                BalanceApiDto.DepositResponse(
                    balance = balanceService.deposit(request.accountNumber, request.amount),
                ),
        )

    @PostMapping("/balance/withdraw")
    fun withdraw(
        @RequestBody request: BalanceApiDto.WithdrawRequest,
    ): ApiResult<BalanceApiDto.WithdrawResponse> =
        ApiResult.success(
            result =
                BalanceApiDto.WithdrawResponse(
                    balance = balanceService.withdraw(request.accountNumber, request.amount),
                ),
        )
}
