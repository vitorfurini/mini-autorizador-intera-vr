package com.mini.autorizador.vr.repository.jpa;

import com.mini.autorizador.vr.entity.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

public interface ICartaoRepositoryJpa extends JpaRepository<Card, Long> {

    Optional<Card> findByCardNumber(@Param("cardNumber") String cardNumber);

    boolean existsByCardNumber(String cardNumber);

    @Modifying
    @Transactional
    @Query("UPDATE Card c "
            + "SET c.balance = :balance "
            + "WHERE c.cardNumber = :cardNumber ")
    void updateBalanceByCardNumber(@Param("balance") BigDecimal balance, @Param("cardNumber") String cardNumber);
}
