package ru.eternity074.webstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
//	for {noop} see https://mkyong.com/spring-boot/spring-security-there-is-no-passwordencoder-mapped-for-the-id-null/
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("john").password("{noop}pa55word").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}root123").roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").usernameParameter("userId").passwordParameter("password");

		http.formLogin().defaultSuccessUrl("/market/products").failureUrl("/login?error");

		http.logout().logoutSuccessUrl("/login?logout");

		http.exceptionHandling().accessDeniedPage("/login?accessDenied");

		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/**/add").access("hasRole('ADMIN')");
		http.authorizeRequests().antMatchers("/**/market/**").access("hasRole('USER')");

		http.csrf().disable();
	}

}
