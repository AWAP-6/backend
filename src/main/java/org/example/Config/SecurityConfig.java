package org.example.Config;

import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.client.api.Authentication;
import org.example.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/registration","/parcels", "/parcels/get",  "/parcels/add","/hello" ,"/logout" , "/login?logout", "/users/test/**", "/login/**", "/lockers/**", "/users/delete/**","/lockers//{lockerId}/updateStatus" , "/lockers/checkOpenCode","/lockers/location/{locationId}/getStatus", "/lockers/lockerStatus","lockers/updateStatus", "/lockers/sendMail", "/activation/act")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/lockers/updateStatus").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .disable()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/hello")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
