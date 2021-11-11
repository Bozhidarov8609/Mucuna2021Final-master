package ex.web;

import ex.model.binding.UpdateVetBindingModel;
import ex.model.binding.VetRegisterBindingModel;
import ex.model.entity.UserEntity;
import ex.model.service.VetServiceModel;
import ex.model.view.VetViewModel;
import ex.repository.UserRepository;
import ex.repository.VetRepository;
import ex.service.VetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/vets")
public class VetsController {

    private final VetService vetService;
    private final ModelMapper modelMapper;
    private final VetRepository vetRepository;
    private final UserRepository userRepository;

    public VetsController(VetService vetService, ModelMapper modelMapper, VetRepository vetRepository, UserRepository userRepository) {
        this.vetService = vetService;
        this.modelMapper = modelMapper;
        this.vetRepository = vetRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("register")
    public String registerVet(Model model){

        if(!model.containsAttribute("vetRegisterBindingModel")){
            model.addAttribute("vetRegisterBindingModel", new VetRegisterBindingModel());
            model.addAttribute("dealerExist",false);
                   }
        return "vets/vet-register";
    }

    @PostMapping("register")
    public String confirmRegisterVet(@Valid @ModelAttribute VetRegisterBindingModel vetRegisterBindingModel,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("vetRegisterBindingModel",vetRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.vetRegisterBindingModel",bindingResult);
        return "redirect:/vets/register";
        }
        else {
            VetServiceModel vetServiceModel = vetService.findVetBy(vetRegisterBindingModel.getVetName());
            if (vetServiceModel != null) {
                redirectAttributes.addFlashAttribute("vetRegisterBindingModel", vetRegisterBindingModel);
                redirectAttributes.addFlashAttribute("dealerExist", true);
                return "redirect:/vets/register";
            } else {

                vetService.registerVet(modelMapper.map(vetRegisterBindingModel,VetServiceModel.class));


                return "redirect:/home";
            }
        }
    }
    @GetMapping("/all")
    public ModelAndView allVets(ModelAndView modelAndView){

        List<VetServiceModel> vets=vetService.findAll();
        modelAndView.addObject("allVets",vets
                .stream()
                .map(v->modelMapper.map(v, VetViewModel.class))
                .collect(Collectors.toList()));
        modelAndView.setViewName("vets/all-vets");

        return modelAndView;
    }
    @GetMapping("/update")
    public String updateVet(Model model, Principal principal){

        if(!model.containsAttribute("updateVetBindingModel")){
            model.addAttribute("updateVetBindingModel", new UpdateVetBindingModel());
        }
        Optional<UserEntity> userEntity = userRepository.findByUsername(principal.getName());

        model.addAttribute("vet",vetRepository.findById(userEntity.get().getDealer().getId()));

        return "vets/update-vet";
    }

    @PostMapping("/update")
    public String updateConfirm(@Valid @ModelAttribute UpdateVetBindingModel updateVetBindingModel
            ,BindingResult bindingResult
            ,RedirectAttributes redirectAttributes
    ,Principal principal) throws IOException {

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("vetRegisterBindingModel",updateVetBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateVetBindingModel",bindingResult);
       return "redirect:/vets/update";
        }else {
            VetServiceModel vetServiceModel = modelMapper.map(updateVetBindingModel,VetServiceModel.class);
            vetService.updateVet(vetServiceModel,  principal);
            return "redirect:/home";
        }
        }


}
