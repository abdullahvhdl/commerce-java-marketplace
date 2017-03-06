package com.respodo.commerce.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.respodo.commerce.common.AuthoritiesConstants;
import com.respodo.commerce.config.security.AjaxLogoutSuccessHandler;
import com.respodo.commerce.config.security.Http401UnauthorizedEntryPoint;

@PropertySource("classpath:application.properties")
public class OAuth2ServerConfiguration {

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends
			ResourceServerConfigurerAdapter {

		@Autowired
		private Http401UnauthorizedEntryPoint authenticationEntryPoint;

		@Autowired
		private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.exceptionHandling()
					.authenticationEntryPoint(authenticationEntryPoint)
					.and()
					.logout()
					.logoutUrl("/api/logout")
					.logoutSuccessHandler(ajaxLogoutSuccessHandler)
					.and()
					.csrf()
					.requireCsrfProtectionMatcher(
							new AntPathRequestMatcher("/oauth/authorize"))
					.disable().headers().frameOptions().disable().headers()
					.and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and().authorizeRequests().antMatchers("/api/authenticate")
					.permitAll().antMatchers("/api/register").permitAll()
					.antMatchers("/api/products/**").permitAll()
					.antMatchers("/api/categories/**").permitAll()
					.antMatchers("/api/stores/**").permitAll()
					.antMatchers("/protected/**").authenticated();

		}
	}
	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends
			AuthorizationServerConfigurerAdapter {

		private static final String PROP_CLIENTID = "authentication.oauth.clientid";
		private static final String PROP_SECRET = "authentication.oauth.secret";
		private static final String PROP_TOKEN_VALIDITY_SECONDS = "authentication.oauth.tokenValidityInSeconds";

		@Resource
		private Environment env;

		@Autowired
		private DataSource dataSource;

		@Bean
		public TokenStore tokenStore() {
			return new JdbcTokenStore(dataSource);
		}

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
				throws Exception {

			endpoints.tokenStore(tokenStore()).authenticationManager(
					authenticationManager);
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients)
				throws Exception {
			System.err.println(env.getRequiredProperty(PROP_CLIENTID));
			System.err.println(env.getRequiredProperty(PROP_SECRET));
			System.err.println(env.getProperty(PROP_TOKEN_VALIDITY_SECONDS,
					Integer.class, 1800));
			clients.inMemory()
					.withClient(env.getRequiredProperty(PROP_CLIENTID))
					.scopes("read", "write")
					.authorities(AuthoritiesConstants.SUPER_ADMIN,AuthoritiesConstants.HELP_DESK,AuthoritiesConstants.ADMIN,
							AuthoritiesConstants.CUSTOMER)
					.authorizedGrantTypes("password", "refresh_token")
					.secret(env.getRequiredProperty(PROP_SECRET))
					.accessTokenValiditySeconds(
							env.getProperty(PROP_TOKEN_VALIDITY_SECONDS,
									Integer.class, 1800));
		}

	}
}
