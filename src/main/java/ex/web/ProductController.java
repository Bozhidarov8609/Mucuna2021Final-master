package ex.web;

import ex.model.binding.AddProductBindingModel;
import ex.model.service.ProductServiceModel;
import ex.model.view.ProductViewModel;
import ex.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("add")
    public String addProduct(Model model){
        if(!model.containsAttribute("addProductBindingModel")){
            model.addAttribute("addProductBindingModel",new AddProductBindingModel());
        }
        return "products/add-product";
    }

    @PostMapping("add")
    public String addProductConfirm(@Valid @ModelAttribute AddProductBindingModel addProductBindingModel,
                                    BindingResult bindingResult, Principal principal, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addProductBindingModel",addProductBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProductBindingModel",bindingResult);
        return "redirect:/products/add";
        }else {
            System.out.println();
            ProductServiceModel productServiceModel = modelMapper.map(addProductBindingModel,ProductServiceModel.class);
            productService.addProduct(productServiceModel,principal);

            return "redirect:/home";
        }


    }
    @GetMapping("/all")
    public ModelAndView allProducts(ModelAndView modelAndView, Principal principal){


        List<ProductServiceModel> list = productService.showAllProducts();
        modelAndView.addObject("allProducts",list.stream()
        .map(p->modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList()));
modelAndView.setViewName("products/all-products");
        return modelAndView;

    }
    @GetMapping("/myProducts")
    public ModelAndView myProducts(ModelAndView modelAndView,Principal principal){
        List<ProductServiceModel> list = productService.showMyProducts(principal);
        modelAndView.addObject("allMyProducts",list.stream()
                .map(p->modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList()));
        modelAndView.setViewName("products/my-products");

     BigDecimal totalEarnedMoney =  productService.allEarnedMoney(principal);
     modelAndView.addObject("totalEarnedMoney",totalEarnedMoney);


        return modelAndView;


    }
    @GetMapping("buy/{id}")
    public String buyProduct(@PathVariable("id") Long id, Principal principal){

       productService.buyProduct(id,principal);
        return "redirect:/home";
    }
    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){

              productService.deleteProduct(id);
        return "redirect:/products/myProducts";
    }



}
