/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.company.sgdadmin.repository.LoginRepository;
import javax.ws.rs.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author the_d
 */
@Configuration
@EnableAutoConfiguration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    LoginRepository loginRepository;

    @Value("select usuario as username, contrasena as password, estatus as enabled from usuario where usuario=?")
    private String userQuery;

    @Value("select u.usuario as username, r.nombre as authority from usuario u join rol r on(u.rol_id = r.rol_id) where u.usuario = ? ")
    private String roleQuery;

    @Autowired
    protected void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(userQuery)
                .authoritiesByUsernameQuery(roleQuery)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/vendor/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/menu").hasRole("ADMIN")
                .antMatchers("/listausuarios").hasRole("ADMIN")
                .antMatchers("/nuevousuario").hasRole("ADMIN")
                .antMatchers("/registro").hasRole("ADMIN")
                .antMatchers("/administradorusuarios").permitAll()
                .antMatchers("/cargaarchivos").hasRole("ADMIN")
                .antMatchers("/upload").hasRole("ADMIN")
                .antMatchers("/js").hasRole("ADMIN")
                .antMatchers("/selectdinamico").hasRole("ADMIN")
                .antMatchers("/editarcifras").hasRole("ADMIN")
                .antMatchers("/registrocifra").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/updateUser").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/index").failureUrl("/500")
                .permitAll()
                .defaultSuccessUrl("/menu", true)
                .and()
                .logout()
                .permitAll()
                .clearAuthentication(true);

        http
                .formLogin()
                .permitAll().and().sessionManagement().maximumSessions(1);

        http
                .headers()
                .contentTypeOptions()
                .and()
                .xssProtection()
                .and()
                .cacheControl()
                .and()
                .httpStrictTransportSecurity()
                .and()
                .frameOptions();

        http
                .exceptionHandling()
                .accessDeniedPage("/403");
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
