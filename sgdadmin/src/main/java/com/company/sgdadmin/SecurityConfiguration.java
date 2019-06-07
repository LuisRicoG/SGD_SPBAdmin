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
                .antMatchers("/index").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/menu").hasAnyRole("USER", "ADMIN")
                .antMatchers("/filemanager").hasAnyRole("USER", "ADMIN")
                .antMatchers("/cargaarchivos").hasAnyRole("USER", "ADMIN")
                .antMatchers("/contratosfirmados").hasAnyRole("USER", "ADMIN")
                .antMatchers("/sagarpa").hasAnyRole("USER", "ADMIN")
                .antMatchers("/informacionindus").hasAnyRole("USER", "ADMIN")
                .antMatchers("/facturasactivos").hasAnyRole("USER", "ADMIN")
                .antMatchers("/avaluoactivos").hasAnyRole("USER", "ADMIN")
                .antMatchers("/edosfinancieros").hasAnyRole("USER", "ADMIN")
                .antMatchers("/proyectoejecutivo").hasAnyRole("USER", "ADMIN")
                .antMatchers("/reporteventas").hasAnyRole("USER", "ADMIN")
                .antMatchers("/generaldownload").hasAnyRole("USER", "ADMIN")
                .antMatchers("/presentacionesCorporativas").hasAnyRole("USER", "ADMIN")
                .antMatchers("/404").hasAnyRole("USER", "ADMIN")
                .antMatchers("/500").hasAnyRole("USER", "ADMIN")
                .antMatchers("/nuevocomitecomercial").hasAnyRole("USER", "ADMIN")
                .antMatchers("/404").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/getFA").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/getFAZip").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/getAA").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/getAAZip").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/getS").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/getSZip").hasAnyRole("USER", "ADMIN")                
                .antMatchers(HttpMethod.POST,"/getEstado").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/getReporte").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/downorganigrama").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/plantrabajo/descargaplan").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/getdocumentogeneral").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/index").failureUrl("/500")
                .defaultSuccessUrl("/menu", true)
                .permitAll()
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