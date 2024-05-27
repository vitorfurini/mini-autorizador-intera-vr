package com.mini.autorizador.intera.vr.service.transaction;

import com.mini.autorizador.intera.vr.dto.transaction.TransactionDTO;
import com.mini.autorizador.intera.vr.dto.transaction.TransactionDTO;
import exception.ApiException;
import exception.transaction.TransactionException;

public interface TransactionService {

    Object createTransaction(TransactionDTO cartaoRequest) throws ApiException, TransactionException;

    void save(TransactionDTO dto);
}
