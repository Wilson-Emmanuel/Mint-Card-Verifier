package com.mint_digital_bank.card_verification.test_project_card_verification;

import com.mint_digital_bank.card_verification.test_project_card_verification.entities.Card;
import com.mint_digital_bank.card_verification.test_project_card_verification.entities.CardInfo;
import com.mint_digital_bank.card_verification.test_project_card_verification.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TestProjectCardVerificationApplication {
	@Autowired private CardRepository cardRepository;


	public static void main(String[] args) {
		SpringApplication.run(TestProjectCardVerificationApplication.class, args);
	}

	@PostConstruct
	public void updateDb(){
		List<CardInfo> infos = new ArrayList<>();
		CardInfo info = new CardInfo();
		info.setHitCount(1);
		info.setCardNumber("123456");
		Card card = new Card();
		card.setType("Visa");
		card.setScheme("debit");
		card.setBank("Access");
		info.setCard(card);
		info.setVerifiedAt(new Date());
		infos.add(info);

		info = new CardInfo();
		info.setHitCount(5);
		info.setCardNumber("356478");
		 card = new Card();
		card.setType("Verve");
		card.setScheme("debit");
		card.setBank("Access");
		info.setCard(card);
		info.setVerifiedAt(new Date());
		infos.add(info);

		info = new CardInfo();
		info.setHitCount(10);
		info.setCardNumber("787563");
		card = new Card();
		card.setType("Mastercard");
		card.setScheme("credit");
		card.setBank("Zennith");
		info.setCard(card);
		info.setVerifiedAt(new Date());
		infos.add(info);

		info = new CardInfo();
		info.setHitCount(8);
		info.setCardNumber("455666");
		card = new Card();
		card.setType("Visa");
		card.setScheme("debit");
		card.setBank("UBA");
		info.setCard(card);
		info.setVerifiedAt(new Date());
		infos.add(info);

		info = new CardInfo();
		info.setHitCount(2);
		info.setCardNumber("000563");
		card = new Card();
		card.setType("Verve");
		card.setScheme("debit");
		card.setBank("Eco bank");
		info.setCard(card);
		info.setVerifiedAt(new Date());
		infos.add(info);


		cardRepository.saveAll(infos);
	}
}
