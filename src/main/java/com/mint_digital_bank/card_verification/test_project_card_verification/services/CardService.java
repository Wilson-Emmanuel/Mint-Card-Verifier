package com.mint_digital_bank.card_verification.test_project_card_verification.services;

import com.mint_digital_bank.card_verification.test_project_card_verification.entities.Card;
import com.mint_digital_bank.card_verification.test_project_card_verification.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CardService {
    @Autowired private CardRepository cardRepository;
    @Autowired private ClientService clientService;

    public Map<String,Object> getVerifiedCard(String cardNumber){

        //verify and return card
        Card card =clientService.verifyCard(cardNumber);

        //save verified card
        cardRepository.save(card);

        //create the response payload that will be returned to our client using our card verification service
        Map<String,Object> responsePayload = new LinkedHashMap<>();
        responsePayload.put("success",true);
        responsePayload.put("payload",card);
        return responsePayload;
    }

}
