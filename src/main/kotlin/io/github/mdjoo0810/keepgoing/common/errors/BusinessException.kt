package io.github.mdjoo0810.keepgoing.common.errors

class BusinessException(
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException()
