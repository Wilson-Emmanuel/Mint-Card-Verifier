package com.mint_digital_bank.card_verification.test_project_card_verification.repositories;

import com.mint_digital_bank.card_verification.test_project_card_verification.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
