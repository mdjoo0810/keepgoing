package io.github.mdjoo0810.keepgoing.common.http

data class ApiResult<T> internal constructor(
    val code: Int,
    val message: String,
    val result: T?,
) {
    companion object {
        fun <T> success(result: T): ApiResult<T> {
            return ApiResult(
                code = 100,
                message = "성공",
                result = result,
            )
        }

        fun <T> error(
            code: Int,
            message: String,
            result: T? = null,
        ): ApiResult<T> {
            return ApiResult(
                code = code,
                message = message,
                result = null,
            )
        }
    }
}
