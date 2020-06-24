package com.mint_digital_bank.card_verification.test_project_card_verification.controllers;

import com.mint_digital_bank.card_verification.test_project_card_verification.entities.Card;
import com.mint_digital_bank.card_verification.test_project_card_verification.entities.CardInfo;
import com.mint_digital_bank.card_verification.test_project_card_verification.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("card-scheme")
@Validated
public class CardController {

    private static final String TOPIC = "com.ng.vela.even.card_verified";
    @Autowired private CardService cardService;

    @Autowired private KafkaTemplate<String, Card> kafkaTemplate;

    /**
     * This endpoint verifies the validity of the card(credit or debit) using the supplied card number
     * we use external service like @link https://www.binlist.net/ to verify the card
     * @param cardNumber this must be between 6 and 16
     * @return
     */
    @GetMapping(value = "verify/{card_number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> verify(@Valid @NotNull(message = "Card number is required")
                           @Pattern(regexp = "^[0-9]{6,16}$", message = "Invalid card number")
                           @PathVariable(value = "card_number") String cardNumber){
        //Note that the first 6 valid number of a card is enough to get the information required
        //(scheme, type and bank)
        cardNumber = cardNumber.substring(0,6);

        CardInfo cardInfo = cardService.getVerifiedCard(cardNumber);
        //create the response payload that will be returned to our client using our card verification service
        Map<String,Object> responsePayload = new LinkedHashMap<>();
        responsePayload.put("success",true);
        responsePayload.put("payload",cardInfo.getCard());

        //publish the payload on kafka
        kafkaTemplate.send(TOPIC,cardInfo.getCard());
        return new ResponseEntity<>(responsePayload, HttpStatus.OK);
    }

    /**
     * This endpoint retrieves the hit count of card verifications in paginated fashion
     * Verification are sorted by date in descending order
     * Note: Invalid card verifications are not stored in the database, hence hit count only returns valid card verifications
     * @param start - page to return
     * @param limit - size per page
     * @return
     */
    @GetMapping(value = "stats",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> countHit(@Valid @Min(value = 1)
                                                       @RequestParam(value = "start", defaultValue = "1") int start,
                                                       @Valid @Min(value = 1)
                                                       @RequestParam(value = "limit", defaultValue = "3") int limit){

        return new ResponseEntity<>(cardService.getHitCount( start,  limit),HttpStatus.OK);
    }
}
