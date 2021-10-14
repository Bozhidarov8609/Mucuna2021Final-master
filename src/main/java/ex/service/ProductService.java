package ex.service;

import ex.model.service.ProductServiceModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel, Principal principal) throws IOException;

    List<ProductServiceModel> showAllProducts();

    void buyProduct(Long id, Principal principal);

    List<ProductServiceModel> showMyProducts(Principal principal);

    void deleteProduct(Long id);

    BigDecimal allEarnedMoney(Principal principal);
}
