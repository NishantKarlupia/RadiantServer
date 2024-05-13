package com.stone.stone.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
// import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
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
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gid;

    @Column(nullable = false)
    private String gamename;

    @Column(length = 1000000)
    private String description;
    @Column(length = 1000000)
    private String requirements;

    // @Lob
    @Column(length = 1000000)
    private String coverImage;

    // @Lob
    // @ElementCollection
    @Column(length = 4000000)
    private List<String> gamePlayImages;



    // private Set<String>images;
    @Column(length = 100000)
    private String video;
    
    private Long price;
    private Long discount;
    private String categories;
    private String platform;
    private String publisher;
    private String releaseDate;
    private Long likes=0L;
    private Long totalDownloads=0L;

    @JsonIgnore
    @OneToMany(mappedBy = "game",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Review>reviews=new HashSet<>();

}
