package com.restapi.first.tutorial;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialRepositary extends JpaRepository<TutorialModel, Long> {
    List<TutorialModel> findByPublished(boolean published);
    //List<TutorialModel> findByTitleContaining(String title);
    List<TutorialModel> findByTitle(String title);

}
