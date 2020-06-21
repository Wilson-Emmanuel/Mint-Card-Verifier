package com.mint_digital_bank.card_verification.test_project_card_verification.errors;

public class CardNotFoundException extends RuntimeException {
    String cardNumber;
    public CardNotFoundException(String message, String cardNumber){
        super(message);
        this.cardNumber = cardNumber;
    }

    public String getCardNumber(){
        return this.cardNumber;
    }
}
