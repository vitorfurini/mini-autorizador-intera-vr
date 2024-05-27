package com.mini.autorizador.vr.controller.card;

import com.mini.autorizador.vr.dto.card.CardDTO;
import com.mini.autorizador.vr.service.card.CartaoService;
import exception.ApiException;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
@Api(tags = "Controller Event Type")
public class CartoesController {

    @Autowired
    CartaoService cartaoService;

    @GetMapping("/{cardNumber}")
    public ResponseEntity<Object> getCardByCardNumber(@PathVariable(name = "cardNumber") String cardNumber) {
        var response = cartaoService.findByCardNumber(cardNumber);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/balance/{cardNumber}")
    public ResponseEntity<Object> getBalanceByCardNumber(@PathVariable(name = "cardNumber") String cardNumber)
            throws ApiException {
        var response = cartaoService.getBalanceByCardNumber(cardNumber);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Object> criar(@RequestBody @Valid CardDTO cartaoRequest) throws ApiException {
        var cartao = cartaoService.create(cartaoRequest);

        return new ResponseEntity<>(cartao, HttpStatus.CREATED);
    }

}
