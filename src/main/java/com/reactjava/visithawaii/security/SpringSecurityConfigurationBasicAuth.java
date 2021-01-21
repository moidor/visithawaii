/*package com.reactjava.visithawaii.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/http://localhost:3000").fullyAuthenticated().and
                ().httpBasic();
        // Autoriser les requêtes CORS afin d'éviter les échecs répertoriés dans la console du navigateur
        http.headers().frameOptions().disable();
    }

*//*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("Login form activated");
        auth.inMemoryAuthentication().withUser("moidor44@hotmail.com").password("{noop}moidor").roles("USER");
    }*//*

}*/

/*package com.reactjava.visithawaii.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //.formLogin().and()
                .httpBasic();
    }
}*/