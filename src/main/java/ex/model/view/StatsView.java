package ex.model.view;

public class StatsView {
    private  int anonymousRequests;
    private  int authRequests;

    public StatsView() {
    }

    public StatsView setAnonymousRequests(int anonymousRequests) {
        this.anonymousRequests = anonymousRequests;
        return this;
    }

    public StatsView setAuthRequests(int authRequests) {
        this.authRequests = authRequests;
        return this;
    }

    public int getTotalRequest(){
        return anonymousRequests+authRequests;
    }


    public int getAnonymousRequests() {
        return anonymousRequests;
    }



    public int getAuthRequests() {
        return authRequests;
    }


}
