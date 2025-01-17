package com.kjw.community.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.kjw.community.common.handler.login.LoginFailureHandler;
import com.kjw.community.common.handler.login.LoginSuccessHandler;
import com.kjw.community.global.GlobalURL;
import com.kjw.community.service.login.LoginService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

	private final LoginSuccessHandler loginSuccessHandler;

	private final LoginFailureHandler loginFailureHandler;

	private final LoginService loginService;

	@Bean
	public PasswordEncoder bcryptoPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebSecurityCustomizer webConfigurer() {
		return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws
		Exception {

		MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);
		MvcRequestMatcher[] whiteList = {
			mvc.pattern(GlobalURL.VIEW_LOGIN),
			mvc.pattern(GlobalURL.VIEW_BOARD)
		};

		http
			.csrf(AbstractHttpConfigurer::disable)
			.cors(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(httpRequests -> httpRequests.requestMatchers(whiteList).permitAll())
			.formLogin(loginOptions -> loginOptions
				.loginPage(GlobalURL.VIEW_LOGIN)
				.usernameParameter("loginUsername")
				.passwordParameter("loginPassword")
				.successHandler(loginSuccessHandler)
				.failureHandler(loginFailureHandler)
				.loginProcessingUrl(GlobalURL.LOGIN_URL));

		return http.build();

	}

	@Bean
	public AuthenticationProvider customAuthenticationProvider() {
		return new CustomerAuthenticationProvider(bcryptoPasswordEncoder(), loginService);
	}

	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(customAuthenticationProvider());
	}

}
