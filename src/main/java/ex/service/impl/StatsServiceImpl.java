package ex.service.impl;

import ex.model.view.StatsView;
import ex.service.StatsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements StatsService {

    private int anonymousRequests,authRequests;

    @Override
    public void onRequest() {

     Authentication authentication= SecurityContextHolder
                .getContext()
                .getAuthentication();

     if (authentication!=null && authentication.getPrincipal() instanceof UserDetails){
authRequests++;

     }else {
         anonymousRequests++;
      
     }

    }

    @Override
    public StatsView getStatus() {

        StatsView statsView = new StatsView();
        statsView.setAnonymousRequests(anonymousRequests)
                .setAuthRequests(authRequests);
        return statsView;
    }
}
