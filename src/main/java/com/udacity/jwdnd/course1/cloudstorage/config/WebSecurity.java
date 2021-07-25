package com.udacity.jwdnd.course1.cloudstorage.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserDetailServiceImp;

@Configuration
@EnableWebSecurity
//@AllArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter{

	  @Autowired	
	  private UserDetailServiceImp userDetailServiceImp;
	  
	  @Autowired
	  private AuthenticationService authenticationService;
	  
	  @Autowired
	  DataSource datasource;
	
	  @Autowired
	  private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	private static final String[] SWAGGER_WHITELIST = {
            // -- swagger ui
            "/swagger", "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui", "/actuator/health",
            "/configuration/security", "/swagger-ui.html", "/webjars/**","/login.html","/signup.html","/css/**", "/js/**" };
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationService);
		
		//.userDetailsService(userDetailServiceImp)
		//.passwordEncoder(bcryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(SWAGGER_WHITELIST)
		.permitAll()
		.anyRequest().authenticated();
		
		http
		.formLogin()
		.loginPage("/login.html").failureUrl("/login?error=true");//.permitAll() do u need to add this?
		
		http.formLogin().defaultSuccessUrl("/home.html", true);
	}

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	  @Bean
	  public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	  }
}
