package com.orchlib.backend

import com.orchlib.backend.database.ComposerDAO
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate

@Configuration
@ComponentScan("com.orchlib.backend")

class AppConfig {
    @Bean
    fun autowiredFieldDependency(): ComposerDAO {
        return ComposerDAO(JdbcTemplate())
    }
}
