package com.mint_digital_bank.card_verification.test_project_card_verification.services;

import com.mint_digital_bank.card_verification.test_project_card_verification.entities.CardInfo;
import com.mint_digital_bank.card_verification.test_project_card_verification.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CardService {
    @Autowired private CardRepository cardRepository;
    @Autowired private ClientService clientService;

    public CardInfo getVerifiedCard(String cardNumber){

        //check if card id already in the database
        CardInfo cardInfo = cardRepository.findByCardNumber(cardNumber);

        //verify and return card, if not already verified before(i.e. if not in the database)
        if(cardInfo ==null)
            cardInfo =clientService.verifyCard(cardNumber);

        //increment the card hit
        cardInfo.setHitCount(cardInfo.getHitCount()+1);

        //save verified card
       return cardRepository.save(cardInfo);

    }

    public Map<String,Object> getHitCount(int start, int limit){

        Pageable pageable = PageRequest.of(start,limit);
        Page<CardInfo> cardInfos = cardRepository.findAllByOrderByVerifiedAt(pageable);

        int size = cardInfos.getSize();
        Map<String,Integer> payload = cardInfos.stream().collect(Collectors.toMap(CardInfo::getCardNumber,CardInfo::getHitCount));

        Map<String,Object> response = new LinkedHashMap<>();
        response.put("success",true);
        response.put("start",start);
        response.put("limit",limit);
        response.put("size",size);
        response.put("payload",payload);
        return response;
    }

}
