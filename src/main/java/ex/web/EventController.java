package ex.web;

import ex.model.binding.AddEventBindingModel;
import ex.model.service.EventServiceModel;
import ex.service.EventService;
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

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final ModelMapper modelMapper;

    public EventController(EventService eventService, ModelMapper modelMapper) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String addEvent(Model model){
        if (!model.containsAttribute("addEventBindingModel")){
            model.addAttribute("addEventBindingModel", new AddEventBindingModel());
        }
        return "events/add-event";
    }
    @PostMapping("/add")
    public String addEventConfirm(@Valid @ModelAttribute AddEventBindingModel addEventBindingModel,
                                  BindingResult bindingResult, Principal principal,
                                  RedirectAttributes redirectAttributes) throws IOException {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addEventBindingModel",addEventBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addEventBindingModel",bindingResult);
            return "redirect:add";
        }else {

            EventServiceModel eventServiceModel= modelMapper.map(addEventBindingModel,EventServiceModel.class);

eventService.addEvent(eventServiceModel,principal);
            return "redirect:/home";
        }
    }
    @GetMapping("/all")
    public ModelAndView allEvent(ModelAndView modelAndView){

        modelAndView.addObject("allEvents", eventService.findAllEvent());
        modelAndView.setViewName("events/all-events");
        return modelAndView;
    }



}
