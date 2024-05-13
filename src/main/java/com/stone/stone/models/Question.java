package com.stone.stone.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quesId;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    // user:{userId:""}

    private String title;

    private String description;

    @Column(length = 40000)
    private List<String> images;


    @JsonIgnore
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private Set<Answer> answers=new HashSet<>();

    
}
