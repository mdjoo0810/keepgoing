package io.github.mdjoo0810.keepgoing.config

import io.github.mdjoo0810.keepgoing.common.lock.LockManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LockConfig {
    @Bean("accountLockManager")
    fun accountLockManager(): LockManager {
        return LockManager()
    }
}
