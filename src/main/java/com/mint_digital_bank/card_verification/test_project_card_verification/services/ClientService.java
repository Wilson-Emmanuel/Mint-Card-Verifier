package com.mint_digital_bank.card_verification.test_project_card_verification.services;

import com.mint_digital_bank.card_verification.test_project_card_verification.entities.Card;
import com.mint_digital_bank.card_verification.test_project_card_verification.entities.CardInfo;
import com.mint_digital_bank.card_verification.test_project_card_verification.errors.CardNotFoundException;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    /**
     * This method verifies the validity of a card number against an external resource
     * @param cardNumber - card number to be verified
     * @return
     */
    public CardInfo verifyCard(String cardNumber){

        HttpResponse<JsonNode> response = null;
        try{
            response = Unirest.get("https://lookup.binlist.net/{card_number}")
                    .header("Accept-Version","3")
                    .routeParam("card_number",cardNumber)
                    .asJson();
        }catch (UnirestException ex){
            throw new RuntimeException("Card verification error");
        }

        //check the http response status code
        if(!response.isSuccess()){
            if(response.getStatus() ==404){
                throw new CardNotFoundException("Card is invalid",cardNumber);
            }else if(response.getStatus() ==429){
                throw new RuntimeException("Verification limit exceeded. Please try again after 10 minutes.");
            }else{
                throw new RuntimeException("Card verification error");
            }
        }

        JSONObject payload = response.getBody().getObject();
        if(payload ==null)
            throw new RuntimeException("Card verification error");

        JSONObject bank = payload.getJSONObject("bank");
        //create the card and save it into the db
        CardInfo cardInfo = new CardInfo();
        Card card = new Card();
        cardInfo.setCardNumber(cardNumber);
        card.setBank(bank.optString("name"));
        card.setScheme(payload.optString("scheme"));
        card.setType(payload.optString("type"));
        cardInfo.setCard(card);
        return cardInfo;
    }
}
