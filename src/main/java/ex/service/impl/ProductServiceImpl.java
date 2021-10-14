package ex.service.impl;

import ex.model.entity.Dealer;
import ex.model.entity.Product;
import ex.model.entity.UserEntity;
import ex.model.service.ProductServiceModel;
import ex.repository.DealerRepository;
import ex.repository.ProductRepository;
import ex.repository.UserRepository;
import ex.service.CloudinaryService;
import ex.service.EmailSenderService;
import ex.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
   private final ProductRepository productRepository;
   private final DealerRepository dealerRepository;
   private final ModelMapper modelMapper;
   private final UserRepository userRepository;
   private final CloudinaryService cloudinaryService;
   private final EmailSenderService emailSenderService;


    public ProductServiceImpl(ProductRepository productRepository, DealerRepository dealerRepository, ModelMapper modelMapper, UserRepository userRepository, CloudinaryService cloudinaryService, EmailSenderService emailSenderService) {
        this.productRepository = productRepository;
        this.dealerRepository = dealerRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel, Principal principal) throws IOException {
        Product product = modelMapper.map(productServiceModel, Product.class);
        System.out.println();
        UserEntity userEntity = userRepository.findByUsername(principal.getName()).orElse(null);
Long id =userEntity.getDealer().getId();
        Dealer dealer = dealerRepository.findById(id).orElse(null);
        product.setDealer(dealer);
        MultipartFile multipartFile=  productServiceModel.getImage();
        String img = this.cloudinaryService.uploadImage(multipartFile);
        product.setPicture(img);
        Set<Product> products;

        if(dealer.getProducts().size()!=0){

           products  = dealer.getProducts();
        }else {
            products=new HashSet<>();
        }

        products.add(product);
        dealer.setProducts(products);
        productRepository.saveAndFlush(product);
        dealerRepository.saveAndFlush(dealer);

    }

    @Override
    public List<ProductServiceModel> showAllProducts() {
      List<ProductServiceModel> productServiceModels = new LinkedList<>();
      productRepository.findAll().forEach(product -> productServiceModels.add(modelMapper.map(product,ProductServiceModel.class)));
      return productServiceModels;
    }

    @Override
    public void buyProduct(Long id, Principal principal) {
        System.out.println();
        UserEntity user = userRepository.findByUsername(principal.getName()).orElse(null);
        Product product = productRepository.findById(id).orElse(null);
        Dealer dealer = dealerRepository.findById(product.getDealer().getId()).orElse(null);
        UserEntity dealerUser = userRepository.findById(dealer.getUserEntity().getId()).orElse(null);
        emailSenderService.sendMail(dealerUser.getEmail(),"Buy Product","Hi you need to send "+ product.getName()+" to "+user.getUsername()+". For more information about address use "+user.getEmail());
        emailSenderService.sendMail(user.getEmail(),product.getName(),"Hi "+user.getUsername()+" , today you will be contact for delivery information");
        BigDecimal sum = dealer.getEarnedMoney();
        BigDecimal totalSum = sum.add(product.getPrice());

        dealer.setEarnedMoney(totalSum);
        dealerRepository.saveAndFlush(dealer);




    }

    @Override
    public List<ProductServiceModel> showMyProducts(Principal principal) {
        List<ProductServiceModel> productServiceModels = new LinkedList<>();
Dealer dealer = dealerRepository.findByUserEntityId(userRepository.findByUsername(principal.getName()).get().getId());
dealer.getProducts().forEach(product -> productServiceModels.add(modelMapper.map(product,ProductServiceModel.class)));


        return productServiceModels;
    }

    @Override
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id).orElse(null);
        this.productRepository.delete(product);
        long count = productRepository.count();
    }

    @Override
    public BigDecimal allEarnedMoney(Principal principal){

        Optional<UserEntity> userEntity = userRepository.findByUsername(principal.getName());

        return  userEntity.get().getDealer().getEarnedMoney();
    }
}
