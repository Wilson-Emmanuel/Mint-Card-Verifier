package com.mint_digital_bank.card_verification.test_project_card_verification.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Card {

    @Column
    private String scheme;

    @Column
    private String type;

    @Column
    private String bank;

    public Card() {
    }

    public Card(String scheme, String type, String bank) {
        this.scheme = scheme;
        this.type = type;
        this.bank = bank;
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
