package ex.service.impl;

import ex.model.entity.Puppy;
import ex.model.entity.Dog;
import ex.model.entity.UserEntity;
import ex.model.service.DogServiceModel;
import ex.model.service.PuppyServiceModel;
import ex.model.service.UserServiceModel;
import ex.repository.PuppyRepository;
import ex.repository.DogRepository;
import ex.repository.UserRepository;
import ex.service.PuppyService;
import ex.service.CloudinaryService;
import ex.web.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class PuppyServiceImpl implements PuppyService {

    private final PuppyRepository puppyRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final UserRepository userRepository;
    private final DogRepository dogRepository;


    public PuppyServiceImpl(PuppyRepository puppyRepository, ModelMapper modelMapper, CloudinaryService cloudinaryService, UserRepository userRepository, DogRepository dogRepository) {
        this.puppyRepository = puppyRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.userRepository = userRepository;
        this.dogRepository = dogRepository;

    }


    @Override
    public void addAd(PuppyServiceModel puppyServiceModel) throws IOException {

        Puppy puppy = modelMapper.map(puppyServiceModel, Puppy.class);

        MultipartFile multipartFile = puppyServiceModel.getImage();
        String img = this.cloudinaryService.uploadImage(multipartFile);
        puppy.setPicture(img);
        Dog dog = dogRepository.findByName(puppyServiceModel.getDog());
        puppy.setDog(dog);
        Set<Puppy> puppies = dog.getAd();
        puppies.add(puppy);
        dog.setAd(puppies);
        dogRepository.save(dog);
         puppyRepository.saveAndFlush(puppy);


            }

    @Override
    public List<Dog> findParent(String name) {

        UserEntity userEntity = userRepository.findByUsername(name).orElseGet(null);

        List<Dog> dogs = new LinkedList<>();
                userEntity.getDog().forEach(d->dogs.add(d));
if(dogs.isEmpty()){
    Dog dog = new Dog();
    dog.setUserEntity(userEntity);
    dog.setApproved(true);
    dog.setName("Unknown");
    dogs.add(dog);
}
        return dogs;
    }

    @Override
    public List<Puppy> showAllPuppies() {


        return this.puppyRepository.findAll();

    }

    @Override
    public PuppyServiceModel findById(Long id) {

        Puppy puppy = puppyRepository.findById(id).orElse(null);
        PuppyServiceModel puppyServiceModel = modelMapper.map(puppy,PuppyServiceModel.class);
        puppyServiceModel.setDog(puppy.getDog().getName());

        return  puppyServiceModel;
    }

    @Override
    public Long countOfPuppies() {

        return puppyRepository.count();
    }

    @Override
    public void updatePuppy(PuppyServiceModel puppyServiceModel) {

        Puppy puppy = puppyRepository.findById(puppyServiceModel.getId()).orElseThrow(() ->
                new ObjectNotFoundException("Offer with id " + puppyServiceModel.getId() + " not found!"));

    puppy.setDescription(puppyServiceModel.getDescription());
    puppy.setPrice(puppyServiceModel.getPrice());
    puppy.setContact(puppyServiceModel.getContact());

    puppyRepository.save(puppy);

    }

    @Override
    public UserServiceModel findUserWhichAddPuppy(PuppyServiceModel puppyServiceModel) {

        DogServiceModel dogServiceModel =modelMapper.map(dogRepository.findByName(puppyServiceModel.getDog()),DogServiceModel.class);
        UserServiceModel userServiceModel = modelMapper.map(userRepository.findByUserId(dogServiceModel.getUserEntity().getId()),UserServiceModel.class);

        return userServiceModel;
    }

    @Override
    public void deletePuppy(Long id) {
        puppyRepository.deleteById(id);
    }


}
