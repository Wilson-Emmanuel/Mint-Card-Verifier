package com.mint_digital_bank.card_verification.test_project_card_verification.entities;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cards")
public class CardInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,updatable = false,unique = true)
    private Long id;

    @Column(length = 16)
    private String cardNumber;

    @Column(name = "hit_count")
    private Integer hitCount = 0;

    @Embedded
    private Card card;

    @Column(name = "verified_at")
    @CreatedDate
    private Date verifiedAt;

    public CardInfo(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Card getCard() {
        return card;
    }

    public Integer getHitCount() {
        return hitCount;
    }

    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }

    public Date getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Date verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
