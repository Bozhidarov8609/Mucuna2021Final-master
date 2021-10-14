package ex.service;

import ex.model.service.VetServiceModel;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface VetService {

    void registerVet(VetServiceModel map) throws IOException;
    VetServiceModel findVetBy(String vetName);

    List<VetServiceModel> findAll();

    void updateVet(VetServiceModel vetServiceModel, Principal principal) throws IOException;


    long countOfVets();
}
