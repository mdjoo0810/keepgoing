package io.github.mdjoo0810.keepgoing.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class WebRouter {
    @GetMapping("/")
    fun index() = "index"

    @GetMapping("/account")
    fun account() = "account"

    @GetMapping("/account/{accountNumber}")
    fun accountDetail(
        @PathVariable("accountNumber") accountNumber: String,
    ) = "account-detail"
}
