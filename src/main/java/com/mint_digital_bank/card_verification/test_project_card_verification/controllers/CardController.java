package com.mint_digital_bank.card_verification.test_project_card_verification.controllers;

import com.mint_digital_bank.card_verification.test_project_card_verification.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @Autowired private CardService cardService;

    @GetMapping(value = "verify/{card_number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> verify(@Valid @NotNull(message = "Card number is required")
                           @Pattern(regexp = "^[0-9]{6}|[0-9]{8}|[0-9]{16}$", message = "Invalid card number")
                           @PathVariable(value = "card_number") String cardNumber){

        return new ResponseEntity<>(cardService.getVerifiedCard(cardNumber), HttpStatus.OK);
    }

    @GetMapping(value = "stats",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> countHit(@Valid @Min(value = 1)
                                                       @RequestParam(value = "start", defaultValue = "1") int start,
                                                       @Valid @Min(value = 1)
                                                       @RequestParam(value = "limit", defaultValue = "3") int limit){
        Map<String,Object> ret = new LinkedHashMap<>();
        ret.put("start",start);
        ret.put("limit",limit);
        return new ResponseEntity<>(ret,HttpStatus.OK);
    }
}
