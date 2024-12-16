//package ca.sheridancollege.parikhmo.security;
//
//import org.springframework.context.annotation.Bean;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import lombok.AllArgsConstructor;
//  
//@Configuration
//@EnableWebSecurity
//@AllArgsConstructor
//public class SecurityConfig {
//	
//	private LoginAccessDeniedHandler accessDenied;
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) 
//			throws Exception {
//		/////////////////
//		//remove this before production
//		
//		http.csrf().disable();
//		http.headers().frameOptions().disable();
//		////////////////
//		
//		
//		http.authorizeHttpRequests((authz) -> authz 
//				.requestMatchers("/vender").hasRole("VENDER")
//				.requestMatchers("/guest").hasRole("GUEST")
//				.requestMatchers("/add","/edit","/delete/**").hasRole("VENDER")
//	            .requestMatchers("/view/**").permitAll()
//	            .requestMatchers("/h2-console/**").permitAll()
//				.requestMatchers("/register").permitAll()
//				.requestMatchers("/css/**").permitAll()
//				.requestMatchers("/").permitAll()
//				.requestMatchers("/images/").permitAll()
//				
////				.requestMatchers("/vender").hasRole("VENDER")
////				.requestMatchers("/guest").hasRole("GUEST")
////				.requestMatchers("/addticket").hasRole("VENDER")
////				.requestMatchers("/edit/**").hasRole("VENDER")
////				.requestMatchers("/delete/**").hasRole("VENDER")
////				.requestMatchers("/register").permitAll()
////				.requestMatchers("/viewtickets").permitAll()
////				.requestMatchers("/").permitAll()
////				.requestMatchers("/h2-console/**").permitAll()	
////				.requestMatchers("/images/**").permitAll()
////				.requestMatchers("/css/**").permitAll()
//				//.requestmatchers("URL Mapping")
//				//.requestMatchers(HTTPMethod, "URL Mapping")
//				//.hashRole("ROLE") in upper case
//				//.hasAnyRole ("ROLE1", "ROLE2",.....)
//	            //.permitAll() no authentication
//				
//				
//				.anyRequest().authenticated()
//				)
//				
//				.formLogin((formLogin) ->formLogin
//				   .loginPage("/login")
//				   .failureUrl("/login?failed")
//				   .permitAll()
//				   )
//                   .logout((logout) ->logout
//                		   .deleteCookies("remove")
//                		   .invalidateHttpSession(true)
//                		   .logoutUrl("/logout")
//                		   .logoutSuccessUrl("/?logout")
//                		   .permitAll()
//				         )
//                   .exceptionHandling((exception) ->exception
//                		   .accessDeniedHandler(accessDenied))
//				;
//		
//		return http.build();
//		
//		
//	}
//	private UserDetailsServiceImpl userDetailsService;
//	
//	@Bean
//	public AuthenticationManager authManager(HttpSecurity http,
//	PasswordEncoder passwordEncoder) throws Exception {
//	AuthenticationManagerBuilder authenticationManagerBuilder = http
//	.getSharedObject(AuthenticationManagerBuilder.class);
//	authenticationManagerBuilder.userDetailsService(userDetailsService)
//	.passwordEncoder(passwordEncoder);
//	return authenticationManagerBuilder.build();
//	}
//	
//	
////	@Bean
////	public PasswordEncoder passwordEncoder() {
////		PasswordEncoder encoder=
////				PasswordEncoderFactories.createDelegatingPasswordEncoder();
////		return encoder;
////	}
//	
//
////	@Bean
////	public InMemoryUserDetailsManager
////	                userDetailService(PasswordEncoder passwordEncoder) {
////		UserDetails user1= User.withUsername("Hinal")
////				.password(passwordEncoder.encode("234"))
////				.roles("USER")
////				.build();
////		
////		UserDetails user2= User.withUsername("Saty")
////				.password(passwordEncoder.encode("234"))
////				.roles("USER", "PICKLE")
////				.build();
////		return new InMemoryUserDetailsManager(user1, user2);
////
////	}
//
//}


package ca.sheridancollege.parikhmo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	private LoginAccessDeniedHandler accessDenied;
//	@Bean--- what a method return wants to inject in the variable in controller
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		//Remove this before Production
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeHttpRequests((authz)->authz
				.requestMatchers("/vender").hasRole("VENDER")
				.requestMatchers("/guest").hasRole("GUEST")
				.requestMatchers("/add").hasRole("VENDER")
				.requestMatchers("/edit/**").hasRole("VENDER")
				.requestMatchers("/delete/{id}**").hasRole("VENDER")
				.requestMatchers("/register").permitAll()
				.requestMatchers("/view").permitAll()
				.requestMatchers("/").permitAll()
				.requestMatchers("/h2-console/**").permitAll()	
				.requestMatchers("/images/**").permitAll()
				.requestMatchers("/css/**").permitAll()
				.anyRequest().authenticated()
				//.requestMatchers("URL mapping")
				//.requestMatchers(HTTPMethod,"URL mapping")
				//.hasRole("ROLE")	Role in Upper case--why on wed..
				//.hasAnyRole("ROLE1","ROLE2",...)
				//.permitAll()   No Authentication
				)
		
		.formLogin((formLogin)->formLogin
				.loginPage("/login")
				.failureUrl("/login?failed")
				.permitAll()
				)
		
		.logout((logout)->logout
				.deleteCookies("remove")
				.invalidateHttpSession(true)
				.logoutUrl("/logout")
//				.logoutSuccessUrl("/login?logout")
				.logoutSuccessUrl("/?logout")
				.permitAll()
				)
		
		
		.exceptionHandling((exception)->exception
				.accessDeniedHandler(accessDenied))
		;
		
		
		return http.build();
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		return encoder;
//	}
//	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//		UserDetails user1 = User.withUsername("Khushbu")
//				.password(passwordEncoder.encode("123"))
//				.roles("USER")
//				.build();
//		UserDetails user2 = User.withUsername("Rajni")
//				.password(passwordEncoder.encode("456"))
//				.roles("USER","INSTRUCTOR")
//				.build();
//		return new InMemoryUserDetailsManager(user1,user2);
//	}
	
	private UserDetailsServiceImpl userDetailsService;
	
	
	
//	Week 9 needs to move this bean to secrepo to stop the cycle or loop
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
	
	
	@Bean 
	public AuthenticationManager authManager(HttpSecurity http,
			PasswordEncoder passwordEncoder)throws Exception{
		AuthenticationManagerBuilder authBuilder =
				http.getSharedObject(AuthenticationManagerBuilder.class);
		authBuilder.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
		return authBuilder.build();
	}
}
