package com.simplon.projet_fil_rouge.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private int price; //Différent de tarif à cause de possible promo
    private Date dateOrder;
    @Enumerated(EnumType.ORDINAL)
    private StatesOrder statesOrder;
    private int numberOfProducts;

    @ManyToOne //TO_DO check
    private DetailsProduct product;
    @ManyToOne
    private Image image;
    @ManyToOne
    private Customer customer;
}
