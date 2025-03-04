package com.example.Query.personalizzate.Controller;

import com.example.Query.personalizzate.Entity.ProdottoEntity;
import com.example.Query.personalizzate.Service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController {
    @Autowired
    ProdottoService prodottoService;

    @PostMapping("/crea")
    public ResponseEntity<ProdottoEntity> creaProdotto(@RequestBody ProdottoEntity prodotto){
    ProdottoEntity prodotto1 = prodottoService.creaProdotto(prodotto);
    return ResponseEntity.ok(prodotto1);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ProdottoEntity>> ritornaLista(){
        return ResponseEntity.ok(prodottoService.readAllProduct());
    }

}
