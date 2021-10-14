package ex.web;

import ex.model.binding.DealerRegisterBindingModel;
import ex.model.entity.UserEntity;
import ex.model.service.DealerServiceModel;
import ex.repository.DealerRepository;
import ex.service.DealerService;
import ex.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("dealers")
public class DealerController {

    private final DealerService dealerService;
    private final DealerRepository dealerRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public DealerController(DealerService dealerService, DealerRepository dealerRepository, ModelMapper modelMapper, UserService userService) {
        this.dealerService = dealerService;
        this.dealerRepository = dealerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerDealer(Model model){
        if(!model.containsAttribute("dealerRegisterBindingModel")){
            model.addAttribute("dealerRegisterBindingModel",new DealerRegisterBindingModel());
            model.addAttribute("dealerExist",false);
        }

        return "dealers/dealer-register";
    }

    @PostMapping("register")
    public String confirmDealerRegister(@Valid @ModelAttribute DealerRegisterBindingModel dealerRegisterBindingModel
            , BindingResult bindingResult, Principal principal, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("dealerRegisterBindingModel",dealerRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.dealerRegisterBindingModel",bindingResult);
            return "redirect:register";
        } else {
DealerServiceModel dealerServiceModel = dealerService.findDealerByCompanyName(dealerRegisterBindingModel.getCompanyName());
            if (dealerServiceModel != null) {
                redirectAttributes.addFlashAttribute("dealerRegisterBindingModel",dealerRegisterBindingModel);
                redirectAttributes.addFlashAttribute("dealerExist",true);
                return "redirect:register";
            } else {

        DealerServiceModel dealerServiceModel1= dealerService.registerDealer(modelMapper.map(dealerRegisterBindingModel, DealerServiceModel.class));
                UserEntity userEntity = userService.findByName(principal.getName());
                userService.setDealer(userEntity,dealerServiceModel1);


                return "redirect:/home";
            }
        }
    }
}
