package com.mindhub.merchshop.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
class WebAuthorization{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/rest/**").hasAuthority("ADMIN")
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/usuarios").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/usuario/actual").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.GET,"/api/usuario/modificar").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.POST,"/api/usuario/registro").permitAll()
                .antMatchers(HttpMethod.GET,"/api/productos").permitAll()
                .antMatchers(HttpMethod.GET,"/api/ilustradores/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/admin/illustradores").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/email/pdf").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.GET,"/web/cuenta.html").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.GET,"/web/carrito.html").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.GET,"/web/editor-producto.html").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.GET,"/web/index.html").permitAll()
                .antMatchers(HttpMethod.GET,"/web/contacto.html").permitAll()
                .antMatchers(HttpMethod.GET,"/web/ilustrador.html").permitAll()
                .antMatchers(HttpMethod.GET,"/web/productos.html").permitAll();



        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");


        http.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");

        // turn off checking for CSRF tokens
        http.csrf().disable();

        //disabling frameOptions so h2-console can be accessed
        http.headers().frameOptions().disable();

        // if user is not authenticated, just send an authentication failure response
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication

        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        return http.build();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }

}
