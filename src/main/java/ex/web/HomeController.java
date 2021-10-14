package ex.web;

import ex.model.view.DogPictureAndNameView;
import ex.service.DogService;
import ex.service.PuppyService;
import ex.service.UserService;
import ex.service.VetService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final DogService dogService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final PuppyService puppyService;
    private final VetService vetService;

    public HomeController(DogService dogService, UserService userService, ModelMapper modelMapper, PuppyService puppyService, VetService vetService) {
        this.dogService = dogService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.puppyService = puppyService;
        this.vetService = vetService;
    }


    @GetMapping("/home")

    public ModelAndView home( ModelAndView modelAndView) {

        modelAndView.addObject("dogs",dogService.allApprovedDogs()
                .stream()
                .map(dogServiceModel->modelMapper.map(dogServiceModel, DogPictureAndNameView.class))
                .collect(Collectors.toList()));
        modelAndView.setViewName("home");
        return modelAndView;
    }
    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.addObject("userCount",userService.countOfUser());
        modelAndView.addObject("dogCount",dogService.countOfDogs());
        modelAndView.addObject("puppyCount",puppyService.countOfPuppies());
        modelAndView.addObject("vetCount",vetService.countOfVets());

        modelAndView.setViewName("index");
        return modelAndView;
    }
}
