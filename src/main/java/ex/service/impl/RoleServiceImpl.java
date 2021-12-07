package ex.service.impl;

import ex.model.entity.Role;
import ex.model.entity.enums.RoleCategoryName;
import ex.repository.RoleRepository;
import ex.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;


@Service
public class RoleServiceImpl implements RoleService {
private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init(){

//        if(this.roleRepository.count()==0){
//            Role admin = new Role(RoleCategoryName.ADMIN);
//            Role user = new Role(RoleCategoryName.USER);
//            Role client = new Role(RoleCategoryName.CLIENT);
//
//            this.roleRepository.saveAndFlush(admin);
//            this.roleRepository.saveAndFlush(user);
//            this.roleRepository.saveAndFlush(client);
//        }
    }

}
