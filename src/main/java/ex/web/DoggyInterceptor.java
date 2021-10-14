package ex.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DoggyInterceptor extends HandlerInterceptorAdapter {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String link = "https://res.cloudinary.com/bozhidar/image/upload/v1617078040/rmmvkdoq7e1m7yitxlbs.jpg";

            request.setAttribute("doggy",link);


        return true;
    }
}