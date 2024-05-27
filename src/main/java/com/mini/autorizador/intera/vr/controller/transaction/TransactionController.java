package com.mini.autorizador.intera.vr.controller.transaction;

import com.mini.autorizador.intera.vr.dto.transaction.TransactionDTO;
import com.mini.autorizador.intera.vr.service.transaction.TransactionService;
import exception.ApiException;
import exception.transaction.TransactionException;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
@Api(tags = "Controller of Transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(path = "/create")
    public ResponseEntity<Object> createTransaction(@RequestBody @Valid TransactionDTO transactionRequest)
            throws ApiException, TransactionException {
        var transaction = transactionService.createTransaction(transactionRequest);

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

}
