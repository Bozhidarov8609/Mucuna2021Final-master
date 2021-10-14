package ex.web;

import ex.model.binding.AddDogBindingModel;
import ex.model.binding.UserRegisterBindingModel;
import ex.model.service.DogServiceModel;
import ex.model.view.DogDetailsViewModel;
import ex.model.view.NotApprovedDogsViewModel;
import ex.service.DogService;
import ex.service.impl.EmailSenderServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/dogs")
public class DogController {

    private final DogService dogService;
    private final ModelMapper modelMapper;
    private final EmailSenderServiceImpl emailSenderService;



    public DogController(DogService dogService, ModelMapper modelMapper, EmailSenderServiceImpl emailSenderService) {
        this.dogService = dogService;
        this.modelMapper = modelMapper;


        this.emailSenderService = emailSenderService;
    }

    @GetMapping("/add")
    public String addDog( Model model){

        if(!model.containsAttribute("addDogBindingModel")){
            model.addAttribute("addDogBindingModel", new AddDogBindingModel());
            model.addAttribute("userRegisterBindingModel",new UserRegisterBindingModel());
        }
        return "dogs/add-dog";
    }

    @PostMapping("/add")
    public String addDogConf(@Valid @ModelAttribute AddDogBindingModel addDogBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes
                            ) throws IOException {

       if (bindingResult.hasErrors()){
           redirectAttributes.addFlashAttribute("addDogBindingModel",addDogBindingModel);
          redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addDogBindingModel",bindingResult);
           return "redirect:add";
       } else {


           dogService.purgeCache();
           dogService.addDog(modelMapper.map(addDogBindingModel, DogServiceModel.class));
emailSenderService.sendMail("mucunateam@gmail.com","New Dog registration","http://localhost:8800/dogs/isApproved");

           return "redirect:/home";
       }
    }

    @GetMapping("/details")
    public ModelAndView viewDetails(@RequestParam("id") Long id, ModelAndView modelAndView){

        DogServiceModel dogServiceModel = dogService.findById(id);
        modelAndView.addObject("dog",modelMapper.map(dogServiceModel, DogDetailsViewModel.class));
        modelAndView.setViewName("dogs/dog-details");
        return modelAndView;
    }

    @GetMapping("/isApproved")
    public ModelAndView isNotApproved(ModelAndView modelAndView){

        List<DogServiceModel> dogServiceModels = dogService.findAllNotApprovedDogs();
        List<NotApprovedDogsViewModel> notApprovedDogsViewModels = new LinkedList<>();
        dogServiceModels.forEach(d->notApprovedDogsViewModels.add(modelMapper.map(d,NotApprovedDogsViewModel.class)));
modelAndView.addObject("notApprovedDogs",notApprovedDogsViewModels);
modelAndView.setViewName("dogs/not-approved-dogs");
        return modelAndView;
    }
    @GetMapping("chahgeStatus/{id}")
public String changeStatus(@PathVariable("id") Long id){
        dogService.purgeCache();
dogService.changeStatus(id);


        return "redirect:/dogs/isApproved";
    }
    @GetMapping("delete/{id}")
    public String deleteDog(@PathVariable("id") Long id){

          dogService.deleteDog(id);


        return "redirect:/dogs/isApproved";
    }

    @GetMapping("/byBreed")
    public String get(){
        return "dogs/by-breed";
    }





}
