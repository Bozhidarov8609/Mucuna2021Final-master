package ex.service.impl;

import ex.model.entity.Dog;
import ex.model.entity.UserEntity;
import ex.model.service.DogServiceModel;
import ex.repository.DogRepository;
import ex.repository.UserRepository;
import ex.service.CloudinaryService;
import ex.service.DogService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class DogServiceImpl implements DogService {
    private final DogRepository dogRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;
    private final Logger LOGGER = LoggerFactory.getLogger(DogService.class);


    public DogServiceImpl(DogRepository dogRepository, ModelMapper modelMapper, UserRepository userRepository, CloudinaryService cloudinaryService) {
        this.dogRepository = dogRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
    }


    @Override
    public void addDog(DogServiceModel dogServiceModel) throws IOException {

        Dog dog = modelMapper.map(dogServiceModel, Dog.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        UserEntity userEntity = modelMapper.map(userRepository.findByUsername(username).orElse(null), UserEntity.class);
        dog.setUserEntity(userEntity);
        MultipartFile multipartFile = dogServiceModel.getImage();
        String img = this.cloudinaryService.uploadImage(multipartFile);

        dog.setPicture(img);
        dog.setApproved(false);
        dogRepository.save(dog);
    }

    @Override
    @Cacheable("allDogs")
    public List<DogServiceModel> allApprovedDogs() {

        List<Dog> dogs = dogRepository.findAllApprovedDogs();
        List<DogServiceModel> dogServiceModels = new LinkedList<>();

        dogs.forEach(d -> dogServiceModels.add(modelMapper.map(d, DogServiceModel.class)));
        LOGGER.info("Making complicated dogs calculations...");
        return dogServiceModels;
    }


    @Override

    public DogServiceModel findById(Long id) {

        Dog dog = dogRepository.findById(id).orElse(null);

        return modelMapper.map(dog, DogServiceModel.class);
    }

    @Override
    public List<DogServiceModel> findAllNotApprovedDogs() {
        List<Dog> dogs = this.dogRepository.findByNotApprevedDogs();
        List<DogServiceModel> dogServiceModels = new LinkedList<>();
        dogs.forEach(d -> dogServiceModels.add(modelMapper.map(d, DogServiceModel.class)));

        return dogServiceModels;
    }

    @Override
    public void changeStatus(Long id) {

        Dog dog = this.dogRepository.findById(id).orElse(null);

        assert dog != null;
        dog.setApproved(true);
        dogRepository.save(dog);

    }

    @Override
    public void deleteDog(Long id) {

        Dog dog = this.dogRepository.findById(id).orElse(null);
        dog.setUserEntity(null);
        dogRepository.save(dog);
        this.dogRepository.delete(dog);
    }

    @Override
    public Long countOfDogs() {

        return dogRepository.count();
    }

    @Override
    @CacheEvict(cacheNames = "allDogs", allEntries = true)
    public void purgeCache() {
        LOGGER.info("Purging cache...");
    }

    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }

    @Scheduled(cron = "0  9 * * * *")
    public void refresh() {

        int numOfNotApprovedDogs = dogRepository.findByNotApprevedDogs().size();
        if (numOfNotApprovedDogs != 0)
            LOGGER.info("Have " + numOfNotApprovedDogs + " not approved dog");

    }


}


