package ex.service;

import ex.model.entity.Puppy;
import ex.model.entity.Dog;
import ex.model.service.PuppyServiceModel;

import java.io.IOException;
import java.util.List;

public interface PuppyService {
    void addAd(PuppyServiceModel puppyServiceModel) throws IOException;

    List<Dog> findParent(String name);

    List<Puppy> showAllPuppies();

    PuppyServiceModel findById(Long id);


    Long countOfPuppies();

    void updatePuppy(PuppyServiceModel puppyServiceModel);
}
