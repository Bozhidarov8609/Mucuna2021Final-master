package ex.service.impl;

import ex.model.entity.UserEntity;
import ex.model.entity.Vet;
import ex.model.service.VetServiceModel;
import ex.repository.RoleRepository;
import ex.repository.UserRepository;
import ex.repository.VetRepository;
import ex.service.CloudinaryService;
import ex.service.VetService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Service
public class VetServiceImpl implements VetService {
    private final VetRepository vetRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;
    private final RoleRepository roleRepository;

    public VetServiceImpl(VetRepository vetRepository, ModelMapper modelMapper, UserRepository userRepository, CloudinaryService cloudinaryService, RoleRepository roleRepository) {
        this.vetRepository = vetRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void registerVet(VetServiceModel map) throws IOException {

        Vet vet = modelMapper.map(map,Vet.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
       String username = userDetails.getUsername();
      UserEntity userEntity = userRepository.findByUsername(username).orElse(null);
      userEntity.setRole(roleRepository.findById(3));
      userRepository.saveAndFlush(userEntity);
vet.setUser(userEntity);
       MultipartFile multipartFile=   map.getImage();
       String img = this.cloudinaryService.uploadImage(multipartFile);
        vet.setPicture(img);
vetRepository.saveAndFlush(vet);
    }

    @Override
    public VetServiceModel findVetBy(String vetName)
    {
        Vet vet = vetRepository.findByVetName(vetName).orElse(null);
        if (vet==null){
            return null;
        }

        return modelMapper.map(vet,VetServiceModel.class);
    }

    @Override
    public List<VetServiceModel> findAll() {
        List<VetServiceModel> vets = new LinkedList<>();
         vetRepository.findAll().forEach(v->vets.add(modelMapper.map(v,VetServiceModel.class)));
         return vets;
    }

    @Override
    public void updateVet(VetServiceModel vetServiceModel, Principal principal) throws IOException {

        UserEntity userEntity =userRepository.findByUsername(principal.getName()).orElse(null);
        Long id = userEntity.getId();
        Vet vet= vetRepository.findByUserEntityId(id);
        vet.setAddress(vetServiceModel.getAddress());
        vet.setPhoneNumber(vetServiceModel.getPhoneNumber());
        vet.setService(vetServiceModel.getService());
        MultipartFile multipartFile=   vetServiceModel.getImage();
        String img = this.cloudinaryService.uploadImage(multipartFile);
        vet.setPicture(img);
        vetRepository.saveAndFlush(vet);
    }

    @Override
    public long countOfVets() {

        return vetRepository.count();
    }


}
