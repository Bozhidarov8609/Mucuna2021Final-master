package ex.service.impl;

import ex.model.entity.Dealer;
import ex.model.entity.Role;
import ex.model.entity.UserEntity;
import ex.model.entity.enums.AuthenticationProvider;
import ex.model.entity.enums.RoleCategoryName;
import ex.model.service.DealerServiceModel;
import ex.model.service.UserServiceModel;
import ex.repository.RoleRepository;
import ex.repository.UserRepository;
import ex.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final DBUserService dbUserService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder encoder, DBUserService dbUserService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.dbUserService = dbUserService;
    }

    @PostConstruct
    public void init() {

        if (userRepository.count() == 0) {

            if (roleRepository.count() == 0) {
                Role admin = new Role(RoleCategoryName.ADMIN);
                Role user = new Role(RoleCategoryName.USER);
                Role client = new Role(RoleCategoryName.CLIENT);

                this.roleRepository.saveAndFlush(admin);
                this.roleRepository.saveAndFlush(user);
                this.roleRepository.saveAndFlush(client);
            }


            UserEntity admin = new UserEntity();
            admin
                    .setUsername("Bozhidarov86")
                    .setEmail("bozhidarov_86@mail.bg")
                    .setPassword(encoder.encode("12345678"))
                    .setRole(roleRepository.findById(1))
                    .setTown("Varna");
            userRepository.saveAndFlush(admin);
            UserEntity user = new UserEntity();
            user
                    .setUsername("Electra")
                    .setEmail("electra@abv.bg")
                    .setPassword(encoder.encode("12345678"))
                    .setRole(roleRepository.findById(2))
                    .setTown("Varna");
            userRepository.saveAndFlush(user);
            UserEntity client = new UserEntity();
            client
                    .setUsername("Vet")
                    .setEmail("vet@mail.bg")
                    .setPassword(encoder.encode("12345678"))
                    .setRole(roleRepository.findById(3))
                    .setTown("Varna");
            userRepository.saveAndFlush(client);

        }

    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {


        UserEntity userEntity = this.modelMapper.map(userServiceModel, UserEntity.class);

        userEntity.setPassword(encoder.encode(userServiceModel.getPassword()));
        Role role = roleRepository.findById(2);
        userEntity.setRole(role);
        userEntity = userRepository.save(userEntity);

        UserDetails principal = dbUserService.loadUserByUsername(userEntity.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                userEntity.getPassword(),
                principal.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return modelMapper.map(userEntity, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsernameAndEmail(String username, String email) {
        UserEntity userEntity = userRepository.findByUsernameAndEmail(username, email).orElse(null);
        if (userEntity != null) {

            return modelMapper.map(userEntity, UserServiceModel.class);
        } else
            return null;

    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {

        UserEntity userEntity = this.userRepository.findByUsernameAndPassword(username, password).orElse(null);

        if (userEntity != null) {

            return modelMapper.map(userEntity, UserServiceModel.class);
        } else
            return null;
    }

    @Override
    public UserEntity findByName(String name) {
        return userRepository.findByUsername(name).orElse(null);
    }

    @Override
    public void setDealer(UserEntity userEntity, DealerServiceModel dealerServiceModel1) {

        Dealer dealer = modelMapper.map(dealerServiceModel1, Dealer.class);
        userEntity.setDealer(dealer);
        userRepository.saveAndFlush(userEntity);

    }

    @Override
    public List<UserServiceModel> findAllUsersWithoutAdminRole() {

        List<UserEntity> userEntities = userRepository.findAllWithoutAdminRole();
        List<UserServiceModel> userServiceModels = new LinkedList<>();
        userEntities.forEach(u -> userServiceModels.add(modelMapper.map(u, UserServiceModel.class)));
        return userServiceModels;
    }

    @Override
    public void updateToAdmin(Long id) {
        UserEntity userEntity = userRepository.findByUserId(id);
        userEntity.setRole(roleRepository.findById(1));
        userRepository.saveAndFlush(userEntity);

    }

    @Override
    public void registerUserAfterOAuthLoginSuccess(String email, String name, AuthenticationProvider authProvider) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(name);
        userEntity.setPassword(encoder.encode("12345678"));
        Role role = roleRepository.findById(2);
        userEntity.setRole(role);
        userEntity.setAuthProvider(authProvider);
        userEntity.setEmail(email);
        userEntity.setTown("Unknown");
        userRepository.save(userEntity);


        UserDetails principal = dbUserService.loadUserByUsername(userEntity.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                userEntity.getPassword(),
                principal.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public long countOfUser() {

        return userRepository.count();
    }
}
