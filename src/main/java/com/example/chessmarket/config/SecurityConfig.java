package com.example.chessmarket.config;

import com.example.chessmarket.user.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  private final CustomUserDetailsService uds;

  public SecurityConfig(CustomUserDetailsService uds) { this.uds = uds; }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    auth.setUserDetailsService(uds);
    auth.setPasswordEncoder(passwordEncoder());
    return auth;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(reg -> reg
        .requestMatchers("/", "/register", "/login", "/h2-console/**", "/uploads/**", "/css/**", "/js/**", "/images/**").permitAll()
        .requestMatchers("/listing/new", "/listing/save").authenticated()
        .anyRequest().permitAll()
      )
      .formLogin(form -> form
        .loginPage("/login").permitAll()
        .defaultSuccessUrl("/", true)
      )
      .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll())
      .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
      .headers(h -> h.frameOptions(f -> f.disable()));

    return http.build();
  }
}
