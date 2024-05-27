package com.mini.autorizador.intera.vr.repository.transaction;

import com.mini.autorizador.intera.vr.entity.transaction.Transaction;
import com.mini.autorizador.intera.vr.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
