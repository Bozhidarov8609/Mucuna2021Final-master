package ex.config;

import ex.model.entity.enums.AuthenticationProvider;
import ex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
       CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

       String email = oAuth2User.getEmail();
       String name = oAuth2User.getName();
       String password = oAuth2User.getEmail();


       userService.registerUserAfterOAuthLoginSuccess(email,name, AuthenticationProvider.FACEBOOK);


        super.onAuthenticationSuccess(request, response, authentication);
    }


}