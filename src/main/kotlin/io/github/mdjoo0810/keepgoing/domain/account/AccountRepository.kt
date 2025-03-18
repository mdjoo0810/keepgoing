package io.github.mdjoo0810.keepgoing.domain.account

interface AccountRepository {
    fun getAccount(accountNumber: String): Account?

    fun insert(account: Account): Account

    fun update(account: Account): Account

    fun deleteAccount(account: Account)
}
