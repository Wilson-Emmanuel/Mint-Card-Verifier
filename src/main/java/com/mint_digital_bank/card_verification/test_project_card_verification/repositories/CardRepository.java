package com.mint_digital_bank.card_verification.test_project_card_verification.repositories;

import com.mint_digital_bank.card_verification.test_project_card_verification.entities.CardInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardInfo,Integer> {

    CardInfo findByCardNumber(String cardNumber);

    Page<CardInfo> findAllByOrderByVerifiedAt(Pageable pageable);
}
