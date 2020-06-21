package com.mint_digital_bank.card_verification.test_project_card_verification.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cards")
@Getter @Setter @NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,updatable = false,unique = true)
    private Integer id;

    @Column(length = 8)
    private String cardNumber;

    @Column
    private String scheme;

    @Column
    private String type;

    @Column
    private String bank;

    public Card(String cardNumber,String scheme, String type, String bank){
        this.cardNumber = cardNumber;
        this.scheme = scheme;
        this.type = type;
        this.bank = bank;
    }

}
