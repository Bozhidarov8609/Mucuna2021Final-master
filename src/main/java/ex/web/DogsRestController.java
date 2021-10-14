package ex.web;


import ex.model.view.DogPictureAndNameView;
import ex.repository.DogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/dogs")
public class DogsRestController {

    private final DogRepository dogRepository;
    private final ModelMapper modelMapper;

    public DogsRestController(DogRepository dogRepository, ModelMapper modelMapper) {
        this.dogRepository = dogRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api")

    public ResponseEntity<List<DogPictureAndNameView>> findAll() {

       List<DogPictureAndNameView> dogPictureAndNameViews = dogRepository.findAll()
        .stream()
        .map(d->{
        return modelMapper.map(d,DogPictureAndNameView.class);
        }).
        collect(Collectors.toList());
        return ResponseEntity
                .ok()
                .body(dogPictureAndNameViews);
    }
}
