package ex.service.impl;

import ex.model.entity.Event;
import ex.model.entity.UserEntity;
import ex.model.service.EventServiceModel;
import ex.repository.EventRepository;
import ex.repository.UserRepository;
import ex.service.CloudinaryService;
import ex.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;

    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper, UserRepository userRepository, CloudinaryService cloudinaryService) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void addEvent(EventServiceModel eventServiceModel, Principal principal) throws IOException {
        System.out.println();
        Event event = modelMapper.map(eventServiceModel,Event.class);
        UserEntity userEntity= userRepository.findByUsername(principal.getName()).orElse(null);
        event.setUser(userEntity);
        MultipartFile multipartFile=  eventServiceModel.getImage();
        String img = this.cloudinaryService.uploadImage(multipartFile);
        event.setPicture(img);
        eventRepository.saveAndFlush(event);

    }

    @Override
    public List<EventServiceModel> findAllEvent() {
      List<Event> events = eventRepository.findAll();
      List<EventServiceModel> eventServiceModels = new LinkedList<>();

      events.forEach(e-> eventServiceModels.add(modelMapper.map(e,EventServiceModel.class)));

        return  eventServiceModels;
    }
}
