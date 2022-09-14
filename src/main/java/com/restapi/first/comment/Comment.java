package com.restapi.first.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.first.tutorial.TutorialModel;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "tutorial_id",nullable = false)
    @JsonIgnore
    private TutorialModel tutorial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TutorialModel getTutorial() {
        return tutorial;
    }

    public void setTutorial(TutorialModel tutorial) {
        this.tutorial = tutorial;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", tutorial=" + tutorial +
                '}';
    }
}
