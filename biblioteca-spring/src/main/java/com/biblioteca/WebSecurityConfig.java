package com.biblioteca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Configura el encriptador de contraseñas a utilizar en la aplicación.
     * 
     * @return el encriptador de contraseñas BCryptPasswordEncoder.
     */
    @Bean
    PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configura el AuthenticationManager a utilizar en la autenticación.
	 * 
	 * @param auth la configuración de autenticación.
	 * @return el AuthenticationManager configurado.
	 * @throws Exception si ocurre algún error al obtener el AuthenticationManager.
	 */
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
		return auth.getAuthenticationManager();
	}

	/**
	 * Configura las reglas de autorización para las rutas de la aplicación.
	 * 
	 * @param http el objeto HttpSecurity a configurar.
	 * @return el SecurityFilterChain configurado.
	 * @throws Exception si ocurre algún error al configurar las reglas de
	 *                   autorización.
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(auth -> auth
                .requestMatchers("/css/**", "/img/**", "/script/**", "/login/**", "/failure-login/**")
                .permitAll()

//                .requestMatchers("/user/list").hasAuthority("ADMIN")
//                .requestMatchers("/autor/**").hasAuthority("ADMIN")
//                .requestMatchers("/libro/add/*").hasAuthority("ADMIN")
                
//                .requestMatchers("/report/download/*").hasAuthority("ADMIN").requestMatchers("/report/downloadBetweenDates")
//                .hasAuthority("ADMIN").requestMatchers("/tutor/downloadActiveTutors").hasAuthority("ADMIN")
//                .requestMatchers("/tutor/details/*").hasAnyAuthority("ADMIN")
//
//                .requestMatchers("/student/details/*").hasAnyAuthority("ADMIN", "TUTOR").requestMatchers("/report/details/*")
//                .hasAnyAuthority("ADMIN", "TUTOR")
//
//                .requestMatchers("/tutorial/details/*").hasAuthority("ESTUDIANTE")
//
//                .requestMatchers("/system/**").hasAuthority("ADMIN").requestMatchers("/user/**").hasAuthority("ADMIN")
//                .requestMatchers("/tutorialGroups/**").hasAuthority("ADMIN").requestMatchers("/admin/**").hasAuthority("ADMIN")
//                .requestMatchers("/tutor/**").hasAuthority("TUTOR").requestMatchers("/tutorial/**").hasAuthority("TUTOR")
//                .requestMatchers("/report/**").hasAuthority("TUTOR").requestMatchers("/student/**").hasAuthority("ESTUDIANTE")
//
//                .requestMatchers("/changePasswordUserInSession").authenticated()

                .anyRequest().authenticated()).formLogin(login -> login.loginPage("/login").permitAll()
                .defaultSuccessUrl("/home").failureUrl("/failure-login")).logout(logout -> logout.logoutSuccessUrl("/login")
                .permitAll());

		return http.build();
	}

	/**
	 * Configura el objeto WebMvcConfigurer para permitir todas las peticiones CORS.
	 * 
	 * @return el WebMvcConfigurer configurado.
	 */
	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*");
			}
		};
	}
}