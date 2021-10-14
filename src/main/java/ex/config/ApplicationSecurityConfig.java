package ex.config;

import ex.service.impl.CustomOauth2UserService;
import ex.service.impl.DBUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig  extends WebSecurityConfigurerAdapter {

    private final DBUserService dbUserService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private  CustomOauth2UserService oAuth2UserService;
    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;



    public ApplicationSecurityConfig(DBUserService dbUserService, PasswordEncoder passwordEncoder) {
        this.dbUserService = dbUserService;
        this.passwordEncoder = passwordEncoder;

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(dbUserService).
                passwordEncoder(passwordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/images/**","/fonts/**","/scss/**").permitAll()
                .antMatchers("/", "/users/register","/users/about", "/users/login","/oauth2/authorization/facebook").permitAll()
                .antMatchers("/**").authenticated()
                .and().
                formLogin().
                loginPage("/users/login").
                usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                defaultSuccessUrl("/home").
                failureForwardUrl("/users/login-error")
               .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oAuth2UserService)
                .and().successHandler(oAuth2LoginSuccessHandler).
                defaultSuccessUrl("/home").
                 and().
                logout().
                logoutUrl("/users/logout").
                logoutSuccessUrl("/").
                invalidateHttpSession(true).
                deleteCookies("JSESSIONID")
        ;

    }





}
