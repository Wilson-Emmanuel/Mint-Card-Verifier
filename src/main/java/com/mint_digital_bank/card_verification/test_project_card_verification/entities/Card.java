package com.mint_digital_bank.card_verification.test_project_card_verification.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,updatable = false,unique = true)
    private Integer id;

    @Column(length = 16)
    @JsonIgnore
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

    public Card(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
