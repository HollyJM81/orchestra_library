package com.orchlib.backend

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import java.lang.Exception

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    /**
     * minor change to trigger fresh build
     * This section defines the security policy for the app.
     * - BASIC authentication is supported (enough for this REST-based demo)
     * - /composers is secured using URL security shown below
     * - CSRF headers are disabled since we are only testing the REST interface,
     * not a web one.
     *
     * NOTE: GET is not shown which defaults to permitted.
     */
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .httpBasic().and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/composers").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/composers/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.PATCH, "/composers/**").hasRole("ADMIN").and()
            .csrf().disable()
    }
}
