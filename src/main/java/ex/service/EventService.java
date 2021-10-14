package ex.service;

import ex.model.service.EventServiceModel;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface EventService {
    void addEvent(EventServiceModel eventServiceModel, Principal principal) throws IOException;

    List<EventServiceModel> findAllEvent();
}
