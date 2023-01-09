package com.simplon.projet_fil_rouge.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String author;
    private String coverPath;
    @Enumerated(EnumType.ORDINAL)
    private GenreBook genre;
    private String summary;
    private int votesAverage;
    private LocalDate dateAdded;

}
