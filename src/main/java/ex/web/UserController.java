package ex.web;

import ex.model.binding.UserRegisterBindingModel;
import ex.model.service.UserServiceModel;
import ex.service.UserService;
import ex.service.VetService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.StringReader;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final VetService vetService;


    public UserController(UserService userService, ModelMapper modelMapper, VetService vetService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.vetService = vetService;
    }


    @GetMapping("/register")
    public String register(Model model){

        if (!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel",new UserRegisterBindingModel());
            model.addAttribute("notSame",false);
            model.addAttribute("userExist",false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()|| !userRegisterBindingModel.getConfirmPassword().equals(userRegisterBindingModel.getPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("notSame",true);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",bindingResult);
            return "redirect:register";

        }else {
            UserServiceModel userServiceModel = userService.findByUsernameAndEmail(userRegisterBindingModel.getUsername(),userRegisterBindingModel.getEmail());
if (userServiceModel!=null){
    redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
    redirectAttributes.addFlashAttribute("userExist",true);
    redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",bindingResult);
    return "redirect:register";
}

          this.userService.registerUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

            return "redirect:/home";
        }
    }
    @GetMapping("/client")
    public String register2(){
        return "register-client";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }




    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                            String username, RedirectAttributes redirectAttributes) {


        redirectAttributes.addFlashAttribute("bad_credentials", true);
        redirectAttributes.addFlashAttribute("username", username);




        return   "redirect:/users/login";

    }
    @GetMapping ("/updateRole")
    public ModelAndView updateUserRole(ModelAndView modelAndView){

        modelAndView.addObject("AllUsersWithRoleUserOrClient", userService.findAllUsersWithoutAdminRole());
        modelAndView.setViewName("update-role");
        return modelAndView;

    }
    @GetMapping("update/{id}")
    public String updateRole(@PathVariable("id") Long id){

       userService.updateToAdmin(id);
        return "redirect:/users/updateRole";
    }




}
