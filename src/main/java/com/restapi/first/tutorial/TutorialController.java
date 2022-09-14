package com.restapi.first.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialRepositary tutorialRepositary;

    @GetMapping("/tutorials")
    public ResponseEntity<List<TutorialModel>> getAllTutorials(@RequestParam(required = false) String title){
        //System.out.println("title " + title);
        try{
            List<TutorialModel> tutorials = new ArrayList<TutorialModel>();
            if(title == null) {
                tutorialRepositary.findAll().forEach(tutorials::add);
            }else{
                System.out.println("title " + title);
                tutorialRepositary.findByTitle(title).forEach(tutorials::add);
            }

            if(tutorials.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                System.out.println("Tutorial :"+ tutorials);
                return new ResponseEntity<>(tutorials,HttpStatus.OK);
            }

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
@GetMapping("/tutorials/{id}")
    public ResponseEntity<TutorialModel> getTutorialById(@PathVariable("id") long id){
        try {
            Optional<TutorialModel> tutorialData = tutorialRepositary.findById(id);
            if(tutorialData.isPresent()){
                return  new ResponseEntity<>(tutorialData.get(),HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
@DeleteMapping("/tutorials/{id}")
public ResponseEntity<String> deleteById(@PathVariable("id") long id){
        try {
            tutorialRepositary.deleteById(id);
//            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return  new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
}

    @PostMapping("/tutorials")
    public ResponseEntity<TutorialModel> createTutorial(@RequestBody TutorialModel tutorial){
       // System.out.println(tutorial.getTitle());
        try {
            TutorialModel _tutorial = tutorialRepositary.save(new TutorialModel(tutorial.getTitle(),tutorial.getDescription(),false));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
