package io.github.mdjoo0810.keepgoing.api.v1

import io.github.mdjoo0810.keepgoing.common.errors.BusinessException
import io.github.mdjoo0810.keepgoing.common.http.ApiResult
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalApiExceptionV1Handler {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<ApiResult<String>> {
        logger.error("[BusinessException] business error", e)
        val result = ApiResult.error<String>(109, e.message)
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(result)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ApiResult<String>> {
        logger.error("[HttpMessageNotReadableException] invalid error", e)
        val result = ApiResult.error<String>(101, e.localizedMessage)
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(result)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResult<String>> {
        logger.error("[Exception] server error", e)
        val result = ApiResult.error<String>(999, "서버 에러")
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(result)
    }
}
