package com.orchlib.backend

import com.orchlib.backend.composer.persistence.ComposerRowMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DriverManagerDataSource

@Configuration
@ComponentScan("com.orchlib.backend")

class AppConfig {
    @Value("\${spring.datasource.url}")
    private var dataSourceUrl: String? = null

    @Value("\${spring.datasource.username}")
    private var dataSourceUsername: String? = null

    @Value("\${spring.datasource.password}")
    private var dataSourcePassword: String? = null

    @Bean
    fun jdbcTemplate(): JdbcTemplate {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("org.postgresql.Driver")
        dataSource.url = dataSourceUrl
        dataSource.username = dataSourceUsername
        dataSource.password = dataSourcePassword

        return JdbcTemplate(dataSource)
    }

    @Bean
    fun composerRowMapper(): ComposerRowMapper {
        return ComposerRowMapper()
    }
}
