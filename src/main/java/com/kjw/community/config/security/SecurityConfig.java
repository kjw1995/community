package com.kjw.community.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.kjw.community.common.handler.login.LoginFailureHandler;
import com.kjw.community.common.handler.login.LoginSuccessHandler;
import com.kjw.community.common.member.MemberRole;
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

		http
			.csrf(AbstractHttpConfigurer::disable)
			.cors(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(httpRequests ->
				httpRequests.requestMatchers(buildWhiteList(mvc))
					.permitAll()
					.requestMatchers(buildAuthenticatedRequests(mvc))
					.hasAnyAuthority(MemberRole.NORMAL.getValue())

			)
			.formLogin(loginOptions -> loginOptions
				.loginPage(GlobalURL.VIEW_LOGIN)
				.usernameParameter("loginUsername")
				.passwordParameter("loginPassword")
				.successHandler(loginSuccessHandler)
				.failureHandler(loginFailureHandler)
				.loginProcessingUrl(GlobalURL.LOGIN_URL)
			)
			.sessionManagement(sessionManagement -> sessionManagement
				.sessionFixation().migrateSession()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.sessionConcurrency(concurrencyOptions -> concurrencyOptions
					.maximumSessions(1)
					.maxSessionsPreventsLogin(true)
					.expiredUrl(GlobalURL.VIEW_LOGIN)
				)

			)
			.logout(logoutOptions -> logoutOptions
				.logoutUrl(GlobalURL.LOGOUT_URL)
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID")
			);

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

	private MvcRequestMatcher[] buildWhiteList(MvcRequestMatcher.Builder mvc) {
		return new MvcRequestMatcher[] {
			mvc.pattern(GlobalURL.VIEW_ERROR),
			mvc.pattern(GlobalURL.VIEW_MAIN),
			mvc.pattern(GlobalURL.VIEW_LOGIN),
			mvc.pattern(GlobalURL.VIEW_SIGN),
			mvc.pattern(GlobalURL.VIEW_POST),
			mvc.pattern(GlobalURL.MEMBER_URL),
			mvc.pattern(GlobalURL.LOGIN_URL),
			mvc.pattern(GlobalURL.LOGOUT_URL),
			mvc.pattern(HttpMethod.GET, GlobalURL.POST_URL + "/**")
		};
	}

	private MvcRequestMatcher[] buildAuthenticatedRequests(MvcRequestMatcher.Builder mvc) {
		return new MvcRequestMatcher[] {
			mvc.pattern(GlobalURL.VIEW_POST_CREATE),
			mvc.pattern(GlobalURL.VIEW_POST_DETAIL + "/**"),
			mvc.pattern(HttpMethod.POST, GlobalURL.POST_URL + "/**")
		};
	}

}
