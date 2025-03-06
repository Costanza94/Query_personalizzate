package com.example.Query.personalizzate.Controller;

import com.example.Query.personalizzate.Entity.ProdottoEntity;
import com.example.Query.personalizzate.Enumerated.CategoriaEnum;
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
    public ResponseEntity<ProdottoEntity> creaProdotto(@RequestBody ProdottoEntity prodotto) {
        ProdottoEntity prodotto1 = prodottoService.creaProdotto(prodotto);
        return ResponseEntity.ok(prodotto1);
    }

    @GetMapping("/select-all")
    public ResponseEntity<List<ProdottoEntity>> ritornaLista() {
        return ResponseEntity.ok(prodottoService.readAllProduct());
    }

    @GetMapping("/trova-per/{id}")
    public ResponseEntity<ProdottoEntity> trovaPerId(@PathVariable Integer id) {
        return prodottoService.findById(id);
    }

    @PutMapping("/update-prodotto/{id}")
    public ResponseEntity<ProdottoEntity> aggiornaProdotto(@PathVariable Integer id, @RequestBody ProdottoEntity prodotto) {
        Optional<ProdottoEntity> prodotto1 = prodottoService.updateProdotto(id, prodotto);
        if (prodotto1.isPresent()) {
            return ResponseEntity.ok(prodotto1.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete-all-products")
    public ResponseEntity<List<ProdottoEntity>> cancellaTutto() {
        return ResponseEntity.ok(prodottoService.deleteAll());
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<ProdottoEntity> cancellaPerId(@PathVariable Integer id){
        return prodottoService.deleteById(id);
    }

    @GetMapping("/find-by-category")
    public ResponseEntity<List<ProdottoEntity>> trovaPerCategoria(@RequestParam CategoriaEnum categoriaEnum){
        List<ProdottoEntity> prodottoEntityList = prodottoService.findByCategory(categoriaEnum);
        return ResponseEntity.ok(prodottoEntityList);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<List<ProdottoEntity>> trovaPerNome(@RequestParam String nome){
        List<ProdottoEntity> prodottoEntityList = prodottoService.findByName(nome);
        return ResponseEntity.ok(prodottoEntityList);
    }

    @GetMapping("/find-by-price")
    public ResponseEntity<List<ProdottoEntity>> trovaPerPrezzoMinore(@RequestParam Double prezzo){
        List<ProdottoEntity> entityList = prodottoService.findByPriceLessThan(prezzo);
        return ResponseEntity.ok(entityList);
    }

    @GetMapping("find-price-desc")
    public ResponseEntity<List<ProdottoEntity>> trovaPerPrezzoDesc(@RequestParam Double prezzo){
        //dobbiamo richiamare il meotodo che ci ritorna la lista desiderata
        List<ProdottoEntity> listaQuery = prodottoService.findByPriceDesc(prezzo);
        //ritorniamo una respons    e entity con la lista degli elementi
        //NB: se non ci sono elementi che soddisfano queste condizione sarà
        // una response entity di una lista vuota
        return ResponseEntity.ok(listaQuery);
    }
}
