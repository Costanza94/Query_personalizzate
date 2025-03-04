package com.example.Query.personalizzate.Controller;

import com.example.Query.personalizzate.Entity.ProdottoEntity;
import com.example.Query.personalizzate.Service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/select-all")
    public ResponseEntity<List<ProdottoEntity>> ritornaLista(){
        return ResponseEntity.ok(prodottoService.readAllProduct());
    }

    @GetMapping("/trova-per/{id}")
    public ResponseEntity<ProdottoEntity> trovaPerId(@PathVariable Integer id){
        return prodottoService.findById(id);
    }

    @PutMapping("/update-prodotto/{id}")
    public ResponseEntity<ProdottoEntity> aggiornaProdotto(@PathVariable Integer id, @RequestBody ProdottoEntity prodotto){
        Optional<ProdottoEntity> prodotto1 = prodottoService.updateProdotto(id, prodotto);
        if(prodotto1.isPresent()){
            return ResponseEntity.ok(prodotto1.get());
        }
        return ResponseEntity.notFound().build();
    }

}
