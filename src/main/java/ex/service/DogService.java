package ex.service;

import ex.model.service.DogServiceModel;

import java.io.IOException;
import java.util.List;

public interface DogService {

    void addDog(DogServiceModel dogServiceModel) throws IOException;

    List<DogServiceModel> allApprovedDogs();

    void purgeCache() ;

    DogServiceModel findById(Long id);

    List<DogServiceModel> findAllNotApprovedDogs();

    void changeStatus(Long id);

    void deleteDog(Long id);

    Long countOfDogs();
}
