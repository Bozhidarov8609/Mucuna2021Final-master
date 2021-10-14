package ex.web;

import ex.model.binding.AddPuppyBindingModel;
import ex.model.entity.Puppy;
import ex.model.entity.Dog;
import ex.model.service.PuppyServiceModel;
import ex.model.view.PuppyViewModel;
import ex.repository.DogRepository;
import ex.service.PuppyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("puppies")
public class PuppyController {

    private final PuppyService puppyService;
    private final ModelMapper modelMapper;
    private final DogRepository dogRepository;



    public PuppyController(PuppyService puppyService, ModelMapper modelMapper, DogRepository dogRepository) {
        this.puppyService = puppyService;
        this.modelMapper = modelMapper;


        this.dogRepository = dogRepository;
    }

    @GetMapping("add")
    public String addAd(Model model, Principal principal) {
        if (!model.containsAttribute("addPuppyBindingModel")) {
            model.addAttribute("addPuppyBindingModel", new AddPuppyBindingModel());

        }

        List<Dog> dogs= puppyService.findParent(principal.getName());
        model.addAttribute("dog",dogs);
        return "puppies/add-puppy";
    }

    @PostMapping("/add")
    public String addAddConfirm(@Valid @ModelAttribute AddPuppyBindingModel addPuppyBindingModel,
                                      BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPuppyBindingModel", addPuppyBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addAdBindingModel", bindingResult);


            return "redirect:/puppies/add";
        } else {

            puppyService.addAd(modelMapper.map(addPuppyBindingModel, PuppyServiceModel.class));

            return  "redirect:/home";


        }
    }
    @GetMapping("/all")
    public ModelAndView allAds(ModelAndView modelAndView){
        List<Puppy> puppies = puppyService.showAllPuppies();
       modelAndView.addObject("allPuppies", puppies
               .stream()
               .map(ad -> modelMapper.map(ad, PuppyViewModel.class))
               .collect(Collectors.toList()));
       modelAndView.setViewName("puppies/all-puppies");
       return modelAndView;

    }
    @GetMapping("/details")
    public ModelAndView viewDetails(@RequestParam("id") Long id, ModelAndView modelAndView){
        System.out.println();

        Puppy puppy = modelMapper.map(puppyService.findById(id),Puppy.class);
        PuppyServiceModel puppyServiceModel = puppyService.findById(id);
       String name = puppyServiceModel.getDog();
       Dog dog = dogRepository.findByName(name);
        puppy.setDog(dogRepository.findByName(name));
        modelAndView.addObject("puppy",modelMapper.map(puppy, PuppyViewModel.class));
        modelAndView.setViewName("puppies/puppy-details");
        return modelAndView;
    }


}
