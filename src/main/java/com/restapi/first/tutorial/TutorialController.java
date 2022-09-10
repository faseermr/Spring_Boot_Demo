package com.restapi.first.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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
                return new ResponseEntity<>(tutorials,HttpStatus.OK);
            }

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
