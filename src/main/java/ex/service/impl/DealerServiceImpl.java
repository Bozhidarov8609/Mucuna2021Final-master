package ex.service.impl;

import ex.model.entity.Dealer;
import ex.model.entity.UserEntity;
import ex.model.service.DealerServiceModel;
import ex.repository.DealerRepository;
import ex.repository.RoleRepository;
import ex.repository.UserRepository;
import ex.service.DealerService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class DealerServiceImpl implements DealerService {

    private final DealerRepository dealerRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public DealerServiceImpl(DealerRepository dealerRepository, UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.dealerRepository = dealerRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DealerServiceModel registerDealer(DealerServiceModel dealerServiceModel) {

        Dealer dealer = modelMapper.map(dealerServiceModel,Dealer.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        UserEntity userEntity = userRepository.findByUsername(username).orElse(null);
        userEntity.setRole(roleRepository.findById(3));
        dealer.setUserEntity(userEntity);
        dealer.setEarnedMoney(BigDecimal.valueOf('0'));
        userEntity.setDealer(dealer);

        dealerRepository.saveAndFlush(dealer);
        userRepository.save(userEntity);
        return modelMapper.map(dealer,DealerServiceModel.class);

    }

    @Override
    public DealerServiceModel findDealerByCompanyName(String dealerName)
    {
      Dealer dealer = dealerRepository.findByCompanyName(dealerName).orElse(null);
      if (dealer==null){
          return null;
      }else

        return modelMapper.map(dealer,DealerServiceModel.class);
    }
}
