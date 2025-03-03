package com.example.Query.personalizzate.Service;

import com.example.Query.personalizzate.Entity.ProdottoEntity;
import com.example.Query.personalizzate.Enumerated.CategoriaEnum;
import com.example.Query.personalizzate.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {
    @Autowired
    ProdottoRepository prodottoRepository;

    //Crate
    public ProdottoEntity creaProdotto(ProdottoEntity prodotto) {
        return prodottoRepository.save(prodotto);
    }

    //read all product
    public List<ProdottoEntity> readAllProduct() {
        return prodottoRepository.findAll();
    }

    //find by id
    public ResponseEntity<ProdottoEntity> updateList(Integer id) {
        if (prodottoRepository.existsById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Update product
    public Optional<ProdottoEntity> updateProdotto(Integer id, ProdottoEntity prodotto) {
        Optional<ProdottoEntity> prodottoEntityOptional = prodottoRepository.findById(id);
        if (prodottoEntityOptional.isPresent()) {
            prodottoEntityOptional.get().setDescrizione(prodotto.getDescrizione());
            prodottoEntityOptional.get().setNome(prodotto.getNome());
            prodottoEntityOptional.get().setPrezzo(prodotto.getPrezzo());
            prodottoEntityOptional.get().setDataCreazione(prodotto.getDataCreazione());
            prodottoEntityOptional.get().setCategoriaEnum(prodotto.getCategoriaEnum());
            prodottoEntityOptional.get().setQuantitaDisponibile(prodotto.getQuantitaDisponibile());
            ProdottoEntity prodottoAggiornato = prodottoRepository.save(prodotto);
            return Optional.of(prodottoAggiornato);
        }
        return Optional.empty();
    }

    //Delete all products
    public List<ProdottoEntity> deleteById(Integer id) {
        List<ProdottoEntity> productList = prodottoRepository.findAll();
        prodottoRepository.deleteAll();
        return productList;
    }

    //Delete by id
    public ResponseEntity<ProdottoEntity> prodotto(Integer id) {
        if (!prodottoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        prodottoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //Find by category
    public Optional<List<ProdottoEntity>> findByCategory(ProdottoEntity prodotto) {
        List<ProdottoEntity> prodottoEntityOptional = prodottoRepository.findByCategoriaEnum(CategoriaEnum.VESTITI);
        return Optional.ofNullable(prodottoEntityOptional);
    }

    //Find by name
    public List<ProdottoEntity> findByName(String nome) {
        return prodottoRepository.findByNomeContaining(nome);
    }

    // Find by prezzo
    public List<ProdottoEntity> findByPrice(Double prezzo) {
        return prodottoRepository.findByPrezzoLessThan(prezzo);
    }

    //Desc Order
    public List<ProdottoEntity> findByPriceDesc(Double prezzo) {
        return prodottoRepository.findByPrezzoOrderByPrezzoDesc(prezzo);
    }
}
