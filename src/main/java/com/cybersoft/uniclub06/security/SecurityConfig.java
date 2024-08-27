package com.cybersoft.uniclub06.security;

import com.cybersoft.uniclub06.filter.CustomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.Header;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean // hàm so sánh password đã được mã hóa
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // báo cho AuthenProvider của Security sử dụng CustomAuthenProvider đã tạo
    public AuthenticationManager authenticationManager(HttpSecurity http, CustomAuthenProvider authenProvider) throws Exception {

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenProvider) // Yêu cầu sử dụng authenProvider đã khởi tạo
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomFilter customFilter) throws Exception {

        return http
                .csrf(csrf -> csrf.disable()) // tắt csrf
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // chặn không cho dùng session
                .authorizeRequests(request -> { // quy định đường dẫn có được phép sài hay không or có cần chứng thực hay không
                    request.requestMatchers("/authen","/file/**").permitAll(); // tất cả các đường dẫn /authen không cần phải chứng thực không phân biệt Post hay get
                    request.requestMatchers("/profile").hasRole("ADMIN");
                    request.anyRequest().authenticated(); // tất cả các đường link khác phải chứng thực
                })
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

}
