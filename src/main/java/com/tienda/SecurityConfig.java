package com.tienda;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                
                .withUser("admin")
                .password("(noop)123")
                .roles("ADMIN", "VENDEDOR","USER")
                .and()
                
                .withUser("vendedor")
                .password("(noop)123")
                .roles("VENDEDOR","USER")
                .and()
                
                .withUser("user")
                .password("(noop)123")
                .roles("VENDEDOR","USER");
                
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                .antMatchers("/articulo/nuevo", "/articulo/guardar", "/articulo/nuevo", "/articulo/modificar/**", "/articulo/eliminar/**",
                            "/categoria/nuevo", "/categoria/guardar", "/categoria/nuevo", "/categoria/modificar/**", "/categoria/eliminar/**",
                            "/cliente/nuevo", "/cliente/guardar", "/cliente/nuevo", "/cliente/modificar/**", "/cliente/eliminar/**",
                            "/usuario/nuevo", "/usuario/guardar", "/usuario/nuevo", "/usuario/modificar/**", "/usuario/eliminar/**")
                .hasRole("ADMIN")
                
                .antMatchers("/articulo/listado",
                        "/categoria/listado", 
                        "/cliente/listado")
                .hasAnyRole("ADMIN", "VENDEDOR")
                
                .antMatchers("/")
                .hasAnyRole("ADMIN", "VENDEDOR", "USER")
                
                .and()
                    .formLogin()
                    .loginPage("/login")
                
                .and()
                    .exceptionHandling().accessDeniedPage("/errores/403"); 
        
    }
}
