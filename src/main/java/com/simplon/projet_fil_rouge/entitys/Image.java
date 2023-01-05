package com.simplon.projet_fil_rouge.entitys;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //Either Books or groups doit être non null
    @ManyToMany
    private List<Book> books;

    @ManyToMany
    private List<Group> groups;
}
