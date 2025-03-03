package com.example.Query.personalizzate.Controller;

import com.example.Query.personalizzate.Entity.ProdottoEntity;
import com.example.Query.personalizzate.Service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {
    @Autowired
    ProdottoService prodottoService;

    @PostMapping
    public ResponseEntity<ProdottoEntity> creaProdotto(@RequestBody ProdottoEntity prodotto){
    ProdottoEntity prodotto1 = prodottoService.creaProdotto(prodotto);
    return ResponseEntity.ok(prodotto1);
    }

}
