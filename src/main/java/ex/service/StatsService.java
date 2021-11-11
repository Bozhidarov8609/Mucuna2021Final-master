package ex.service;

import ex.model.view.StatsView;

public interface StatsService {

    void onRequest();
    StatsView getStatus();

}
