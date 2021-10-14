package ex.service;

import ex.model.entity.UserEntity;
import ex.model.entity.enums.AuthenticationProvider;
import ex.model.service.DealerServiceModel;
import ex.model.service.UserServiceModel;

import java.util.List;


public interface UserService {




    UserServiceModel registerUser(UserServiceModel userServiceModel);

   UserServiceModel findByUsernameAndEmail(String username, String email);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    UserEntity findByName(String name);

    void setDealer(UserEntity userEntity, DealerServiceModel dealerServiceModel1);

   List<UserServiceModel> findAllUsersWithoutAdminRole();

    void updateToAdmin(Long id);

    void registerUserAfterOAuthLoginSuccess(String email, String name, AuthenticationProvider facebook);

    long countOfUser();
}
