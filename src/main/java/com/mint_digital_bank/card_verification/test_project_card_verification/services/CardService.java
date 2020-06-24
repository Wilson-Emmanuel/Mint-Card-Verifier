package com.mint_digital_bank.card_verification.test_project_card_verification.services;

import com.mint_digital_bank.card_verification.test_project_card_verification.entities.CardInfo;
import com.mint_digital_bank.card_verification.test_project_card_verification.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired private CardRepository cardRepository;
    @Autowired private ClientService clientService;

    public CardInfo getVerifiedCard(String cardNumber){

        //verify and return card
        CardInfo cardInfo =clientService.verifyCard(cardNumber);

        //save verified card
       return cardRepository.save(cardInfo);

    }

}
