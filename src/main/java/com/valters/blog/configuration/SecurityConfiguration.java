package com.valters.blog.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER", "ADMIN");
//	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/post").permitAll()
		.antMatchers("/h2-console/**").permitAll();
		
		httpSecurity.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
		.authenticated();
		
		httpSecurity.csrf().disable().formLogin().loginPage("/login").failureUrl("/?error=true")
		.defaultSuccessUrl("/")
		.and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/").and().exceptionHandling()
		.accessDeniedPage("/access-denied");
		
		httpSecurity.headers().frameOptions().disable();
	}

}
