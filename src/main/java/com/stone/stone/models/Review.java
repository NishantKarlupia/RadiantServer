package com.stone.stone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rid;
    private String title;
    private String description;
    // private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    // private String username;
    // private String userId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Game game;

    
}
